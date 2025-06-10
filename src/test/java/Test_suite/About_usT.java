package Test_suite;

import PAGES.About_us;
import Test_suite.Base_For_test.Base_before_TEST;
import org.testng.Assert;
import org.testng.annotations.Test;

public class About_usT extends Base_before_TEST {
    @Test(priority = 1)
    public void testVideoModalFunctionality() {
        About_us aboutUsPage = new About_us(driver);

        aboutUsPage.openAboutUsModal();
        Assert.assertTrue(aboutUsPage.isVideoDisplayed(), "Video should be displayed in About Us modal");
        Assert.assertTrue(aboutUsPage.isCloseButtonDisplayed(), "Close button should be visible");
    }

    @Test(priority = 2)
    public void testCloseButtonFunctionality() {
        About_us aboutUsPage = new About_us(driver);
        aboutUsPage.openAboutUsModal();

        // Assert modal is open before closing
        Assert.assertTrue(aboutUsPage.isVideoDisplayed(), "Modal should be open before testing close");

        aboutUsPage.clickCloseButton();

        // Add wait and assertion for modal close
        Assert.assertFalse(aboutUsPage.isVideoDisplayed(), "Modal should close after clicking close button");
    }
}


