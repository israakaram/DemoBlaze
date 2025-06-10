package Test_suite;

import PAGES.Contact_us;
import Test_suite.Base_For_test.Base_before_TEST;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Contact_us_T extends Base_before_TEST {
 private Contact_us contactUs;
    @Test(priority = 1, description = "Submit contact form with valid data")
    public void testValidContactSubmission() {
        contactUs.fillContactForm(
                "test@example.com",
                "Mohamed Ahmed",
                "I need help with my recent order"
        );

        contactUs.submitForm();

        String alertText = contactUs.getAlertTextAndAccept();
        Assert.assertTrue(alertText.contains("Thanks"),
                "Expected success message but got: " + alertText);
    }

    @Test(priority = 2, description = "Submit form with invalid email")
    public void testInvalidEmail() {
        contactUs.fillContactForm(
                "invalid-email",
                "Ali Mahmoud",
                "Product inquiry"
        );

        contactUs.submitForm();

        String alertText = contactUs.getAlertTextAndAccept();
        Assert.assertTrue(alertText.contains("valid email"),
                "Expected email validation error but got: " + alertText);
    }

    @Test(priority = 3, description = "Submit form with empty message")
    public void testEmptyMessage() {
        contactUs.fillContactForm(
                "test@example.com",
                "Samira Said",
                ""
        );

        contactUs.submitForm();

        String alertText = contactUs.getAlertTextAndAccept();
        Assert.assertTrue(alertText.contains("message"),
                "Expected message validation error but got: " + alertText);
    }

}
