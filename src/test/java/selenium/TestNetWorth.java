package selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.StringTokenizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestNetWorth {

    @Test
    public void testNetWorth(){
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com/search?q=jeff+bezon+patrimonio&oq=jeff+bezon+patrimonio&aqs=chrome..69i57j0i13l7j0i22i30l2.3007j1j7&sourceid=chrome&ie=UTF-8");
        WebElement cookies = driver.findElement(By.xpath("//*[@id=\"L2AGLb\"]/div"));
        cookies.click();

        WebElement webElement = driver.findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div/div[1]/div/div[1]/div[1]/div/div[1]/div/div[1]/div[2]/div/div[1]"));

        String netWorth = webElement.getText();
        driver.close();
        StringTokenizer st = new StringTokenizer(netWorth);
        String value = st.nextToken();
        value = value.replace(",", ".");
        double netWorthValue = Double.parseDouble(value);
        boolean test = netWorthValue > 168.0;
        assertTrue(test);
    }

}
