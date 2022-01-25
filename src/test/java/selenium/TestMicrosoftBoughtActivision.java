package selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestMicrosoftBoughtActivision {


    @Test
    public void testHowMuchDidItPay(){

            System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
            WebDriver driver = new ChromeDriver();


            driver.get("https://www.google.com/search?q=how+much+microsoft+payed+for+activision&rlz=1C1JJTC_itIT987IT987&oq=how+much+microsoft+payed+for+activision&aqs=chrome..69i57j33i10i22i29i30l9.15514j1j7&sourceid=chrome&ie=UTF-8");
            WebElement webElement = driver.findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div/div[1]/div/div[1]/div/div/div/div/div[1]/div/span[1]/span/b"));

            String stringPay = webElement.getText();

            assertEquals("$68.7 billion", stringPay);

            driver.close();

}
}
