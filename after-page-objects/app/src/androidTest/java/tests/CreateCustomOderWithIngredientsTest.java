package tests;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.SmallTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;
import nl.testchamber.mailordercoffeeshop.MainActivity;
import nl.testchamber.mailordercoffeeshop.R;
import pageobjects.CustomOrderScreen;
import pageobjects.OnboardingScreen;
import pageobjects.ReviewOrderScreen;
import utils.SmokeTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4ClassRunner.class)
@SmallTest
public class CreateCustomOderWithIngredientsTest {

    private OnboardingScreen onboardObject = new OnboardingScreen();
    private CustomOrderScreen customizeObject = new CustomOrderScreen();
    private ReviewOrderScreen reviewObject = new ReviewOrderScreen();

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
    @SmokeTest
    public void orderOverViewShouldDisplayIngredients() {
         onView(ViewMatchers.withId(R.id.close_button))
             .perform(click());
        onView(withText("+"))
            .perform(click(), click());
        onView(withId(R.id.chocolate))
            .perform(click());
        onView(withText(R.string.review_order_button))
            .perform(click());
        onView(withId(R.id.beverage_detail_ingredients))
            .check(matches(withText("Ingredients:\n2 shots of espresso\nChocolate")));
    }

    // After Page Object
    @Test
    public void orderOverViewDisplayIngredients()
    {
        onboardObject.closeOnBoardingScreen();
        customizeObject.customizeYourOrder(3);
        reviewObject.checkIngredients(2);
    }
}
