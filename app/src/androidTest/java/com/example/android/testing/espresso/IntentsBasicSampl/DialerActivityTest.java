package com.example.android.testing.espresso.IntentsBasicSampl;

import static android.app.Instrumentation.ActivityResult;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.example.android.testing.espresso.IntentsBasicSample.DialerActivity;
import com.example.android.testing.espresso.IntentsBasicSample.R;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DialerActivityTest {

    private static final String phoneNumber = "123-345-6789";
    private static final Uri IntentPhoneNumber = Uri.parse("tel:" + phoneNumber);

    @Rule
        public IntentsTestRule<DialerActivity> mActivityRule = new IntentsTestRule<>(
        DialerActivity.class);

    @Before
    public void stubAllExternalIntents() {
        intending(not(isInternal()))
            .respondWith(new ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void typeNumber_ValidInput_InitiatesCall() {
        onView(withId(R.id.edit_text_caller_number))
            .perform(typeText(phoneNumber), closeSoftKeyboard());
        onView(withId(R.id.button_call_number)).perform(click());

        intended(allOf(
            hasAction(Intent.ACTION_CALL),
            hasData(IntentPhoneNumber)));
    }
}
