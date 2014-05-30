package com.gbt.gcm.app;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

/**
 * Created by kurt.yang on 2014/4/22.
 */
public class GcmBroadcastReceiver extends WakefulBroadcastReceiver  {
    @Override
    public void onReceive(Context context, Intent intent) {

        int a = getResultCode();

        String action = intent.getAction();

        // Explicitly specify that GcmIntentService will handle the intent.
        ComponentName comp = new ComponentName(context,
                GcmIntentService.class.getName());

        // Start the service, keeping the device awake while it is launching.
        startWakefulService(context, (intent.setComponent(comp)));

        //kurt add
        //intent.setAction("com.gbt.gcm.app.MainActivity");
       // intent.setAction("com.gbt.gcm.app.ListActivity");
        //send broadcast
       // LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

        setResultCode(Activity.RESULT_OK);
    }
}
