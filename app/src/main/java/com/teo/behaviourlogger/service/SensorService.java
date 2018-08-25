package com.teo.behaviourlogger.service;

/**
 * Created by teo on 04.05.2018.
 */

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.teo.behaviourlogger.SensorRestarterBroadcastReceiver;


public class SensorService extends Service {

    private SensorRestarterBroadcastReceiver sensorRestarterBroadcastReceiver;

    public SensorService(Context applicationContext) {
        super.onCreate();
    }

    public SensorService() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("START", "eu rulez");
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.setPriority(100);
        sensorRestarterBroadcastReceiver = new SensorRestarterBroadcastReceiver();
        registerReceiver(sensorRestarterBroadcastReceiver, intentFilter);

        Log.i("START", "baaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        final Handler handler = new Handler();
        final int delay = 1000; //milliseconds

        handler.postDelayed(new Runnable(){
            public void run(){
                Log.i("HERE", "here I am!");
                handler.postDelayed(this, delay);
            }
        }, delay);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("EXIT", "ondestroy!");
        Intent broadcastIntent = new Intent();
        sendBroadcast(broadcastIntent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}