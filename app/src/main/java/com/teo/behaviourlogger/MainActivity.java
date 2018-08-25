package com.teo.behaviourlogger;

import android.accessibilityservice.AccessibilityService;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.teo.behaviourlogger.service.ApiService;
import com.teo.behaviourlogger.service.SensorService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
/**
 * Created by teo on 19.04.2018.
 */

public class MainActivity extends AppCompatActivity {
    // set logged apps list here
    private List<String> loggedApps;
    //
    private Map<String, Integer> startTimes;
    private Map<String, Integer> endTimes;
    private Map<String, Integer> totalDurations;
    private Map<String, Integer> order;
    //
    Intent mServiceIntent;
    private SensorService mSensorService;
    private static ApiService apiService;
    //
    Context ctx;
    public Context getCtx() {
        return ctx;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = this;
        /* check if device is locked
        AccessibilityService context;
        KeyguardManager myKM = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        boolean isPhoneLocked = myKM.inKeyguardRestrictedInputMode();
        */

        final Handler handler = new Handler();
        final int delay = 2000; //milliseconds

        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);

        handler.postDelayed(new Runnable(){
            public void run(){
                String topPackageName ;
                KeyguardManager myKM = (KeyguardManager) getCtx().getSystemService(Context.KEYGUARD_SERVICE);
                boolean isPhoneLocked = myKM.inKeyguardRestrictedInputMode();

                if (isPhoneLocked)
                    Log.i("LOCKED","I'm locked dude! Waiting for you to unlock me...");
                else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        UsageStatsManager mUsageStatsManager = (UsageStatsManager) getCtx().getSystemService(Context.USAGE_STATS_SERVICE);
                        long time = System.currentTimeMillis();
                        // We get usage stats for the last 10 seconds
                        List<UsageStats> stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 1000 * 10, time);
                        // Sort the stats by the last time used
                        if (stats != null) {
                            SortedMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();
                            for (UsageStats usageStats : stats) {
                                mySortedMap.put(usageStats.getLastTimeUsed(), usageStats);
                            }
                            if (!mySortedMap.isEmpty()) {
                                topPackageName = mySortedMap.get(mySortedMap.lastKey()).getPackageName();
                                Log.i("RUN", topPackageName);
                            }
                        }
                    }
                }
                handler.postDelayed(this, delay);
            }
        }, delay);

        mSensorService = new SensorService(getCtx());
        mServiceIntent = new Intent(getCtx(), mSensorService.getClass());

//        if (!isMyServiceRunning(mSensorService.getClass())) {
//            startService(mServiceIntent);
//        }
    }

//    private boolean isMyServiceRunning(Class<?> serviceClass) {
//        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
//            if (serviceClass.getName().equals(service.service.getClassName())) {
//                Log.i ("isMyServiceRunning?", true+"");
//                return true;
//            }
//        }
//        Log.i ("isMyServiceRunning?", false+"");
//        return false;
//    }

    @Override
    protected void onDestroy() {
        stopService(mServiceIntent);
        Log.i("MAINACT", "onDestroy!");
        super.onDestroy();

    }
}
