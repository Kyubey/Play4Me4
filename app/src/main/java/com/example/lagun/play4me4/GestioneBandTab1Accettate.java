package com.example.lagun.play4me4;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lagun.play4me4.model.Event;
import com.example.lagun.play4me4.model.ObjectFactory;
import com.example.lagun.play4me4.model.User;

public class GestioneBandTab1Accettate extends Fragment {
    public static GestioneAccettateAdapter adapter;

    public GestioneBandTab1Accettate(){
        super();
    }

    @SuppressLint("ValidFragment")
    public GestioneBandTab1Accettate(GestioneAccettateAdapter adapter){
        super();
        this.adapter=adapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Event event=ObjectFactory.getEventi().get(getActivity().getIntent().getIntExtra("numberEvent",-1));
        View rootView = inflater.inflate(R.layout.fragment_gestione_bands_tab1_accettate_layout, container, false);
        //adapter = new GestioneAccettateAdapter(event.getAccettati(),getActivity().getIntent().getIntExtra("numberEvent",-1));

        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.content_accettate);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();
        if(adapter!=null)
            adapter.notifyDataSetChanged();
    }

}
