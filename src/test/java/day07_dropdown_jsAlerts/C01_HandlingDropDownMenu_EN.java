package day07_dropdown_jsAlerts;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class C01_HandlingDropDownMenu_EN {
    // 1- set properties
    // 2- go to the "https://www.amazon.com"
    // 3- select "Books" from the dropdown menu
    // 4- search for "Java"
    // 5- test if title contains "Java"


    // 1- set properties
    WebDriver driver;

    @Before
    public void setUp (){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(14));

    }

    @After
    public void tearDown(){
        driver.close();
    }

    @Test
    public void test01() throws InterruptedException {
        // 2- go to the "https://www.amazon.com"
        driver.get("https://www.amazon.com");

        // 3- select "Books" from the dropdown menu

        /*
            To select an option from dropdown menu first we need to create an object from Select Class
            but this select object will ask us to enter drop down webelement so we need to locate it
            we will use the select object
         */

        WebElement dropDownMenu = driver.findElement(By.id("searchDropdownBox"));

        Select select = new Select(dropDownMenu);
        //select.selectByIndex(5); //we can select options by using the index ( index starts from zero)
        //select.selectByValue("search-alias=stripbooks-intl-ship");
        select.selectByVisibleText("Books");

        // 4- search for "Java"

        WebElement amazonSearchBox = driver.findElement(By.id("twotabsearchtextbox"));
        amazonSearchBox.sendKeys("Java"+ Keys.ENTER);

        // 5- test if title contains "Java"

        String expectedData = "Java";
        String actualData = driver.getTitle();
        System.out.println();

        Assert.assertTrue(actualData.contains(expectedData));

        // Test that Books had been chosen

        dropDownMenu = driver.findElement(By.id("searchDropdownBox"));
        select = new Select(dropDownMenu);
        select.selectByVisibleText("Books");
        String expectedDropDownMenuStr = "Books";
        String actualDropDownMenuStr =select.getFirstSelectedOption().getText();

        Assert.assertEquals(expectedDropDownMenuStr,actualDropDownMenuStr);

        // verify that there are 28 options in dropdownMenu

        int expectedOptonsNumber = 28;
        List<WebElement> optionList =select.getOptions();
        int actualOptionsNumber = optionList.size();

        Assert.assertEquals(expectedOptonsNumber,actualOptionsNumber);

    }

}
