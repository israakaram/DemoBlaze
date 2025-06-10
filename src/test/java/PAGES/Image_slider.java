package PAGES;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Image_slider {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By sliderContainer = By.cssSelector("div.carousel-inner");
    private final By productImages = By.cssSelector("div.carousel-item img");
    private final By nextButton = By.cssSelector("a.carousel-control-next");
    private final By prevButton = By.cssSelector("a.carousel-control-prev");
    private final By activeSlide = By.cssSelector("div.carousel-item.active");

    public Image_slider(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isSliderDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(sliderContainer)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public List<WebElement> getAllProductImages() {
        return driver.findElements(productImages);
    }

    public int getTotalImagesCount() {
        return getAllProductImages().size();
    }

    public boolean isImageDisplayed(int index) {
        try {
            WebElement image = getAllProductImages().get(index);
            return image.isDisplayed();
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public void clickNextButton() {
        WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        nextBtn.click();
        waitForSlideTransition();
    }

    public void clickPrevButton() {
        WebElement prevBtn = wait.until(ExpectedConditions.elementToBeClickable(prevButton));
        prevBtn.click();
        waitForSlideTransition();
    }

    public String getCurrentActiveImageAltText() {
        WebElement activeImage = wait.until(ExpectedConditions.visibilityOfElementLocated(activeSlide))
                .findElement(By.tagName("img"));
        return activeImage.getAttribute("alt");
    }

    private void waitForSlideTransition() {
        try {
            Thread.sleep(500); // simple wait
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
