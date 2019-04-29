package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.services.MqttMessageService;
import com.example.myapplication.services.PahoMqttClient;
import com.example.myapplication.views.WaypointActivity;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttException;


import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
    private MqttAndroidClient client;
    private String TAG = "MainActivity";
    private PahoMqttClient pahoMqttClient;

    private EditText textMessage, subscribeTopic, unSubscribeTopic;
    private Button publishMessage, subscribe, unSubscribe, waypoints;

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
}
