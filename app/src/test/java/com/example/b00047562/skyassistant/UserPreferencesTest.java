package com.example.b00047562.skyassistant;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Administrator on 5/6/2016.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class UserPreferencesTest {
    private UserPreferences activity;


    // @Before => JUnit 4 annotation that specifies this method should run before each test is run
    // Useful to do setup for objects that are needed in the test
    @Before
    public void setup() {
        // Convenience method to run MainActivity through the Activity Lifecycle methods:
        // onCreate(...) => onStart() => onPostCreate(...) => onResume()
        activity = Robolectric.setupActivity(UserPreferences.class);
    }

    // @Test => JUnit 4 annotation specifying this is a test to be run
    // The test simply checks that our TextView exists and has the text "Hello world!"
    @Test
    public void validateButtonContent() {
        Button addevent = (Button) activity.findViewById(R.id.btn_addevent);
        assertNotNull("View could not be found", addevent);
        assertTrue("View contains incorrect text",
                "Add to Schedule".equals(addevent.getText().toString()));

        Button mysched = (Button) activity.findViewById(R.id.btn_submit);
        assertNotNull("View could not be found", mysched);
        assertTrue("View contains incorrect text",
                "My Schedule".equals(mysched.getText().toString()));
    }

    @Test
    public void UserPrefsActivityStartedOnClick() {
        activity.findViewById(R.id.btn_addevent).performClick();
        Intent expectedIntent = new Intent(activity, AddeventDialogue.class);
        assertThat(Shadows.shadowOf(activity).getNextStartedActivity()).isEqualTo(expectedIntent);
    }

    @Test
    public void airlineInfoActivityStartedOnClick() {

        activity.findViewById(R.id.btn_submit).performClick();
        Intent expectedIntent = new Intent(activity, mySchedule.class);
        assertThat(Shadows.shadowOf(activity).getNextStartedActivity()).isEqualTo(expectedIntent);
    }

    @Test
    public void UserPrefsActivityLifeCycle() {
        activity = Robolectric.buildActivity(UserPreferences.class).create().start().resume().visible().get();
        Assert.assertNotNull(activity);
    }
}