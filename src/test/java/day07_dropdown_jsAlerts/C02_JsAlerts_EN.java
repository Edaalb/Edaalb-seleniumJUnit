package day07_dropdown_jsAlerts;

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

public class C02_JsAlerts_EN {
    // set properties
    // go to "https://the-internet.herokuapp.com/javascript_alerts"
    // test all js allers
    // use regarding methods

    // set properties

    static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void tearDown(){
        driver.close();
    }

    @Test
    public void test01() throws InterruptedException {

        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        WebElement jsSimpleAlert = driver.findElement(By.xpath("//*[text()='Click for JS Alert']"));
        jsSimpleAlert.click();

        // Test the text of the simple alert is "I am a JS Alert"
        String expectedSimpleJSText = "I am a JS Alert";
        String actualSimpleJSText = driver.switchTo().alert().getText();

        Thread.sleep(3000);
        Assert.assertEquals(expectedSimpleJSText,actualSimpleJSText);

        driver.switchTo().alert().accept();

    }

    @Test
    public void test02(){

        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        WebElement jsConfirmAlert = driver.findElement(By.xpath("//*[text()='Click for JS Confirm']"));
        jsConfirmAlert.click();

        String expectedConfirmAlertText = "I am a JS Confirm";
        String actualConfimrAlertText = driver.switchTo().alert().getText();

        Assert.assertEquals(expectedConfirmAlertText,actualConfimrAlertText);

        driver.switchTo().alert().dismiss();


        String expectedText = "You clicked: Cancel";
        String actualText = driver.findElement(By.id("result")).getText();

        Assert.assertEquals(expectedText,actualText);

    }

    @Test
    public void Test03(){

        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElement(By.xpath("//*[text()='Click for JS Prompt']")).click();

        driver.switchTo().alert().sendKeys("Ahmet");
        driver.switchTo().alert().accept();

        // test that result contains entered word

        String expectedName ="Ahmet";
        String actualText = driver.findElement(By.id("result")).getText();

        Assert.assertTrue(actualText.contains(expectedName));

    }

}
