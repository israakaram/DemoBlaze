//Author: Israa Karam Sayed
package Test_suite;

import PAGES.Sign_in;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;

public class login_test {

    WebDriver driver;
    Sign_in signInPage;

    @BeforeClass
    public void setUp() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/index.html");
        signInPage = new Sign_in(driver);
    }

    // Valid login scenario
    @Test (priority = 1)
    public void testSignInWithValidUsername() {
        signInPage.openLoginModal();
        signInPage.enterCredentials("israak", "123456");  // Replace with actual valid credentials
        signInPage.clickLoginButton();

        Assert.assertTrue(signInPage.isLoginSuccessful(), "Login failed!");
        driver.findElement(By.id("logout2")).click();
    }

    // Invalid login scenario with incorrect credentials
    @Test (priority = 2)
    public void testInvalidSignIn() {
        signInPage.openLoginModal();
        signInPage.enterCredentials("pyo", "123456");
        signInPage.clickLoginButton();
        String alertText = signInPage.getAlertMessage();
        Assert.assertEquals(alertText, "User does not exist.", "Unexpected alert message!");
        signInPage.acceptAlert();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
