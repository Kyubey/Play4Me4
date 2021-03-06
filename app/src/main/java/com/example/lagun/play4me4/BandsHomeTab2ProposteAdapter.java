package com.example.lagun.play4me4;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lagun.play4me4.model.DateUtils;
import com.example.lagun.play4me4.model.Event;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BandsHomeTab2ProposteAdapter extends RecyclerView.Adapter<BandsHomeTab2ProposteAdapter.ViewHolder> {
    private HashMap<Integer,Map.Entry<Integer, Event>> mDataset;
    private int numberProposes=0;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView mImage;
        public TextView mName;
        public TextView mDate;
        public TextView mOrganizer;
        public TextView mPlace;
        public View mProp;
        public View mProposedIcon;

        public ViewHolder(View v) {
            super(v);
            mImage=v.findViewById(R.id.image_event);
            mName=v.findViewById(R.id.name_event);
            mDate=v.findViewById(R.id.date_event);
            mOrganizer=v.findViewById(R.id.organizer_event);
            mPlace=v.findViewById(R.id.place_event);
            mProp=v.findViewById(R.id.prop);
            mProposedIcon=v.findViewById(R.id.notification_type);
            //mImage=v.findViewById(R.id.bands_part);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)

    public  BandsHomeTab2ProposteAdapter(HashMap<Integer,Event> myDataset, HashMap<Integer,Event> myNewEventsDataset)  {
        mDataset=new HashMap<>();
        Iterator<Map.Entry<Integer, Event>> iterator = myDataset.entrySet().iterator();
        Iterator<Map.Entry<Integer, Event>> iteratorNewEvents = myNewEventsDataset.entrySet().iterator();
        boolean checkIfPresented=false;
        numberProposes=0;
        int i=0;
        while(iterator.hasNext()) {
            mDataset.put(i++, iterator.next());
            numberProposes=i;
        }
        while(iteratorNewEvents.hasNext()){
            mDataset.put(i++, iteratorNewEvents.next());
        }
    }


    static <T, S> boolean equals(Iterator<T> a, Iterator<S> b) {
            if (!a.next().equals(b.next())) {
                return false;
            }
        return true;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public BandsHomeTab2ProposteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                      int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_bands_home_proposte, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        if(numberProposes>position)
            holder.mProposedIcon.setVisibility(View.VISIBLE);
        else
            holder.mProposedIcon.setVisibility(View.INVISIBLE);

        holder.mImage.setImageDrawable(mDataset.get(position).getValue().getEventPicture());
        holder.mName.setText(mDataset.get(position).getValue().getNome());
        holder.mPlace.setText(mDataset.get(position).getValue().getStringPlace());
        holder.mDate.setText((new SimpleDateFormat("dd/MM/yyyy").format(mDataset.get(position).getValue().getData().getTime()).split("/")[0])+" "+DateUtils.getMese(new SimpleDateFormat("dd/MM/yyyy").format(mDataset.get(position).getValue().getData().getTime()).split("/")[1], 1)+" "+ DateUtils.formatTime(mDataset.get(position).getValue().getData()));
        holder.mOrganizer.setText(mDataset.get(position).getValue().getOwner().getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(view.getContext(),EventPageActivityBand.class);
                i.putExtra("numberEvent",mDataset.get(position).getKey());
                view.getContext().startActivity(i);
            }
        });
        holder.mOrganizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(view.getContext(),UserProfileActivity.class);
                i.putExtra("userMail",mDataset.get(position).getValue().getOwner().getMail());
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