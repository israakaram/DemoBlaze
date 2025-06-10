package PAGES;
// author : Habiba Ragab Abdelmoneam
// to understand the architecture of the code
// pages folder contain elements and methods , test case contain tests (as suites)
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class About_us {
    private WebDriverWait wait;
    private WebDriver driver;
    private About_us AU_page;
    private By modalTitleLocator = By.xpath("//*[@id=\"navbarExample\"]/ul/li[3]/a");
    private By ImagesLocator = By.xpath("//*[@id=\"example-video\"]/div[1]");
    private By Video_locator=By.xpath("//*[@id=\"example-video_html5_api\"]");
    private By playButtonLocator = By.id("example-video_html5_api");
    private By closeButtonLocator = By.xpath("//*[@id=\"videoModal\"]/div/div/div[3]/button");


    public About_us(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public By getModalTitleLocator() {
        return modalTitleLocator;
    }

    public By getImagesLocator() {
        return ImagesLocator;
    }

    public WebElement getModalTitle() {
        return driver.findElement(getModalTitleLocator());
    }

    public List<WebElement> getAllImages() {
        return driver.findElements(getImagesLocator());
    }

    public boolean areImagesDisplayed() {
        List<WebElement> images = getAllImages();
        if (images.isEmpty()) {
            return false;
        }

        for (WebElement img : images) {
            if (!img.isDisplayed()) {
                return false;
            }
        }
        return true;
    }
    //open the About us button
    public void openAboutUsModal() {
        driver.findElement(modalTitleLocator).click();
    }

    public boolean isVideoDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(Video_locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
//video display
    public boolean isVideoPlayable() {
        try {
            WebElement video = driver.findElement(Video_locator);
            return video.getAttribute("readyState").equals("4");
        } catch (Exception e) {
            return false;
        }
    }
    //closed button
    public boolean isCloseButtonDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(closeButtonLocator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void clickPlayButton() {
        wait.until(ExpectedConditions.elementToBeClickable(playButtonLocator)).click();
    }

    public boolean isVideoPlaying() {
        try {
            WebElement video = driver.findElement(Video_locator);
            return (boolean) ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("return arguments[0].currentTime > 0 && !arguments[0].paused && !arguments[0].ended", video);
        } catch (Exception e) {
            return false;
        }
    }
    public void clickCloseButton() {
        wait.until(ExpectedConditions.elementToBeClickable(closeButtonLocator)).click();
    }
}
