package com.sniper.bdd.test;
import com.applitools.eyes.android.espresso.Eyes;
import com.sniper.bdd.LoginActivity;
import com.sniper.bdd.pageobjects.LoginScreen;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import androidx.test.filters.SmallTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;


@RunWith(AndroidJUnit4.class)
@SmallTest
public class LoginTest {
    private LoginScreen loginObject = new LoginScreen();
    private Eyes eyes;

    @Rule
    public ActivityTestRule activityRule =
        new ActivityTestRule<>(LoginActivity.class);

    private final String email = "test1@test.com";
    private final String password = "123456789";

    @Before
    public void setup() {
        eyes = new Eyes();
        eyes.setApiKey("tYKhJE1i7SnPtUU79899sJwSAy931rSNZd77KxptMM1VI110");
    }

    @Test
    public void testUserLogin() {
        try {
            eyes.open("Hello Espresso", "First Espresso Test");
            eyes.checkWindow("Login Screen");
            loginObject.enterEmail(email);
            loginObject.enterPassword(password);
            loginObject.closeKeyboard();
            loginObject.clickSignInButton();
            loginObject.isSuccessfulLogin();
            eyes.checkWindow("Click!");
            eyes.close();
        } finally {
            eyes.abortIfNotClosed();
        }
    }

}
