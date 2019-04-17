package com.example.myapplication.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.WaypointCardViewAdapter;
import com.example.myapplication.models.Waypoint;

import java.util.ArrayList;

public class WaypointActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waypoint);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new WaypointCardViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        ((WaypointCardViewAdapter) mAdapter).setOnItemClickListener(new WaypointCardViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
            }
        });
    }

    private ArrayList<Waypoint> getDataSet(){
        ArrayList results = new ArrayList<Waypoint>();
        for(int i = 0; i<6; i++){
            Waypoint obj = new Waypoint(41.266228, -95.837593, 150, "Home-"+i);
            results.add(i, obj);
        }
        return results;
    }
}
