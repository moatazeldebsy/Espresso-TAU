package com.example.android.testing.espresso.IdlingResourceSample;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class ChangeTextTest {

    private static final String messageText = "Hello TAU";
    private IdlingResource mIdlingResource;

    @Rule public ActivityTestRule<MainActivity> activityTestRule
        = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResource() {
        mIdlingResource = activityTestRule.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(mIdlingResource);
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }

    @Test
    public void changeText_sameActivity() {
        onView(withId(R.id.editTextUserInput))
                .perform(typeText(messageText), closeSoftKeyboard());

        onView(withId(R.id.changeTextBt))
            .perform(click());

        onView(withId(R.id.textToBeChanged))
            .check(matches(withText(messageText)));
    }
}
