package PAGES;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Contact_us
{   private WebDriverWait wait;
    private WebDriver driver;
    // locators (elements)
    private By contactLink = By.xpath("//*[@id=\"navbarExample\"]/ul/li[2]/a");
    private By contactEmail = By.xpath("//*[@id=\"recipient-email\"]");
    private By contactName = By.id("recipient-name");
    private By message = By.id("message-text");
    private By sendButton = By.xpath("//*[@id=\"exampleModal\"]/div/div/div[3]/button[2]");
    private final By contactModal = By.cssSelector("div.modal.fade.show");

    public void Contact_uso(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openContactModal() {
        try {
            WebElement contactBtn = wait.until(ExpectedConditions.elementToBeClickable(contactLink));
            contactBtn.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(contactModal));
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to open contact modal: " + e.getMessage());
        }
    }

    public void fillContactForm(String email, String name, String messageText) {
        clearAndSendKeys(contactEmail, email);
        clearAndSendKeys(contactName, name);
        clearAndSendKeys(message, messageText);
    }

    public void submitForm() {
        try {
            WebElement sendBtn = wait.until(ExpectedConditions.elementToBeClickable(sendButton));
            sendBtn.click();
            wait.until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException e) {
            throw new RuntimeException("Form submission failed: " + e.getMessage());
        }
    }

    public String getAlertTextAndAccept() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String text = alert.getText();
            alert.accept();
            return text;
        } catch (NoAlertPresentException e) {
            throw new RuntimeException("No alert present: " + e.getMessage());
        }
    }

    public void closeModalIfDisplayed() {
        try {
            if (driver.findElement(contactModal).isDisplayed()) {
                driver.findElement(By.cssSelector("#exampleModal .btn-secondary")).click();
                wait.until(ExpectedConditions.invisibilityOfElementLocated(contactModal));
            }
        } catch (NoSuchElementException e) {
            // Modal already closed
        }
    }

    private void clearAndSendKeys(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }


        }