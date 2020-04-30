package com.tau.drawersampleapplication;

import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerMatchers.isClosed;
import static androidx.test.espresso.contrib.DrawerMatchers.isOpen;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class DrawerActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> rule =
        new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void openAndCloseDrawer() {
        int drawerID = R.id.drawer_layout;

        onView(withId(drawerID))
            .perform(DrawerActions.open())
            .check(matches(isOpen()));

        onView(withText("Gallery")).perform(click());

        onView(withId(R.id.text_gallery))
            .check(matches(isDisplayed()));

        onView(withId(drawerID))
            .perform(DrawerActions.open())
            .check(matches(isOpen()));

        onView(withText("Home")).perform(click());

        onView(withId(drawerID))
            .perform(DrawerActions.close())
            .check(matches(isClosed()));
    }
}
