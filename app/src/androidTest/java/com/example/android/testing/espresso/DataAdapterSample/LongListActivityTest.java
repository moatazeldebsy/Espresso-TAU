package com.example.android.testing.espresso.DataAdapterSample;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class LongListActivityTest {

    private static final String itemText = "item: 30";
    private static final String selectedItemText = "30";
    private static final String lastItemID = "item: 99";

    @Rule
    public ActivityScenarioRule<LongListActivity> rule =
        new ActivityScenarioRule<>(LongListActivity.class);

    @Test
    public void lastItemNotDisplayed() {
        onView(withText(lastItemID)).check(doesNotExist());
    }

    @Test
    public void scrollTest() {
        onData
            (hasEntry(equalTo(LongListActivity.ROW_TEXT), is(lastItemID)))
            .check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void rowClickTest() {
        onData
            (hasEntry(equalTo(LongListActivity.ROW_TEXT), is(itemText)))
            .onChildView(withId(R.id.rowContentTextView))
            .perform(click());

        onView(ViewMatchers.withId(R.id.selection_row_value))
            .check(matches(withText(selectedItemText)));
    }

    @Test
    public void toggleClickTest() {
        onData
            (hasEntry(equalTo(LongListActivity.ROW_TEXT), is(itemText)))
            .onChildView(withId(R.id.rowToggleButton))
            .perform(click());
        onData
            (hasEntry(equalTo(LongListActivity.ROW_TEXT), is(itemText)))
            .onChildView(withId(R.id.rowToggleButton))
            .check(matches(isChecked()));
    }
}
