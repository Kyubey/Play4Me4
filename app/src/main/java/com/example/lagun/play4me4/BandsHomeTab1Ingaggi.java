package com.example.lagun.play4me4;

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

public class BandsHomeTab1Ingaggi extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        User band=ObjectFactory.getLoggedUser(getContext());
        View rootView = inflater.inflate(R.layout.fragment_bands_home_ingaggi_layout, container, false);
        RecyclerView.Adapter adapter = new BandsHomeTab1IngaggiAdapter(ObjectFactory.getEventiAccettati(band));

        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.content_ingaggi);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}