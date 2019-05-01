package com.example.myapplication.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class NotificationUtility extends ContextWrapper {
    private NotificationManager mManager;
    public static final String GEOFENCE_NOTIFICATION_CHANNEL_ID = "com.example.myapplication.services.GeofenceTransition";
    public static final String GEOFENCE_NOTIFICATION_NAME = "GEOFENCE CHANNEL";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public NotificationUtility(Context base) {
        super(base);
        createChannels();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannels() {

        // create android channel
        NotificationChannel geofenceChannel = new NotificationChannel(GEOFENCE_NOTIFICATION_CHANNEL_ID,
                GEOFENCE_NOTIFICATION_NAME, NotificationManager.IMPORTANCE_HIGH);
        // Sets whether notifications posted to this channel should display notification lights
        geofenceChannel.enableLights(true);
        // Sets whether notification posted to this channel should vibrate.
        geofenceChannel.enableVibration(true);
        // Sets the notification light color for notifications posted to this channel
        geofenceChannel.setLightColor(Color.GREEN);
        // Sets whether notifications posted to this channel appear on the lockscreen or not
        geofenceChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

        getManager().createNotificationChannel(geofenceChannel);

    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getGeofenceChannelNotification(String title, String body) {
        return new Notification.Builder(getApplicationContext(), GEOFENCE_NOTIFICATION_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getPendingGeofenceChannelNotification(String title, String body, PendingIntent notificationPendingIntent){
        return new Notification.Builder(getApplicationContext(), GEOFENCE_NOTIFICATION_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setContentIntent(notificationPendingIntent)
                .setAutoCancel(true);
    }
    
}
