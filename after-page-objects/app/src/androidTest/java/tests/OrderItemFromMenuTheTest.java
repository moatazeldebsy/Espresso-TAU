package tests;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.MediumTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;
import nl.testchamber.mailordercoffeeshop.MainActivity;
import nl.testchamber.mailordercoffeeshop.R;
import pageobjects.MenuScreen;
import pageobjects.OnboardingScreen;
import utils.E2ETest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4ClassRunner.class)
@MediumTest
public class OrderItemFromMenuTheTest {

    private OnboardingScreen onboardObject = new OnboardingScreen();
    private MenuScreen menuObject = new MenuScreen();
    private String selectedItem = "CAPPUCCINO";
    private String itemTitle = "Cappuccino";

    @Rule
    public IntentsTestRule<MainActivity> activityTestRule = new IntentsTestRule<MainActivity>(MainActivity.class) {
        @Override
        public void beforeActivityLaunched() {
            super.beforeActivityLaunched();
            Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
            SharedPreferences.Editor editor = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE).edit();
            editor.putBoolean("is_first_launch", true);
            editor.commit();
        }
    };

    // Before Page Object
    @Test
    @E2ETest
    public void shouldBeAbleToSelectAnItemInTheMenu() {
        onView(ViewMatchers.withId(R.id.close_button)).perform(click());
        onView(withId(R.id.use_menu)).perform(click());
        onView(withId(R.id.beverage_recycler_view))
            .perform(RecyclerViewActions.
                actionOnItem(hasDescendant(withText("CAPPUCCINO")), click()));
        onView(withId(R.id.beverage_detail_title)).check(matches(withText("Cappuccino")));
    }

    @Test
    public void selectAnItemInTheMenuWithPageObject() {
        onboardObject.closeOnBoardingScreen();
        menuObject.clickOnMenuButton();
        menuObject.scrollToMenuItem(selectedItem, itemTitle);
    }
}
