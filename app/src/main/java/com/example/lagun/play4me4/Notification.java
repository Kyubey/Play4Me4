package com.example.lagun.play4me4;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.example.lagun.play4me4.model.ObjectFactory;
import com.example.lagun.play4me4.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Notification extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private User utente;

    private Button mButtonCreatorView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_layout);
        utente=ObjectFactory.getLoggedUser(getApplicationContext());
        ImageView imageTool = (ImageView) findViewById(R.id.noticeMe);
        Button numberNote = findViewById(R.id.numberNote);
        if(utente.numNotifiche()!=0) {
            numberNote.setText(Integer.toString(utente.numNotifiche()));
        }
        else
            numberNote.setVisibility(View.INVISIBLE);

        mAdapter = new NotificationAdapter(utente.getNuoveNotifiche(), utente.getVecchieNotifiche());
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);

        /*mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ClubHomeAdapter(new String[]{"Proviamo"});
        mRecyclerView.setAdapter(mAdapter);/*/

        mButtonCreatorView= (Button)findViewById(R.id.event_creation);
        mButtonCreatorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Notification.this, EventCreationActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onResume(){
        if(mAdapter!=null)
            mAdapter.notifyDataSetChanged();
        super.onResume();
    }



    private List<SimpleViewModel> generateSimpleList() {
        List<SimpleViewModel> simpleViewModelList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            simpleViewModelList.add(new SimpleViewModel(String.format(Locale.US, "This is item %d", i)));
        }

        return simpleViewModelList;
    }

    public class SimpleViewModel {
        private String simpleText;

        public SimpleViewModel(final String simpleText) {
            setSimpleText(simpleText);
        }

        public String getSimpleText() {
            return simpleText;
        }

        public void setSimpleText(final String simpleText) {
            this.simpleText = simpleText;
        }
    }
    //Paolo@paolo.it
}
