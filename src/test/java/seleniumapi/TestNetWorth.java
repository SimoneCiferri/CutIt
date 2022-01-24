package seleniumapi;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestNetWorth {

    @Test
    public void testNetWorth(){
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com/search?q=jeff+bezon+patrimonio&oq=jeff+bezon+patrimonio&aqs=chrome..69i57j0i13l7j0i22i30l2.3007j1j7&sourceid=chrome&ie=UTF-8");
        WebElement webElement = driver.findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div/div[1]/div/div[1]/div[1]/div/div[1]/div/div[1]/div[2]/div/div[1]"));

        String netWorth = webElement.getText();
        System.out.println(netWorth);
        Double netWorthValue = Double.parseDouble(netWorth);
        boolean test = netWorthValue > 170.0;
        assertEquals(true, test);

        driver.close();
    }

}
