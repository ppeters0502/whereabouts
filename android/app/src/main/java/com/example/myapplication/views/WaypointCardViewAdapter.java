package com.example.myapplication.views;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.models.Waypoint;

import java.util.ArrayList;


public class WaypointCardViewAdapter extends RecyclerView
        .Adapter<WaypointCardViewAdapter
        .WaypointHolder> {
    private static String LOG_TAG = "MyWaypointCardViewAdapter";
    private ArrayList<Waypoint> mDataset;
    private static MyClickListener myClickListener;

    public static class WaypointHolder extends RecyclerView.ViewHolder
        implements View
        .OnClickListener {
        TextView Name;
        TextView Location;

        public WaypointHolder(View itemView){
            super(itemView);
            Name = (TextView) itemView.findViewById(R.id.WaypointName);
            Location = (TextView) itemView.findViewById(R.id.WayPointLocation);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener){
        this.myClickListener = myClickListener;
    }
    public WaypointCardViewAdapter(ArrayList<Waypoint> myDataset){
        mDataset = myDataset;
    }

    @Override
    public WaypointHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_waypoint_card, parent, false);
        WaypointHolder waypointHolder = new WaypointHolder(view);
        return waypointHolder;
    }

    @Override
    public void onBindViewHolder(WaypointHolder holder, int position) {
        holder.Name.setText(mDataset.get(position).getWaypointName());
        holder.Location.setText("Lat: "+mDataset.get(position).getWaypointLatitude()
                + " Long: "+mDataset.get(position).getWaypointLongitude() + " Radius: "+mDataset.get(position).getWaypointRadius());

    }

    public void addItem(Waypoint wp, int index) {
        mDataset.add(index, wp);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
