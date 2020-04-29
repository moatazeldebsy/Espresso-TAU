package com.example.android.testing.espresso.BasicSample;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class ChangeTextBehaviorTest {

    @Rule public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void changeText_sameActivity() {
       // editTextUserInput
        // changeTextBt
        // textToBeChanged

        // Locate the element
        // perform action on this element
        // assert/verify the element state

        onView(withId(R.id.editTextUserInput))
            .perform(typeText("Hello TAU"));
        closeSoftKeyboard();
        onView(withId(R.id.changeTextBt))
            .perform(click());
        onView(withId(R.id.textToBeChanged))
            .check(matches(withText("Hello TAU")));
    }
}
