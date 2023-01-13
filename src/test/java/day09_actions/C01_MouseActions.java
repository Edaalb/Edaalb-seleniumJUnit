package day09_actions;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.ReusableMethods;
import utilities.TestBase;

import java.util.Set;

public class C01_MouseActions extends TestBase {
    @Test
    public void test01(){

        //1- Yeni bir class olusturalim: MouseActions1

        //2- https://the-internet.herokuapp.com/context_menu sitesine gidin
        driver.get("https://the-internet.herokuapp.com/context_menu");

        //3- Cizili alan uzerinde sag click yapin
        Actions actions= new Actions(driver);
        WebElement ciziliAlanElementi= driver.findElement(By.xpath("//*[@id='hot-spot']"));
        //üzerine bastığımızda inspect çıkmadığı için ayrı bir yerde locate yaparız

        actions.contextClick(ciziliAlanElementi).perform();
        //actions ile başlayan her kod perform ile bitmelidir

        ReusableMethods.bekle(2);

        //4- Alert’te cikan yazinin “You selected a context menu” oldugunu test edin.
        String expectedAlertYazisi="You selected a context menu";
        String actualAlertYazisi=driver.switchTo().alert().getText();

        Assert.assertEquals(expectedAlertYazisi,actualAlertYazisi);

        //5- Tamam diyerek alert’i kapatalim
        driver.switchTo().alert().accept();

        //6- Elemental Selenium linkine tiklayalim
        //buna tıkladığımızda yeni sayfa açılacak. bu yüzden ilk sayfadaken click yapmadan önce
        //ilk sayfanın window handle değerini alıp kaydederiz.
        String ilkSayfaWHD= driver.getWindowHandle(); // CDwindow-F9742959E8FD03F25B0419B7B70EE203
        //değerler kalıcı değildir, her çalıştırdığımızda farklı değer verir

        System.out.println(ilkSayfaWHD);

        driver.findElement(By.linkText("Elemental Selenium")).click();

        //7- Acilan sayfada h1 taginda “Elemental Selenium” yazdigini test edelim

        //yukarıda ilk sayfanın window handle değerini alıp kaydettik
        //sonra click yapınca iki tane tab açık olacak
        //iki tab'ın WHD almak için 2. sayfanın WHD bulmak gerekir.
        //kaydetmek için set kullanırız

        Set<String> ikiSayfaninWHDSeti = driver.getWindowHandles();
        //elimizdeki sette bulunan iki değerden birisinin WHD eşit olduğunu, diğerinin eşit olmadığını biliyoruz
        //eşit olmayan ikinci sayfanın WHD değeridir deriz

        String ikinciSayfaWHD="";

        for (String eachWHD: ikiSayfaninWHDSeti
        ) {
            if (!eachWHD.equals(ilkSayfaWHD)){
                ikinciSayfaWHD= eachWHD;
            }
        }

        driver.switchTo().window(ikinciSayfaWHD); //2. sayfaya geçtik, testi burada yaparız

        String expectedYeniSayfaYazi="Elemental Selenium";
        String actualYeniSayfaYazi= driver.findElement(By.tagName("h1")).getText();

        Assert.assertEquals(expectedYeniSayfaYazi,actualYeniSayfaYazi);

        ReusableMethods.bekle(5);
    }
}
