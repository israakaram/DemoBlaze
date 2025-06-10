package PAGES;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class Sign_in { private WebDriver driver;
    private WebDriverWait wait;

    //Locators for login page
    private By loginButton = By.id("login2");
    private By usernameField = By.id("loginusername");
    private By passwordField = By.id("loginpassword");
    private By loginSubmitButton = By.xpath("//button[text()='Log in']");
    private By alertMessage = By.id("signInAlert");
    private By userNameLabel = By.id("nameofuser");

    //Constructor
    public Sign_in(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Method to click the login button to open the modal
    public void openLoginModal() {
        driver.findElement(loginButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
    }

    //Method to enter username and password
    public void enterCredentials(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
    }

    //Method to click the login button
    public void clickLoginButton() {
        driver.findElement(loginSubmitButton).click();
    }

    //Method to check if the login was successful
    public boolean isLoginSuccessful() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameLabel));
        return driver.findElement(userNameLabel).isDisplayed();
    }

    //Method to get the alert message text
    public String getAlertMessage() {
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }

 //   Method to accept the alert
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

}
