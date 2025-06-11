//Author: Israa Karam Sayed
package PAGES;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Sign_up {

    private WebDriver driver;
    private WebDriverWait wait;

    //Locators
    private By signUpNavButton = By.id("signin2");
    private By usernameField = By.id("sign-username");
    private By passwordField = By.id("sign-password");
    private By signUpConfirmButton = By.xpath("//button[text()='Sign up']");
    private By closeButtonLocator = By.xpath("(//button[@class=\"close\"]/span[contains(text(),'×')])[2]");

    //Constructor
    public Sign_up(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Open the sign up modal
    public void openSignUpModal() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(signUpNavButton)));
        driver.findElement(signUpNavButton).click();

    }

    //Enter username
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(usernameField)));
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
    }

    //Enter password
    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    //Click the sign-up button to submit
    public void clickSignUpButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(signUpConfirmButton));
        driver.findElement(signUpConfirmButton).click();
    }

    //Get alert message text
    public String getAlertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }

    //Accept the alert popup
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }
    public void clickXButton(){


        // Wait until the button is clickable
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(closeButtonLocator));

        closeButton.click();
    }
}

