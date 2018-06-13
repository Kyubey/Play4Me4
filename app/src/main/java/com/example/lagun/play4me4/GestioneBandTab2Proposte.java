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

public class GestioneBandTab2Proposte extends Fragment {
    GestioneAccettateAdapter adapter2;

    public GestioneBandTab2Proposte(){
        super();
    }

    @SuppressLint("ValidFragment")
    public GestioneBandTab2Proposte(GestioneAccettateAdapter adapter){
        super();
        this.adapter2=adapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Event event=ObjectFactory.getEventi().get(getActivity().getIntent().getIntExtra("numberEvent",-1));
        View rootView = inflater.inflate(R.layout.fragment_gestione_bands_tab2_proposte_layout, container, false);
        RecyclerView.Adapter adapter = new GestioneProposteAdapter(event.getProposti(),
                getActivity().getIntent().getIntExtra("numberEvent",-1),
                adapter2);

        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.content_proposte);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}
