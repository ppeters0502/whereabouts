package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import org.eclipse.paho.android.service.MqttAndroidClient;

public class MainActivity extends AppCompatActivity {
    private MqttAndroidClient client;
    private String TAG = "MainActivity";
    private PahoMqttClient pahoMqttClient;

    private EditText textMesage, subscribeTopic, unSubscribeTopic;
    private Button publishMessage, subscribe, unSubscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pahoMqttClient = new PahoMqttClient();

        textMesage = (EditText) findViewById(R.id.publishText);
        publishMessage = (Button) findViewById(R.id.publishButton);

        subscribe = (Button) findViewById(R.id.subscribeButton);
        unSubscribe = (Button) findViewById(R.id.unSubscribeButton);

        subscribeTopic = (EditText) findViewById(R.id.subscribeText);
        unSubscribeTopic = (EditText) findViewById(R.id.unSubscribeText);
        client = pahoMqttClient.getMqttClient(getApplicationContext(), Constants.MQTT_BROKER_URL, Constants.CLIENT_ID);
        
    }
}
