package day09_actions;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.ReusableMethods;
import utilities.TestBase;

public class C05_FormDoldurma extends TestBase {

    @Test                           //id sayfa güncellendiğinde değişiyor
    public void test01() {

        // facebook.com sayfasina gidin
        driver.get("https://www.facebook.com");

        // cookies'i kabul et
        driver.findElement(By.xpath("//button[@title='Only allow essential cookies']")).click();

        // yeni hesap olustur butonuna basin
        driver.findElement(By.xpath("//a[@class='_42ft _4jy0 _6lti _4jy6 _4jy2 selected _51sy']")).click();

        // ilgili alanlari faker kutuphanesinden degerlerle doldurup
        WebElement firstnameKutusu=driver.findElement(By.name("firstname"));

        Actions actions = new Actions(driver);
        Faker faker= new Faker();

        String mailAdresi=faker.internet().emailAddress(); //email adresi doğrulama isteyeceği için
        //baştan bir mail üretip kaydediyoruz

        actions.click(firstnameKutusu)
                .sendKeys(faker.name().firstName())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.name().lastName())
                .sendKeys(Keys.TAB)
                .sendKeys(mailAdresi)
                .sendKeys(Keys.TAB)
                .sendKeys(mailAdresi)
                .sendKeys(Keys.TAB)
                .sendKeys(faker.internet().password())
                .sendKeys(Keys.TAB) //manuel olarak girerken kaçınca tab'da geçtiğine bakarız
                .sendKeys(Keys.TAB)
                .sendKeys("23")
                .sendKeys(Keys.TAB)
                .sendKeys("Oct")
                .sendKeys(Keys.TAB)
                .sendKeys("1990")
                .sendKeys(Keys.TAB) //male seçmek için 2 tab yapıldı
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ARROW_RIGHT).perform(); //male seçmek için

        // kaydol butonuna basin
        WebElement signupButonu= driver.findElement(By.name("websubmit"));
        signupButonu.click();

        // Kayit olamadiginizi test edin

        ReusableMethods.bekle(20);

        WebElement hataMesajElementi= driver.findElement(By.id("reg_error_inner"));
        //hata mesajını alabilmek için TestBase class'ından otomatik kapatmayı durdurduk

        Assert.assertTrue(hataMesajElementi.isDisplayed());

    }
}

