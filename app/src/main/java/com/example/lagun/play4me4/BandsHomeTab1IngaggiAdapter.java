package com.example.lagun.play4me4;


import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lagun.play4me4.model.DateUtils;
import com.example.lagun.play4me4.model.Event;
import com.example.lagun.play4me4.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BandsHomeTab1IngaggiAdapter extends RecyclerView.Adapter<BandsHomeTab1IngaggiAdapter.ViewHolder> {
    private HashMap<Integer,Map.Entry<Integer, Event>> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView mImage;
        public TextView mName;
        public TextView mDate;
        public TextView mPlace;
        public TextView mOrganizer;

        public ViewHolder(View v) {
            super(v);
            mImage=v.findViewById(R.id.image_event);
            mName=v.findViewById(R.id.name_event);
            mDate=v.findViewById(R.id.date_event);
            mPlace=v.findViewById(R.id.place_event);
            mOrganizer=v.findViewById(R.id.organizer_event);
            //mImage=v.findViewById(R.id.bands_part);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public  BandsHomeTab1IngaggiAdapter(HashMap<Integer,Event> myDataset)  {
        mDataset=new HashMap<>();
        Iterator<Map.Entry<Integer, Event>> iterator = myDataset.entrySet().iterator();
        int i=0;
        while(iterator.hasNext()) {
            mDataset.put(i++, iterator.next());
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public BandsHomeTab1IngaggiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_bands_home_ingaggi, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mImage.setImageDrawable(mDataset.get(position).getValue().getEventPicture());
        holder.mName.setText(mDataset.get(position).getValue().getNome());
        holder.mPlace.setText(mDataset.get(position).getValue().getStringPlace());
        holder.mDate.setText((new SimpleDateFormat("dd/MM/yyyy").format(mDataset.get(position).getValue().data.getTime()).split("/")[1])+" "+DateUtils.getMese(new SimpleDateFormat("dd/MM/yyyy").format(mDataset.get(position).getValue().data.getTime()).split("/")[0], 1)+" "+ DateUtils.formatTime(mDataset.get(position).getValue().data));
        holder.mOrganizer.setText(mDataset.get(position).getValue().getOwner().getName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}