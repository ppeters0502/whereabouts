package com.example.myapplication.services;
import android.app.Notification;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;
import android.widget.Toast;

import com.example.myapplication.models.Waypoint;


public class DBAdapter {
    myDbHelper myHelper;
    public DBAdapter(Context context){
        myHelper = new myDbHelper(context);
    }

    public long insertWaypoint(String Name, double Latitude, double Longitude, int radius)
    {
        SQLiteDatabase dbb = myHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, Name);
        contentValues.put(myDbHelper.LATITUDE, ""+Latitude);
        contentValues.put(myDbHelper.LONGITUDE, ""+Longitude);
        contentValues.put(myDbHelper.RADIUS, radius);
        long id = dbb.insert(myDbHelper.WAYPOINTS_TABLE, null, contentValues);
        return id;
    }


    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "whereabouts";
        private static final String WAYPOINTS_TABLE = "waypoints";
        private static final int DATABASE_Version = 1;    // Database Version
        private static String UID = "ID";
        private static String NAME = "Name";
        private static String LATITUDE = "0.0";
        private static String LONGITUDE = "0.0";
        private static String RADIUS = "0";
        private static final String CREATE_WAYPOINTS_TABLE = "CREATE TABLE "+WAYPOINTS_TABLE+
                " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, Name varchar(255), Latitude varchar(255), Longitude varchar(255), Radius int)";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS waypoints";
        private Context context;
        public myDbHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_Version);
        }

        public void onCreate(SQLiteDatabase db){
            try {
                db.execSQL(CREATE_WAYPOINTS_TABLE);
            } catch (Exception e){
               Toast.makeText(context, ""+e, Toast.LENGTH_LONG).show();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            try{
                Toast.makeText(context, "OnUpgrade", Toast.LENGTH_LONG).show();
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch(Exception e)
            {
                Toast.makeText(context, ""+e, Toast.LENGTH_LONG).show();
            }
        }
    }
}
