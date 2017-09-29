package com.rain.unittest.test;

import android.content.Intent;
import android.widget.Toast;

import com.rain.unittest.BuildConfig;
import com.rain.unittest.MyReceviver;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

/**
 * Created by HwanJ.Choi on 2017-9-29.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class ReceviverTest {

    @Test
    public void restReceive() throws Exception {

        String action = "com.rain.unittest.receiver";
        Intent intent = new Intent(action);
        intent.putExtra("user", "hwanj");

        MyReceviver myReceiver = new MyReceviver();
        //确认没弹
        Toast latestToast = ShadowToast.getLatestToast();
        Assert.assertNull(latestToast);
        //调用onreceive
        myReceiver.onReceive(RuntimeEnvironment.application, intent);
        //确认弹了，并且传递的字符串正确
        latestToast = ShadowToast.getLatestToast();
        Assert.assertNotNull(latestToast);
        Assert.assertEquals("hwanj", ShadowToast.getTextOfLatestToast());
    }
}
