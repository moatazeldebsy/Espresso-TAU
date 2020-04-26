package com.sniper.bdd.cucumber.steps;

import android.content.Intent;

import com.applitools.eyes.android.espresso.Eyes;
import com.sniper.bdd.LoginActivity;
import com.sniper.bdd.pageobjects.LoginScreen;
import com.sniper.bdd.utils.ActivityFinisher;

import androidx.test.rule.ActivityTestRule;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class LoginSteps {

    ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(LoginActivity.class,
        false, false);
    private LoginScreen loginObject = new LoginScreen();
    private Eyes eyes;

    @Before
    public void setup() {
        eyes = new Eyes();
        eyes.setApiKey("tYKhJE1i7SnPtUU79899sJwSAy931rSNZd77KxptMM1VI110");
    }
    @After
    public void tearDown() {
        ActivityFinisher.finishOpenActivities();
    }

    @Given("^I start the application$")
    public void i_start_app() {
        mActivityRule.launchActivity(new Intent());
        eyes.open("Espresso Login", "First Espresso Test");
    }

    @When("^I enter valid email (\\S+)$")
    public void i_enter_valid_email(String email ) {
        eyes.checkWindow("Login Screen");
        loginObject.enterEmail(email);
    }

    @And("^I enter valid password (\\S+)$")
    public void i_enter_valid_password(String password) {

        loginObject.enterPassword(password);
    }

    @And("^I close the keyboard$")
    public void iCloseTheKeyboard() {
        loginObject.closeKeyboard();
    }

    @And("^I click sign in button$")
    public void i_click_sign_in_button() {
        loginObject.clickSignInButton();
    }

    @Then("^I expect to see successful login message$")
    public void i_expect_to_see_successful_login_message() {
        try {
            loginObject.isSuccessfulLogin();
            eyes.checkWindow("Login Click!");
            eyes.close();
        } finally {
            eyes.abortIfNotClosed();
        }
    }
}
