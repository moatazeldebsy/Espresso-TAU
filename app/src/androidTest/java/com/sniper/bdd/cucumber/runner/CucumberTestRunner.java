package com.sniper.bdd.cucumber.runner;

import android.os.Bundle;
import com.sniper.bdd.BuildConfig;

import cucumber.api.android.CucumberAndroidJUnitRunner;

@SuppressWarnings("unused")
public class CucumberTestRunner extends CucumberAndroidJUnitRunner {

    private static final String CUCUMBER_TAGS_KEY = "tags";
    private static final String CUCUMBER_SCENARIO_KEY = "name";

    @Override
    public void onCreate(final Bundle bundle) {
        String tags = BuildConfig.TEST_TAGS;
        String scenario = BuildConfig.TEST_SCENARIO;
        super.onCreate(bundle);
    }
}
