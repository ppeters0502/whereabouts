package com.example.myapplication.models;
import com.google.android.gms.location.Geofence;

public class Waypoint {
    //private final String requestId;
    private double Lat;
    private double Long;
    private int radius;
    private String WaypointName;

    //public Waypoint(String rID, double latitude, double longitude, int radius, String name)
    public Waypoint(String name, double latitude, double longitude, int radius)
    {
        this.WaypointName = name;
        this.Lat = latitude;
        this.Long = longitude;
        this.radius = radius;

    }
    public String getWaypointName(){
        return this.WaypointName;
    }
    public double getWaypointLongitude(){
        return this.Long;
    }
    public double getWaypointLatitude(){
        return this.Lat;
    }
    public int getWaypointRadius(){
        return this.radius;
    }
    public void setWaypointName(String name){
        this.WaypointName = name;
    }
    public void setLat(double l){
        this.Lat = l;

    }
    public void setLong(double lo){
        this.Long = lo;

    }
    public void setRadius(int r){
        this.radius = r;
    }

    public void saveWayPoint(){

    }



}
