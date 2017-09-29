package com.rain.unittest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by HwanJ.Choi on 2017-9-29.
 */

public class MyReceviver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getStringExtra("user"), Toast.LENGTH_SHORT).show();
    }
}
