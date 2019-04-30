package com.example.myapplication.services;
import android.app.Notification;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
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
        String lat = ""+Latitude;
        String longi = ""+Longitude;
        String rad = ""+radius;
        contentValues.put(myDbHelper.NAME, Name);
        contentValues.put(myDbHelper.LATITUDE, lat);
        contentValues.put(myDbHelper.LONGITUDE, longi);
        contentValues.put(myDbHelper.RADIUS, rad);
        long id = dbb.insert(myDbHelper.WAYPOINTS_TABLE, null, contentValues);
        dbb.close();
        return id;
    }
    public String getData()
    {
        SQLiteDatabase db = myHelper.getReadableDatabase();
        String[] columns = {myDbHelper.NAME, myDbHelper.LATITUDE, myDbHelper.LONGITUDE, myDbHelper.RADIUS};
        Cursor cursor = db.query(myDbHelper.WAYPOINTS_TABLE, columns, null,null,null,null,null);
        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext())
        {
           // int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name = cursor.getString(cursor.getColumnIndex("NAME"));
            String latitude = cursor.getString(cursor.getColumnIndex("LATITUDE"));
            String longitude = cursor.getString(cursor.getColumnIndex("LONGITUDE"));
            String radius = cursor.getString(cursor.getColumnIndex("RADIUS"));
            buffer.append(name+";"+latitude+";"+longitude+";"+radius+"\n");
        }
        cursor.close();
        return buffer.toString();
    }

    public int delete(String name){
        SQLiteDatabase db = myHelper.getWritableDatabase();
        String[] whereArgs = {name};
        int count = db.delete(myDbHelper.WAYPOINTS_TABLE, myDbHelper.NAME+" = ?",whereArgs);
        db.close();
        return count;
    }

    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "whereabouts";
        private static final String WAYPOINTS_TABLE = "waypoints";
        private static final int DATABASE_Version = 1;    // Database Version
       // private static String UID = "ID";
        private static String NAME = "NAME";
        private static String LATITUDE = "LATITUDE";
        private static String LONGITUDE = "LONGITUDE";
        private static String RADIUS = "RADIUS";
        private static final String CREATE_WAYPOINTS_TABLE = "CREATE TABLE "+WAYPOINTS_TABLE+
                //" ( ID INTEGER PRIMARY KEY AUTOINCREMENT, Name varchar(255), Latitude varchar(255), Longitude varchar(255), Radius int)";
                " ( NAME varchar(255), LATITUDE varchar(255), LONGITUDE varchar(255), RADIUS int)";
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
