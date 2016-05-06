package com.example.b00047562.skyassistant;

/**
 * Created by Administrator on 5/6/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.model.InitializationError;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import java.io.File;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;


@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class MainActivityTest  {
    private MainActivity activity;
    private SkyAssitantParseApp app;

    // @Before => JUnit 4 annotation that specifies this method should run before each test is run
    // Useful to do setup for objects that are needed in the test
    @Before
    public void setup() {
        // Convenience method to run MainActivity through the Activity Lifecycle methods:
        // onCreate(...) => onStart() => onPostCreate(...) => onResume()
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    // @Test => JUnit 4 annotation specifying this is a test to be run
    // The test simply checks that our TextView exists and has the text "Hello world!"
    @Test
    public void validateButtonContent() {
        Button prefs = (Button) activity.findViewById(R.id.btn_prefs);
        assertNotNull("TextView could not be found", prefs);
        assertTrue("TextView contains incorrect text",
                "Set Up Schedule".equals(prefs.getText().toString()));

        Button airline = (Button) activity.findViewById(R.id.btn_airlineview);
        assertNotNull("TextView could not be found", airline);
        assertTrue("TextView contains incorrect text",
                "Airline Information".equals(airline.getText().toString()));

//
        Button myRes = (Button) activity.findViewById(R.id.btn_myRes);
        assertNotNull("TextView could not be found", myRes);
        assertTrue("TextView contains incorrect text",
                "My Reservations".equals(myRes.getText().toString()));


        Button sugg = (Button) activity.findViewById(R.id.btn_suggestions);
        assertNotNull("TextView could not be found", sugg);
        assertTrue("TextView contains incorrect text",
                "View More".equals(sugg.getText().toString()));
    }
    @Test
    public void UserPrefsActivityStartedOnClick() {
        activity.findViewById(R.id.btn_prefs).performClick();
        Intent expectedIntent = new Intent(activity, UserPreferences.class);
        assertThat(Shadows.shadowOf(activity).getNextStartedActivity()).isEqualTo(expectedIntent);
    }
    @Test
    public void airlineInfoActivityStartedOnClick() {

        activity.findViewById(R.id.btn_airlineview).performClick();
        Intent expectedIntent = new Intent(activity, AirlineView.class);
        assertThat(Shadows.shadowOf(activity).getNextStartedActivity()).isEqualTo(expectedIntent);
    }
    @Test
    public void viewAllActivityStartedOnClick() {

        activity.findViewById(R.id.btn_suggestions).performClick();
        Intent expectedIntent = new Intent(activity, Suggestions.class);
        assertThat(Shadows.shadowOf(activity).getNextStartedActivity()).isEqualTo(expectedIntent);
    }
    @Test
    public void MainActivityLifeCycle() {
        activity = Robolectric.buildActivity(MainActivity.class).create().start().resume().visible().get();
        Assert.assertNotNull(activity);
    }


}