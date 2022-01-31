package selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestGoleadorPrice {

    //Test Selenium API Di Filippo
    @Test
    public Integer testPriceGoleador() {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.it/Goleador-Cola-doppia-caramella-gusto/dp/B00AA2G3OA/ref=sr_1_1_sspa?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&crid=2A6I99AN9Y3BS&keywords=goleador+cola&qid=1643024396&rdc=1&sprefix=goleador+cola%2Caps%2C71&sr=8-1-spons&psc=1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUEyRDZIVUVDM0xJWTdXJmVuY3J5cHRlZElkPUEwNDI4Nzg1Mzc1NDhUTTNMWFY4NiZlbmNyeXB0ZWRBZElkPUEwNDMyNDIyM1RJQ0M5NkNCMjlRNCZ3aWRnZXROYW1lPXNwX2F0ZiZhY3Rpb249Y2xpY2tSZWRpcmVjdCZkb05vdExvZ0NsaWNrPXRydWU=");

        WebElement element = driver.findElement(By.xpath("//*[@id=\"corePriceDisplay_desktop_feature_div\"]/div[1]/span[1]/span[2]/span[1]"));
        driver.findElement(By.xpath("//*[@id=\"sp-cc-accept\"]")).click();
        String price = element.getText();
        return Integer.parseInt(price);
    }

    @Test
    public void priceChecker(){
        TestGoleadorPrice goleadorPrice = new TestGoleadorPrice ();
        double output = goleadorPrice.testPriceGoleador();
        assertTrue(output<= 20.00);

    }
}
