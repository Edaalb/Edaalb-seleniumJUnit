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

public class C04_IFrame_EN {
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


//1) Go to https://the-internet.herokuapp.com/iframe.
//2 ) Create a method: iframeTest
// - “An IFrame containing….” Test that the text is accessible and print it in the console.
// - Write "Hello World!" to Text Box.
// - Verify that the text of the “Elemental Selenium” link at the bottom of the TextBox is visible and print it on the console.

    @Test
    public void test02() throws InterruptedException {

        driver.get("https://the-internet.herokuapp.com/iframe");

        WebElement IFrameFirstText = driver.findElement(By.tagName("h3"));
        String IframeFirstTextStr = IFrameFirstText.getText();

        System.out.println(IframeFirstTextStr);

        //driver.switchTo().frame("mce_0_ifr"); // you can reach Iframe by Id

        WebElement IframeWebElement =driver.findElement(By.id("mce_0_ifr"));

        driver.switchTo().frame(IframeWebElement);

        WebElement textBox = driver.findElement(By.id("tinymce"));
        Thread.sleep(1000);
        textBox.clear();
        Thread.sleep(1000);
        textBox.sendKeys("Hello World!");
        Thread.sleep(2000);

        driver.switchTo().defaultContent(); // driver will go back to homepage (starting point of html codes)

        WebElement finalText=driver.findElement(By.xpath("//div[text()='Powered by ']"));
        Assert.assertTrue(finalText.isDisplayed());

    }
}
