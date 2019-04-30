package com.example.myapplication;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.services.MqttMessageService;
import com.example.myapplication.services.PahoMqttClient;
import com.example.myapplication.views.WaypointActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttException;


import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    private MqttAndroidClient client;
    private static String TAG = MainActivity.class.getSimpleName();
    private PahoMqttClient pahoMqttClient;

    private EditText textMessage, subscribeTopic, unSubscribeTopic;
    private Button publishMessage, subscribe, unSubscribe, waypoints;
    //GoogleApi vars
    private GoogleApiClient googleApiClient;
    private Location lastLocation;
    private LocationCallback locationCallBack;
    private static final int REQ_PERMISSION = 35;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pahoMqttClient = new PahoMqttClient();
      //  private GeofencingClient geofencingClient;
        textMessage = (EditText) findViewById(R.id.publishText);
        publishMessage = (Button) findViewById(R.id.publishButton);

        waypoints = (Button) findViewById(R.id.newViewButton);

        subscribe = (Button) findViewById(R.id.subscribeButton);
        //unSubscribe = (Button) findViewById(R.id.unSubscribeButton);
        subscribeTopic = (EditText) findViewById(R.id.subscribeText);
        unSubscribeTopic = (EditText) findViewById(R.id.unSubscribeText);
        client = pahoMqttClient.getMqttClient(getApplicationContext(), Constants.MQTT_BROKER_URL, Constants.CLIENT_ID);

        publishMessage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String msg = textMessage.getText().toString().trim();
                if(!msg.isEmpty()){
                    try{
                        pahoMqttClient.publishMessage(client, msg, 1, Constants.PUBLISH_TOPIC);
                    } catch(MqttException e){
                        e.printStackTrace();
                    } catch(UnsupportedEncodingException e){
                        e.printStackTrace();
                    }
                }
            }

        });

        subscribe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                addWaypoint(v);
            }
        });

        locationCallBack = new LocationCallback() {
            @Override
                    public void onLocationResult(LocationResult locationResult){
                if(locationResult == null)
                    return;
                for (Location location : locationResult.getLocations()){
                    Log.i(TAG, "Location: Lat:"+location.getLatitude()+" | Long: "+location.getLongitude());
                }
            }
        };
        waypoints.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                addWaypoint(v);
            }
        });
        Intent intent = new Intent(MainActivity.this, MqttMessageService.class);
        startService(intent);
    }
    public void addWaypoint (View view){
        Intent intent = new Intent(this, WaypointActivity.class);
        startActivity(intent);
    }

    private void getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location != null){
                            Log.i(TAG, "LastKnown Location: "+
                                    "Lat: "+location.getLatitude()+
                                    " | Long: "+location.getLongitude());
                            startLocationUpdates();
                        } else {
                            Log.w(TAG, "No location retrieved yet");
                            startLocationUpdates();
                        }
                    }
                });
    }
    private LocationRequest locationRequest;
    private final int UPDATE_INTERVAL = 1000;
    private final int FASTEST_INTERVAL = 900;

    private void startLocationUpdates(){
        Log.i(TAG, "startLocationUpdates()");
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
            mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallBack, null);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult()");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch ( requestCode ) {
            case REQ_PERMISSION: {
                if ( grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                    // Permission granted
                    getLastKnownLocation();

                } else {
                    // Permission denied
                    permissionsDenied();
                }
                break;
            }
        }
    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i(TAG, "onConnected()");
        getLastKnownLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.w(TAG, "onConnectionSuspended()");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.w(TAG, "onConnectionFailed()");
    }

    @Override
    public void onLocationChanged(Location location) {

    }
    // App cannot work without the permissions
    private void permissionsDenied() {
        Log.w(TAG, "permissionsDenied()");
    }
}
