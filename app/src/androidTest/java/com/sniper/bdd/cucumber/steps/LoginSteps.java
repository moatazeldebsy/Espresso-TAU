package com.sniper.bdd.cucumber.steps;

import android.content.Intent;

import com.sniper.bdd.LoginActivity;

import androidx.test.rule.ActivityTestRule;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import com.sniper.bdd.pageobjects.LoginScreen;
import com.sniper.bdd.utils.ActivityFinisher;


public class LoginSteps {

    private LoginScreen loginObject = new LoginScreen();

    ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(LoginActivity.class,
        false, false);

    @After
    public void tearDown() {
        ActivityFinisher.finishOpenActivities();
    }

    @Given("I start the application")
    public void iStartTheApplication() {
        mActivityRule.launchActivity(new Intent());
    }

    @When("I enter valid email (\\S+)$")
    public void iEnterValidEmailEmail(String email) {
        loginObject.enterEmail(email);
    }

    @And("I enter valid password (\\S+)$")
    public void iEnterValidPasswordPassword(String password) {
        loginObject.enterPassword(password);
    }

    @And("I close the keyboard")
    public void iCloseTheKeyboard() {
        loginObject.clickSignInButton();
    }

    @And("I click sign in button")
    public void iClickSignInButton() {
        loginObject.clickSignInButton();
    }

    @Then("I expect to see successful login message")
    public void iExpectToSeeSuccessfulLoginMessage() {
        loginObject.isSuccessfulLogin();
    }
}
