package com.example.myapplication.services;

import android.app.IntentService;
import android.content.Intent;


public class GeofenceTransitionsIntentService extends IntentService {
    @Override
    protected void onHandleIntent(Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            String errorMessage = GeofenceErrorMessages.getErrorString(this,
                    geofencingEvent.getErrorCode());
            Log.e(TAG, errorMessage);
            return;

    }
    public GeofenceTransitionsIntentService(){}
}
