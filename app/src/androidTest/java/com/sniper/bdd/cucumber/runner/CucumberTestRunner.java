package com.sniper.bdd.cucumber.runner;

import android.os.Bundle;

import com.sniper.bdd.BuildConfig;

import androidx.test.runner.AndroidJUnitRunner;
import cucumber.api.android.CucumberInstrumentationCore;

@SuppressWarnings("unused")
public class CucumberTestRunner extends AndroidJUnitRunner {

    private static final String CUCUMBER_TAGS_KEY = "tags";
    private static final String CUCUMBER_SCENARIO_KEY = "name";
    private final CucumberInstrumentationCore instrumentationCore =
        new CucumberInstrumentationCore(this);

    @Override
    public void onCreate(final Bundle bundle) {
        String tags = BuildConfig.TEST_TAGS;
        String scenario = BuildConfig.TEST_SCENARIO;
        instrumentationCore.create(bundle);
        super.onCreate(bundle);
    }

    @Override
    public void onStart() {
        waitForIdleSync();
       instrumentationCore.start();
    }
}
