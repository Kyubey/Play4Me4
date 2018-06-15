package com.example.lagun.play4me4;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.example.lagun.play4me4.model.ObjectFactory;
import com.example.lagun.play4me4.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ClubHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onResume(){
        if(mAdapter!=null)
            mAdapter.notifyDataSetChanged();
        if(getIntent().getBooleanExtra("Deleted",false))
            startActivity(new Intent(ClubHomeActivity.this,ClubHomeActivity.class));
        super.onResume();
    }


    @Override
    public void onBackPressed() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
