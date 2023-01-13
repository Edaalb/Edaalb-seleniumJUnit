package day05_junitFramework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C02_BeforeAfter {
    // 3 farkli test method'u olusturun
    // her bir method'un basinda driver'i olusturup
    // 1- amazon.com
    // 2- wisequarter.com
    // 3- youtube.com'a gidip
    // title'lari yazdirin ve method'dan sonra driver'i kapatin

    WebDriver driver; //driver'ın her yerden görünebilmesi için class level'da olması lazım

    @Before //her method'un başında oluşturmamız isteniyor
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        System.out.println("setUp method'u calisti");
    }

    @After //kapatma işlemi her method'dan sonra olacak
    public void tearDown(){
        System.out.println("teardown method'u calisti");
        driver.close();

    }
    //bizden istenen 3 test method'unu oluşturalım

    @Test
    public void test01() throws InterruptedException {
        driver.get("https://www.amazon.com");
        System.out.println(driver.getTitle());
        Thread.sleep(3000);
    }

    @Test
    public void test02() throws InterruptedException {
        driver.get("https://www.wisequarter.com");
        System.out.println(driver.getTitle());
        Thread.sleep(3000);
    }
    @Test
    public void test03() throws InterruptedException {
        driver.get("https://www.youtube.com");
        System.out.println(driver.getTitle());
        Thread.sleep(3000);
    }

    //eğer before-after notasyonlarını kullanıyorsak her test methodundan
    // önce before, sonra da after çalışır.

    //bazen her seferinde açmak kapatmak istemyebiliriz. Örneğin aynı sitede bir şey aratıyorsak
    //bunun da farklı handikapları vardır
}
