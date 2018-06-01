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

import java.util.ArrayList;

public class ClubHomeAdapter extends RecyclerView.Adapter<ClubHomeAdapter.ViewHolder> {
    private ArrayList<Event> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView,mDateView,mAcceptView;
        public ImageView mImage;
        public View everything;

        public ViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.name_event);
            mDateView = v.findViewById(R.id.date_event);
            mImage = v.findViewById(R.id.image_event);
            mAcceptView = v.findViewById(R.id.partecipant_event);
            everything=v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ClubHomeAdapter(ArrayList<Event> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ClubHomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
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
        holder.mTextView.setText(mDataset.get(position).getNome());
        holder.mDateView.setText(DateUtils.formatDateExtended(mDataset.get(position).data));
        holder.mImage.setImageDrawable(mDataset.get(position).getEventPicture());
        holder.mAcceptView.setText("Band partecipanti: "+mDataset.get(position).getAccettati().size());
        holder.everything.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), LoginActivity.class);
                i.putExtra("numberEvent", position);
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