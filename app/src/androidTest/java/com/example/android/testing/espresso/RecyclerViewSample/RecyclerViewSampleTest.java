package com.example.android.testing.espresso.RecyclerViewSample;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecyclerViewSampleTest {

    private static final int itemText = 40;
    private static final String displayedText = "This is element #"+ itemText;

    @Rule
    public ActivityScenarioRule<MainActivity> activityTestRule =
        new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void scrollToItem_checkItsText() {
        onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions
                    .actionOnItemAtPosition(itemText, click()));

        onView(withText(displayedText))
            .check(matches(isDisplayed()));
    }
}
