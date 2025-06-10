package Test_suite.Base_For_test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Base_before_TEST {

    protected WebDriver driver;
    protected WebDriverWait wait;
    @BeforeClass
    public void setup()
    {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/index.html");
    }
    @AfterClass
    public void tearDown()
    {
        if (driver != null) {
            driver.quit();
        }
    }
}
