package com.tau.toastdemo;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MainActivityTest
{
    private static final String toastMessage = "Hello from Test Automation University";

    @Rule
    public ActivityScenarioRule<MainActivity> rule =
        new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testToastMessage()
    {
        onView(withId(R.id.buttonToast))
            .perform(click());

        onView(withText((toastMessage)))
            .inRoot(new ToastMatcher())
            .check(matches(isDisplayed()));
    }
}
