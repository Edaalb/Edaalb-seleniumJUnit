package day07_dropdown_jsAlerts;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C02_JsAlerts {
     /*
    Bir Alert'ün JavaScript Alert'ü olduğunu şu şekilde anlarız? -->
          >Tıkladıktan sonra Inspect yapamayız.
    Eğer bir JSAlert ile karşı karşıyaysak işlem yapacağımız her seferinde
    mutlaka driver'ı önce Alert'e geçirmemiz gerekir. Bunun için her işlem için
    driver.switchTo().alert() deriz.
        > OK tuşuna basmak için accept
        > cancel için dismiss
        > alert üzerindeki yazıyı almak için getText
        > prompt ise yazı göndermek için sendKeys
     */

    // Gerekli ayarlamalari yapip
    // https://the-internet.herokuapp.com/javascript_alerts  adresine gidin
    // 3 test method'u olusturup her method'da bir JsAlert'e basin
    // Ilgili method'lari kullanin

    static WebDriver driver;

    @BeforeClass
    public static void setUp(){

        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @AfterClass
    public static void teardown() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
    }
    //test methodları static olmaz,
    // yukarıdaki WebDriver methodu static olduğu için her yerden kullanılabilir

    @Test
    public void test01() throws InterruptedException {

        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        // 1.alert'e tiklayalim

        driver.findElement(By.xpath("//*[text()='Click for JS Alert']")).click();

        // Alert'deki yazinin "I am a JS Alert" oldugunu test edelim

        //driver ile alert'e gittiğimizde bir menü çıkar ancak sağ tuşa basıp locate edilemiyor
        //ancak driver'ı o alert'e geçmek lazım bunun için switchTo.alert kullanırız
        String actualAlertyazisi = driver.switchTo().alert().getText();
        String expectedAlertYazisi="I am a JS Alert";

        Thread.sleep(3000);

        Assert.assertEquals(expectedAlertYazisi,actualAlertyazisi);

        // OK tusuna basip alert'i kapatalim

        driver.switchTo().alert().accept();

    }

    @Test
    public void test02() throws InterruptedException {

        //her methodu ayrı ayrı çalıştırmak için her birinde gitme işini yapmalıyız
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        // 2.alert'e tiklayalim
        driver.findElement(By.xpath("//*[text()='Click for JS Confirm']")).click();

        // cancel'a basip, cikan sonuc yazisinin "You clicked: Cancel" oldugunu test edin
        Thread.sleep(3000);
        driver.switchTo().alert().dismiss();

        String actualSonucYazisi=driver.findElement(By.xpath("//*[text()='You clicked: Cancel']"))
                .getText();


        String expectedSonucYazisi="You clicked: Cancel";

        Assert.assertEquals(expectedSonucYazisi,actualSonucYazisi);

    }

    @Test
    public void test03() throws InterruptedException {

        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        // 3.alert'e tiklayalim

        driver.findElement(By.xpath("//*[text()='Click for JS Prompt']")).click();

        // Cikan prompt ekranina "Abdullah" yazdiralim ve OK tusuna basarak alert'i kapatalim

        driver.switchTo().alert().sendKeys("Abdullah");
        Thread.sleep(3000);

        driver.switchTo().alert().accept();

        // Cikan sonuc yazisinin Abdullah icerdigini test edelim

        String actualSonucYazisi=driver.findElement(By.xpath("//*[@id='result']"))
                .getText();

        String expectedKelime= "Abdullah";

        Assert.assertTrue(actualSonucYazisi.contains(expectedKelime));
    }

}
