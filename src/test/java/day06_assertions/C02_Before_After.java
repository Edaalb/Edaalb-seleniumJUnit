package day06_assertions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C02_Before_After {
    // create 3 different tests
    // before every  method, create a driver
    // 1 - amazon
    // 2- wisequarter
    // 3- youtube
    // visit these websites and print the titles and close drivers
    WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @After
    public void tearDown(){
        driver.close();
    }

    @Test
    public void test01(){
        driver.get("https://www.amazon.com");
        System.out.println(driver.getTitle());
    }

    @Test
    public void test02(){
        driver.get("https://www.wisequarter.com");
        System.out.println(driver.getTitle());
    }

    @Test
    public void test03(){
        driver.get("https://www.youtube.com");
        System.out.println(driver.getTitle());
    }
}
