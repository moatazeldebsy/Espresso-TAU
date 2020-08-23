package tests;

import androidx.test.filters.SmallTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import pageobjects.CustomOrderScreen;
import pageobjects.OnboardingScreen;
import pageobjects.ReviewOrderScreen;

@RunWith(AndroidJUnit4ClassRunner.class)
@SmallTest
public class CreateCustomOderWithIngredientsTest extends TestBase {

    private OnboardingScreen onboardObject = new OnboardingScreen();
    private CustomOrderScreen customizeObject = new CustomOrderScreen();
    private ReviewOrderScreen reviewObject = new ReviewOrderScreen();

    // After Page Object
    @Test
    public void orderOverViewDisplayIngredients() {
        onboardObject.closeOnBoardingScreen();
        customizeObject.customizeYourOrder(3);
        reviewObject.checkIngredients(2);
    }
}
