package com.example.myapplication.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.WaypointCardViewAdapter;
import com.example.myapplication.models.Waypoint;

import java.util.ArrayList;

public class WaypointCardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waypoint_card);
    }

}
