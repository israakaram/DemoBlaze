//Author: Israa Karam Sayed
package Test_suite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import PAGES.Sign_up;
import java.time.Duration;
import java.util.Random;
public class sign_up_test {

    WebDriver driver;
    WebDriverWait wait;
    Sign_up signUpPage;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/index.html");

        signUpPage = new Sign_up(driver);
    }

    @Test
    public void testValidSignUp() throws InterruptedException {
        String randomUsername = "userrrrr" + new Random().nextInt(1000);
        String randomPassword = "pass123";

        signUpPage.openSignUpModal();
        signUpPage.enterUsername(randomUsername);
        signUpPage.enterPassword(randomPassword);
        signUpPage.clickSignUpButton();

        String alertText = signUpPage.getAlertText();
        signUpPage.acceptAlert();

        Assert.assertEquals(alertText, "Sign up successful.", "Sign up message mismatch!");


    }

    @Test
    public void testSignUpWithEmptyFields() throws InterruptedException {
        signUpPage.openSignUpModal();
        signUpPage.clickSignUpButton();

        String alertText = signUpPage.getAlertText();
        signUpPage.acceptAlert();
        Assert.assertTrue(alertText.contains("Please fill out Username and Password."),
                "Empty fields error message not displayed!");

        signUpPage.clickXButton();

    }

    @Test
    public void testSignUpWithExistingUser() {
        signUpPage.openSignUpModal();
        signUpPage.enterUsername("israak8");
        signUpPage.enterPassword("1");
        signUpPage.clickSignUpButton();

        String alertText = signUpPage.getAlertText();
        signUpPage.acceptAlert();
        signUpPage.clickXButton();

        Assert.assertTrue(alertText.contains("This user already exist."),
                "Existing user error message not displayed!");
    }

    @Test
    public void testSignUpWithShortPassword() {
        signUpPage.openSignUpModal();
        signUpPage.enterUsername("threedaysdown");
        signUpPage.enterPassword("ج");
        signUpPage.clickSignUpButton();

        String alertText = signUpPage.getAlertText();
        signUpPage.acceptAlert();
        signUpPage.clickXButton();

        Assert.assertTrue(alertText.contains("Password too short"),
                "Short password error message not displayed!");
    }

    @Test
    public void testSignUpWithShortUsername() {
        signUpPage.openSignUpModal();
        signUpPage.enterUsername("`~");
        signUpPage.enterPassword("1");
        signUpPage.clickSignUpButton();

        String alertText = signUpPage.getAlertText();
        signUpPage.acceptAlert();
        signUpPage.clickXButton();
        Assert.assertTrue(alertText.contains("Username is too short"),
                "Short username error message not displayed!");
    }

   @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }}
}

