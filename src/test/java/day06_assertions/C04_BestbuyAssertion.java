package day06_assertions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C04_BestbuyAssertion {
    //JUnit'in handikaplarından biri test sıralamasını kendi belirliyor, harf sırası vs yok.
    //bu yüzden test1, test2.. şeklinde isimlendirmek en doğrusudur

    //1) Bir class oluşturun: BestBuyAssertions
    //2) https://www.bestbuy.com/ Adresine gidin farkli test method’lari olusturarak
    //   asagidaki testleri yapin
    //		○ Sayfa URL’inin https://www.bestbuy.com/ ‘a esit oldugunu test edin
    //		○ titleTest => Sayfa başlığının “Rest” içermediğini(contains) test edin
    //		○ logoTest => BestBuy logosunun görüntülendigini test edin
    //		○ FrancaisLinkTest => Fransizca Linkin görüntülendiğini test edin

    //Her method için driver'ın açılıp kapanmasına gerek yok çünkü tamamı bir sayfada
    //Tüm testleri aynı sayfa içerisinde yapabiliriz
    static WebDriver driver; //driver'ı class level'da oluşturuyoruz

    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        driver.get("https://www.bestbuy.com/"); //before class method'una yerleştiririz
        //böylece methodları ayrı ayrı çalıştırabiliriz
    }
    @AfterClass
    public static void teardown(){
        driver.close();
    }
    @Test
    public void urlTest(){

        //		○ Sayfa URL’inin https://www.bestbuy.com/ ‘a esit oldugunu test edin:

        String expectedUrl="https://www.bestbuy.com/";
        String actualUrl= driver.getCurrentUrl(); //actual her zaman driver'ın aldığıdır

        Assert.assertEquals(expectedUrl,actualUrl); //ikisinin eşit olduğunu test ederiz
    }
    @Test
    public void titleTest(){

        //		○ titleTest => Sayfa başlığının “Rest” içermediğini(contains) test edin:

        String expectedIcermeyenKelime="Rest";
        String actualTitle= driver.getTitle();

        Assert.assertFalse(actualTitle.contains(expectedIcermeyenKelime)); //içermediğini test ediyoruz
        //bu yüzden assertFalse
    }
    @Test
    public void logoTest(){

        //		○ logoTest => BestBuy logosunun görüntülendigini test edin:

        WebElement logoElementi= driver.findElement(By.xpath("(//img[@class='logo'])[1]"));

        Assert.assertTrue(logoElementi.isDisplayed());

    }

    @Test
    public void FransizcaLinkTesti(){

        //		○ FrancaisLinkTest => Fransizca Linkin görüntülendiğini test edin:

        WebElement fransizcaLinki= driver.findElement(By.xpath("//*[text()='Français']"));
        Assert.assertTrue(fransizcaLinki.isDisplayed());

    }
}
