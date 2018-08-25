package com.teo.behaviourlogger;

/**
 * Created by teo on 04.05.2018.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.teo.behaviourlogger.service.SensorService;

public class SensorRestarterBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("stop", "well, i stopped and now i'm restarting");
        context.startService(new Intent(context, SensorService.class));;
    }
}