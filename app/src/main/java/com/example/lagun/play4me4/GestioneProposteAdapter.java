package com.example.lagun.play4me4;


import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lagun.play4me4.model.DateUtils;
import com.example.lagun.play4me4.model.Event;
import com.example.lagun.play4me4.model.ObjectFactory;
import com.example.lagun.play4me4.model.User;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

public class GestioneProposteAdapter extends RecyclerView.Adapter<GestioneProposteAdapter.ViewHolder> {
    private ArrayList<User> mDataset;
    private Pair<Integer,Event> evento;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView mImage;
        public TextView mName;
        public Button mAccept;
        public Button mRefuse;

        public ViewHolder(View v) {
            super(v);
            mImage=v.findViewById(R.id.image_band);
            mName=v.findViewById(R.id.name_band);
            mAccept=v.findViewById(R.id.button_accept);
            mRefuse=v.findViewById(R.id.button_refuse);
            //mImage=v.findViewById(R.id.bands_part);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public  GestioneProposteAdapter(ArrayList<User> myDataset,int numberEvent) {
        mDataset = myDataset;
        evento = Pair.create(numberEvent, ObjectFactory.getEventi().get(numberEvent));
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GestioneProposteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                  int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_gestione_bands_proposte, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mImage.setImageDrawable(mDataset.get(position).getProPicture());
        holder.mName.setText(mDataset.get(position).getName());
        holder.mRefuse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                evento.second.rimuoviProposto(mDataset.get(position));
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount());
            }
        });

        holder.mAccept.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                evento.second.accettaNuovo(mDataset.get(position));
                evento.second.rimuoviProposto(mDataset.get(position));
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount());
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}