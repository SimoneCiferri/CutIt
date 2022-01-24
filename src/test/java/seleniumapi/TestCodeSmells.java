package seleniumapi;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCodeSmells {

    @Test
    public void testNetWorth(){
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://it.wikipedia.org/wiki/Jeff_Bezos");
        WebElement webElement = driver.findElement(By.xpath("//*[@id=\"gsc_rsb_st\"]/tbody/tr[1]/td[2]"));

        String stringCit = webElement.getText();
        System.out.println(stringCit);
        int cit = Integer.parseInt(stringCit);
        boolean test = cit > 1800;
        assertEquals(true, test);

        driver.close();
    }

}
