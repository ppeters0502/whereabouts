package com.example.myapplication.views;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.WayPointSelectionActivity;
import com.example.myapplication.models.Waypoint;
import com.example.myapplication.services.DBAdapter;

import java.util.ArrayList;

public class WaypointActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";
    private Button addWaypoint;
    private static ArrayList<Waypoint> results = new ArrayList<Waypoint>();
    public static final int REQUEST_CODE_GET_WAYPOINT = 1014;
    DBAdapter dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waypoint);
        dbHelper = new DBAdapter(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new WaypointCardViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);
        addWaypoint = (Button) findViewById(R.id.newWaypoint);
        addWaypoint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ addNewWaypoint(v); }
        });
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
        if(results.size()>0)
            results.clear();
        String data = dbHelper.getData();
        if(data != null && !data.isEmpty()) {
            String[] waypoints = data.split("\n");
            for (String w : waypoints) {
                String[] waypoint = w.split(";");
                String name = waypoint[0];
                double latitude = Double.parseDouble(waypoint[1]);
                double longitude = Double.parseDouble(waypoint[2]);
                int radius = Integer.parseInt(waypoint[3]);
                Waypoint obj = new Waypoint(name, latitude, longitude, radius);
                results.add(obj);

            }

        }
        else {
            Waypoint obj = new Waypoint("Test", 45.5555,45.55555,150);
            results.add(obj);
        }
        return results;
    }
    private void addNewWaypoint(View view){
        Intent intent = new Intent(this, WayPointSelectionActivity.class);
        //startActivity(intent);
        startActivityForResult(intent, REQUEST_CODE_GET_WAYPOINT);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch(requestCode){
            case REQUEST_CODE_GET_WAYPOINT:
                if(resultCode == Activity.RESULT_OK){
                    double latitude = data.getDoubleExtra("latitude", 0.0000000);
                    double longitude = data.getDoubleExtra("longitude", 0.0000000);
                    int radius = data.getIntExtra("radius", 100);
                    String name = data.getStringExtra("name");
                    int waypointCount = mAdapter.getItemCount();
                    Waypoint newWaypoint = new Waypoint(name, latitude, longitude, radius);
                    String msg = "Name: "+name+" Lat: "+latitude+" Long: "+longitude+" Radius: "+radius+"";
                    Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                    long result = dbHelper.insertWaypoint(name, latitude, longitude, radius);
                    Log.i("MyApp","Data inserted, result: "+result);
                    results.add(newWaypoint);
                    mAdapter.notifyItemInserted(results.size() - 1);

                } else {
                    Log.i("MyApp", "Activity Cancelled.");
                }

        }
    }


}
