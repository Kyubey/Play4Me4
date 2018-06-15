package com.example.lagun.play4me4;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lagun.play4me4.model.DateUtils;
import com.example.lagun.play4me4.model.Event;
import com.example.lagun.play4me4.model.Notify;
import com.example.lagun.play4me4.model.ObjectFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private ArrayList<Notify> mDataset;
    private int numNewNotifiche;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ImageView mImage;
        public ImageButton mStatus;
        public View everything;

        public ViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.notification_description);
            mImage = v.findViewById(R.id.image_event);
            mStatus= v.findViewById(R.id.notification_type);
            everything=v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public NotificationAdapter(ArrayList<Notify> myNewNotifiche, ArrayList<Notify> myOldNotifiche) {
        mDataset=new ArrayList<Notify>();
        numNewNotifiche=0;
        for(Notify notify:myNewNotifiche){
            mDataset.add(notify);
        numNewNotifiche++;}

        for(Notify notify:myOldNotifiche)
            mDataset.add(notify);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_club_home, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position).getContenuto());
        holder.mImage.setImageDrawable(ObjectFactory.getEventi().get(mDataset.get(position).getNumberEvent()).getEventPicture() );

        holder.everything.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), EventPageActivity.class);
                i.putExtra("numberEvent", mDataset.get(position).getNumberEvent());
                view.getContext().startActivity(i);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}