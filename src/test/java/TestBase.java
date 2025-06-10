import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;


public class TestBase {
    protected WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/index.html");
}

    @Test
    public void testTitle() {
        String title = driver.getTitle();
        System.out.println("Page title is: " + title);
        Assert.assertEquals(title, "STORE", "not correct URL");
    }

    @Test
    public void testLoginFunctionality() throws InterruptedException {
        driver.findElement(By.id("login2")).click(); // Click on LOGIN
        Thread.sleep(2000); // Wait for modal to appear

        driver.findElement(By.id("loginusername")).sendKeys("Tabler"); // Enter User Name
        driver.findElement(By.id("loginpassword")).sendKeys("12345678910"); // Enter Password
        driver.findElement(By.xpath("//*[@id='logInModal']/div/div/div[3]/button[2]")).click();
        // After Username and Password Click on

        // Wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement usernameLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        System.out.println("Username label text: " + usernameLabel.getText());

        //Assert.assertTrue(usernameLabel.getText().contains(""), "Faild login !"); // Check LOgin
    }

    @Test
    public void testSignUp() throws InterruptedException {
        driver.findElement(By.id("signin2")).click();
        Thread.sleep(2000); // Wait for modal to appear
        driver.findElement(By.id("sign-username")).sendKeys("nonari");
        driver.findElement(By.id("sign-password")).sendKeys("9789456123");
        driver.findElement(By.xpath("//*[@id=\"signInModal\"]/div/div/div[3]/button[2]")).click();
    }

    @Test
    public void testCartNavigation() {
        driver.findElement(By.id("cartur")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("cart"), "Failed to navigate to Cart!");
    }

    @Test
    public void testContactUs() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[2]/a")).click();
        Thread.sleep(2000); // Wait for modal to appear
        driver.findElement(By.id("recipient-email")).sendKeys("Habiba99@gmail.com");
        driver.findElement(By.id("recipient-name")).sendKeys("Habiba");
        driver.findElement(By.id("message-text")).sendKeys("not fair for one person");

    }

    @Test
    public void testCategoriesNavigation() {
        driver.findElement(By.id("cat")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"itemc\"]")).isDisplayed(), "Laptops category not loaded!");

        driver.findElement(By.xpath("/html/body/div[5]/div/div[1]/div/a[4]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[5]/div/div[1]/div/a[4]")).isDisplayed(), "Monitors category not loaded!");
    }

    @Test
    public void testHomeNavigation() {
        driver.findElement(By.xpath("/html/body/nav/div[1]/ul/li[1]/a")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("index.html"), "Failed to navigate to Home!");
    }

    @Test
    public void testProductsClick() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // products
        String[] productXPaths = {
                "//*[@id=\"tbodyid\"]/div[1]/div/a/img", // phone
                "//*[@id=\"tbodyid\"]/div[8]/div/a/img", // laptop
                "//*[@id=\"tbodyid\"]/div[5]/div/a/img"  // mointor
        };

        for (String productXPath : productXPaths) {

            WebElement product = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(productXPath)));
            product.click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"navbarExample\"]/ul/li[1]/a"))); 
            driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[1]/a")).click();
        }
    }
        @AfterTest
        public void tearDown() {
            // Close the browser after all tests
            if (driver != null) {
                driver.quit();
            }
        }
    }



