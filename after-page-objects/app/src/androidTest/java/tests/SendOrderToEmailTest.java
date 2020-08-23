package tests;

import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import pageobjects.CustomOrderScreen;
import pageobjects.OnboardingScreen;
import pageobjects.ReviewOrderScreen;

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class SendOrderToEmailTest extends TestBase {

    private OnboardingScreen onboardObject = new OnboardingScreen();
    private CustomOrderScreen customizeObject = new CustomOrderScreen();
    private ReviewOrderScreen reviewObject = new ReviewOrderScreen();

    private String name = "Moataz";
    private String orderName = "TAU Order Name";
    private String mailSubject = "Order: Moataz - TAU Order Name";

    @Test
    public void sendAnIntentContainingTheRightOrderDetails() {
        onboardObject.closeOnBoardingScreen();
        customizeObject.customizeYourOrder(4);
        reviewObject.reviewOrder(name, orderName, mailSubject);
    }
}
