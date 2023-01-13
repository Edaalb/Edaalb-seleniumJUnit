package day06_assertions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C05_CheckBox {
    //Gerekli yapiyi olusturun ve aşağıdaki görevi tamamlayın.
    //	a. Verilen web sayfasına gidin.
    //	     https://the-internet.herokuapp.com/checkboxes
    //	b. Checkbox1 ve checkbox2 elementlerini locate edin.
    //	c. Checkbox1 seçili değilse onay kutusunu tıklayın ve secili oldugunu test edin
    //	d. Checkbox2 seçili değilse onay kutusunu tıklayın ve secili oldugunu test edin


    //(radio button sadece bir tane seçilebilir ör: kadın-erkek)
    //(checkbox birden fazla seçilebilir)
    WebDriver driver;
    @Before
    public void setUp(){
        //Gerekli yapiyi olusturun
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @After
    public void teardown(){
        driver.close();
    }

    @Test
    public void test01() throws InterruptedException {

        //	a. Verilen web sayfasına gidin.

        driver.get("https://the-internet.herokuapp.com/checkboxes");

        //	b. Checkbox1 ve checkbox2 elementlerini locate edin.

        WebElement cb1= driver.findElement(By.xpath("(//input[@type='checkbox'])[1]"));
        WebElement cb2= driver.findElement(By.xpath("(//input[@type='checkbox'])[2]"));

        //	c. Checkbox1 seçili değilse onay kutusunu tıklayın ve secili oldugunu test edin

        if (!cb1.isSelected()){
            cb1.click();
        }

        Assert.assertTrue(cb1.isSelected());

        //	d. Checkbox2 seçili değilse onay kutusunu tıklayın ve secili oldugunu test edin

        if (!cb2.isSelected()){
            cb2.click();
        }

        Assert.assertTrue(cb2.isSelected());

        Thread.sleep(3000);
    }
}
