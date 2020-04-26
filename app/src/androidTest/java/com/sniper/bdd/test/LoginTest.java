package com.sniper.bdd.test;
import com.sniper.bdd.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.sniper.bdd.pageobjects.LoginScreen;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;
import androidx.test.rule.ActivityTestRule;


@RunWith(AndroidJUnit4.class)
@SmallTest
public class LoginTest
{
    private LoginScreen loginObject = new LoginScreen();
    @Rule
    public ActivityTestRule activityRule =
        new ActivityTestRule<>(LoginActivity.class);

    private final String email = "test1@test.com";
    private final String password = "123456789";

    @Test
    public void testUserLogin()
    {
        loginObject.enterEmail(email);
        loginObject.enterPassword(password);
        loginObject.closeKeyboard();
        loginObject.clickSignInButton();
        loginObject.isSuccessfulLogin();
    }
}
