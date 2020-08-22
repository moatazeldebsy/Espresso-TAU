package tests;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import nl.testchamber.mailordercoffeeshop.MainActivity;
import nl.testchamber.mailordercoffeeshop.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class OrderItemFromMenuTheTest {

    @Rule
    public IntentsTestRule<MainActivity> activityTestRule =
            new IntentsTestRule<MainActivity>(MainActivity.class) {
                @Override
                protected void beforeActivityLaunched() {
                    super.beforeActivityLaunched();
                    Context context = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();
                    SharedPreferences.Editor editor = context
                            .getSharedPreferences(context.getPackageName(),
                                    Activity.MODE_PRIVATE).edit();
                    editor.putBoolean("is_first_launch", true);
                    editor.commit();
                }
            };

    @Test
    public void shouldBeAbleToSelectAnItemInTheMenu() {
        onView(withId(R.id.close_button))
                .perform(click());

        onView(withId(R.id.use_menu))
                .perform(click());

        onView(withId(R.id.beverage_recycler_view))
                .perform(actionOnItem(hasDescendant(withText("CAPPUCCINO"))
                        , click()));

        onView(withId(R.id.beverage_detail_title))
                .check(matches(withText("Cappuccino")));
    }
}
