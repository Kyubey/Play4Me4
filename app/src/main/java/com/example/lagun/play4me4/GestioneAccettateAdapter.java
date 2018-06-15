package com.example.lagun.play4me4;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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

import java.util.ArrayList;

public class GestioneAccettateAdapter extends RecyclerView.Adapter<GestioneAccettateAdapter.ViewHolder> {
    private ArrayList<User> mDataset;
    private Pair<Integer,Event> evento;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView mImage;
        public TextView mName;
        public Button mLicenzia;

        public ViewHolder(View v) {
            super(v);
            mImage=v.findViewById(R.id.image_band);
            mName=v.findViewById(R.id.name_band);
            mLicenzia=v.findViewById(R.id.button_licenzia);
            //mImage=v.findViewById(R.id.bands_part);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public  GestioneAccettateAdapter(ArrayList<User> myDataset,int numberEvent) {
        mDataset = myDataset;
        evento = Pair.create(numberEvent, ObjectFactory.getEventi().get(numberEvent));
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GestioneAccettateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_gestione_bands_accettate, parent, false);

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
        holder.mLicenzia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setCancelable(true);
                builder.setTitle("Title");
                builder.setMessage("Message");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                evento.second.rimuoviAccettato(mDataset.get(position),evento.first);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, getItemCount());
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}