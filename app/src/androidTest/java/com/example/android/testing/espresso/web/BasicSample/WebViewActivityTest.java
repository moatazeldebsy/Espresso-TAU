package com.example.android.testing.espresso.web.BasicSample;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.content.Intent;

import androidx.test.espresso.web.webdriver.DriverAtoms;
import androidx.test.espresso.web.webdriver.Locator;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.web.assertion.WebViewAssertions.webMatches;
import static androidx.test.espresso.web.sugar.Web.onWebView;
import static androidx.test.espresso.web.webdriver.DriverAtoms.clearElement;
import static androidx.test.espresso.web.webdriver.DriverAtoms.findElement;
import static androidx.test.espresso.web.webdriver.DriverAtoms.getText;
import static androidx.test.espresso.web.webdriver.DriverAtoms.webClick;
import static org.hamcrest.Matchers.containsString;

@RunWith(AndroidJUnit4.class)
public class WebViewActivityTest {

    private static final String messageText = "TAU";

    @Rule
    public ActivityTestRule<WebViewActivity> mActivityRule = new ActivityTestRule<WebViewActivity>(
            WebViewActivity.class, false, false) {
        @Override
        protected void afterActivityLaunched() {
            onWebView().forceJavascriptEnabled();
        }
    };

    @Test
    public void typeTextInInput_clickButton_SubmitsForm() {
        Intent webFormIntent = new Intent();
        mActivityRule.launchActivity(webFormIntent);
        onWebView()
            .withElement(findElement(Locator.ID, "text_input"))
            .perform(clearElement())
            .perform(DriverAtoms.webKeys(messageText))

            .withElement(findElement(Locator.ID, "submitBtn"))
            .perform(webClick())

            .withElement(findElement(Locator.ID, "response"))
            .check(webMatches(getText(), containsString(messageText)));
    }
}
