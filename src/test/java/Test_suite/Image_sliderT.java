package Test_suite;
import PAGES.Image_slider;
import Test_suite.Base_For_test.Base_before_TEST;
import io.cucumber.java.be.I;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
public class Image_sliderT extends Base_before_TEST {
    private Image_slider ImageSliderPage;



    @Test(priority = 1, description = "Verify slider is displayed")
    public void testSliderVisibility() {
        Assert.assertTrue(ImageSliderPage.isSliderDisplayed(),
                "Image slider should be displayed on homepage");
    }

    @Test(priority = 2, description = "Verify all product images exist")
    public void testAllProductImagesExist() {
        int totalImages = ImageSliderPage.getTotalImagesCount();
        Assert.assertTrue(totalImages > 0, "There should be at least one product image in slider");

        for (int i = 0; i < totalImages; i++) {
            Assert.assertTrue(ImageSliderPage.isImageDisplayed(i),
                    "Product image at index " + i + " should be displayed");
        }
    }

    @Test(priority = 3, description = "Verify images are displayed one at a time")
    public void testSingleImageDisplay() {
        int activeImagesCount = driver.findElements(By.cssSelector("div.carousel-item.active")).size();
        Assert.assertEquals(activeImagesCount, 1,
                "Only one image should be active at a time");
    }

    @Test(priority = 4, description = "Test next button functionality")
    public void testNextButton() {
        String firstImageAlt = ImageSliderPage.getCurrentActiveImageAltText();
        ImageSliderPage.clickNextButton();
        String secondImageAlt = ImageSliderPage.getCurrentActiveImageAltText();

        Assert.assertNotEquals(firstImageAlt, secondImageAlt,
                "Next button should change the displayed image");
    }

    @Test(priority = 5, description = "Test previous button functionality")
    public void testPrevButton() {
        String beforeClickAlt = ImageSliderPage.getCurrentActiveImageAltText();
        ImageSliderPage.clickPrevButton();
        String afterClickAlt = ImageSliderPage.getCurrentActiveImageAltText();

        Assert.assertNotEquals(beforeClickAlt, afterClickAlt,
                "Previous button should change the displayed image");
    }

    @Test(priority = 6, description = "Verify full slider cycle")
    public void testFullSliderCycle() {
        int totalImages = ImageSliderPage.getTotalImagesCount();
        String firstImageAlt = ImageSliderPage.getCurrentActiveImageAltText();

        // Click next button for all images
        for (int i = 0; i < totalImages; i++) {
            ImageSliderPage.clickNextButton();
        }

        String finalImageAlt = ImageSliderPage.getCurrentActiveImageAltText();
        Assert.assertEquals(firstImageAlt, finalImageAlt,
                "After full cycle, slider should return to first image");
    }
}
