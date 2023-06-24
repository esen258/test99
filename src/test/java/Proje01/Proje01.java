package Proje01;

import Utilities.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

public class Proje01 extends TestBase {
    @Test
    public void test01() {
        //YouTube adresine gidelim
        driver.get("https://youtube.com");

        //arama kutusuna istedigin sarkiyi arat
        WebElement arama =   driver.findElement(By.xpath("//input[@id='search']"));
        arama.click();
        arama.sendKeys("kuzu kuzu", Keys.ENTER);

        //istediginiz sarkiya tiklayin
        driver.findElement(By.xpath("//a[@id='video-title']")).click();

        //sarkinin url adresini kopyala
        String muzik = driver.getCurrentUrl();

        //MP3 donusturucu sitesine git 'https://notube.net/tr/youtube-app-v72'

        bekle(3);

        driver.get("https://notube.net/tr/youtube-app-v72");

        String ilkSayfaHandle = driver.getWindowHandle();

        // link ekleyecegimiz kutunun lacatorunu al ve linkli ekle
        WebElement donusturmeKutusu = driver.findElement(By.xpath("//input[@id='keyword']"));
        donusturmeKutusu.click();
        donusturmeKutusu.sendKeys(muzik);

        //kategorilerden mp3'u sec
        WebElement ddm = driver.findElement(By.xpath("//select[@id='myDropdown']"));
        Select select = new Select(ddm);
        select.selectByValue("mp3");

        bekle(3);

        //tamam butonuna tiklayin
        ddm.sendKeys(Keys.TAB,Keys.ENTER);

        //yeni acilan sayfadan eski sayfaya gecis yap
        driver.switchTo().window(ilkSayfaHandle);

        bekle(5);
        //indir butonunun gelmesini bekle ve sonra tikla

        WebElement indirBekle = driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-download-alt']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(indirBekle));

        //Files.delete(Paths.get("/Users/aydogduyunuss/Downloads/TARKAN - Kuzu Kuzu (Original).mp3"));

        //indir butonuna tikla
        WebElement indir = driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-download-alt']"));
        indir.click();

        //onceki sayfaya don
        driver.switchTo().window(ilkSayfaHandle);

        bekle(20);

        //indirmeyi dogrula
        Assert.assertTrue(Files.exists(Paths.get("/Users/aydogduyunuss/Downloads/TARKAN - Kuzu Kuzu (Original).mp3")));











    }
}
