package tests;

import androidx.test.filters.MediumTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import pageobjects.MenuScreen;
import pageobjects.OnboardingScreen;
import utils.E2ETest;

@RunWith(AndroidJUnit4ClassRunner.class)
@MediumTest
public class OrderItemFromMenuTheTest extends TestBase {

    private OnboardingScreen onboardObject = new OnboardingScreen();
    private MenuScreen menuObject = new MenuScreen();
    private String selectedItem = "CAPPUCCINO";
    private String itemTitle = "Cappuccino";

    @Test
    @E2ETest
    public void selectAnItemInTheMenuWithPageObject() {
        onboardObject.closeOnBoardingScreen();
        menuObject.clickOnMenuButton();
        menuObject.scrollToMenuItem(selectedItem, itemTitle);
    }
}
