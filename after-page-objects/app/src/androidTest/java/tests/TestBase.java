package tests;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;

import nl.testchamber.mailordercoffeeshop.MainActivity;

class TestBase {

    @Rule
    public IntentsTestRule<MainActivity> activityTestRule =
            new IntentsTestRule<MainActivity>(MainActivity.class) {
                @Override
                public void beforeActivityLaunched() {
                    super.beforeActivityLaunched();
                    Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
                    SharedPreferences.Editor editor =
                            context.getSharedPreferences(context.getPackageName(),
                                    Activity.MODE_PRIVATE).edit();
                    editor.putBoolean("is_first_launch", true);
                    editor.commit();
                }
            };
}
