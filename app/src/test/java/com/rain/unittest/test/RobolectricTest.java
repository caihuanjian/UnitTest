package com.rain.unittest.test;

import android.content.Intent;
import android.os.Bundle;

import com.rain.unittest.BuildConfig;
import com.rain.unittest.MainActivity;
import com.rain.unittest.R;
import com.rain.unittest.SecondActivity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.util.ActivityController;

import static junit.framework.Assert.assertEquals;

/**
 * Created by HwanJ.Choi on 2017-9-28.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class RobolectricTest {

    @Test
    public void testJump() {
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        mainActivity.findViewById(R.id.btn).performClick();

        ShadowActivity shadowActivity = Shadows.shadowOf(mainActivity);
        final Intent nextStartedActivity = shadowActivity.getNextStartedActivity();
        Assert.assertEquals(nextStartedActivity.getComponent().getClassName(), SecondActivity.class);

    }

    @Test
    public void testLifeCycle() {
        final ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
        final MainActivity mainActivity = controller.get();
        controller.create();
        Assert.assertTrue(mainActivity.isActivityCreate());
    }

    @Test
    public void testStartActivityWithIntent() throws Exception {
        Intent intent = new Intent();
        intent.putExtra("test", "HelloWorld");
        final MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).withIntent(intent).create().get();
        assertEquals("HelloWorld", mainActivity.getIntent().getExtras().getString("test"));
    }

    @Test
    public void testSavedInstanceState() throws Exception {
        Bundle savedInstanceState = new Bundle();
        savedInstanceState.putString("state","rain");
        final ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
        final MainActivity mainActivity = controller.create().restoreInstanceState(savedInstanceState).get();
        assertEquals("rain",mainActivity.getState());
    }
}
