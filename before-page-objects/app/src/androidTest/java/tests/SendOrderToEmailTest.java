package tests;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;


@RunWith(AndroidJUnit4.class)
public class SendOrderToEmailTest {

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
    public void shouldSendAnIntentContainingTheRightOrderDetails() {
        onView(withId(R.id.close_button))
                .perform(click());
        for (int i = 0; i < 4; i++) {
            onView(withText("+"))
                    .perform(click());
        }
        onView(withText("-"))
                .perform(click());
        onView(withId(R.id.chocolate))
                .perform(click());

        onView(withText("Review order"))
                .perform(click());
        onView(withId(R.id.name_text_box))
                .perform(scrollTo(), typeText("Moataz"));
        onView(withId(R.id.custom_order_name_box))
                .perform(scrollTo(), typeText("TAU Order Name"));
        onView(withId(R.id.mail_order_button))
                .perform(scrollTo(), click());

        intended(allOf(
                hasAction(equalTo(Intent.ACTION_SENDTO)),
                hasExtra(Intent.EXTRA_SUBJECT, "Order: Moataz - TAU Order Name")));
    }
}
