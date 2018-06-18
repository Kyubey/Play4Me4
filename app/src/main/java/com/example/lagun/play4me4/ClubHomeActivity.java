package com.example.lagun.play4me4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lagun.play4me4.model.ObjectFactory;
import com.example.lagun.play4me4.model.User;

public class ClubHomeActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private User utente;

    private Button mButtonCreatorView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("Impariamo ad usare la Toolbar");
        getSupportActionBar().setTitle("Club Home");
        utente=ObjectFactory.getLoggedUser(getApplicationContext());
        ImageView imageTool = (ImageView) findViewById(R.id.noticeMe);
        imageTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ClubHomeActivity.this, Notification.class);
                startActivity(i);
            }
        });
        Button numberNote = findViewById(R.id.numberNote);
        if(utente.numNotifiche()!=0) {
            numberNote.setVisibility(View.VISIBLE);
            numberNote.setText(Integer.toString(utente.numNotifiche()));
        }
        else
            numberNote.setVisibility(View.INVISIBLE);

        mAdapter = new ClubHomeAdapter(ObjectFactory.getTuoiEventi(utente));
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
                Intent i = new Intent(ClubHomeActivity.this, EventCreationActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onResume(){
        if(mAdapter!=null)
            mAdapter.notifyDataSetChanged();
        if(getIntent().getBooleanExtra("Deleted",false))
            startActivity(new Intent(ClubHomeActivity.this,DrawClub.class));
        super.onResume();
    }


    @Override
    public void onBackPressed() {

    }
}
