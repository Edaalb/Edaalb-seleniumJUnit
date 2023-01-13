package day08_handlingWindows;

import org.junit.Assert;
import org.junit.Test;

public class C04_TestBaseIlkTest {
    @Test
    public void test01(){

        // amazon'a gidin
        driver.get("https://www.amazon.com");

        // amazon'a gittiginizi test edin

        String expectedKelime= "amazon";
        String actualUrl= driver.getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedKelime));

        ReusableMethods.bekle(3);
}
