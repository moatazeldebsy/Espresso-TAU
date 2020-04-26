package com.sniper.bdd.pageobjects;

import com.sniper.bdd.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class LoginScreen {

    private final int emailText = R.id.email ;
    private final int passwordText = R.id.password;
    private final int loginButton = R.id.email_sign_in_button;
    private final int successfulLoginTextView = R.id.successful_login_text_view;
    private final int successfulLogin = R.string.successful_login;

    public void enterEmail(String email) {
        onView(withId(emailText))
            .perform(typeText(email));
    }

    public void enterPassword(String password) {
        onView(withId(passwordText))
            .perform(typeText(password));
    }

    public void closeKeyboard() {
        closeSoftKeyboard();
    }

    public void clickSignInButton() {
        onView(withId(loginButton))
            .perform(click());
    }
    public void isSuccessfulLogin() {
        onView(withId(successfulLoginTextView))
            .check(matches(isDisplayed()))
            .check(matches(withText(successfulLogin)));
    }
}
