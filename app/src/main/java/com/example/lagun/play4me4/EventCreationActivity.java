package com.example.lagun.play4me4;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lagun.play4me4.model.DateUtils;
import com.example.lagun.play4me4.model.Event;
import com.example.lagun.play4me4.model.ObjectFactory;
import com.example.lagun.play4me4.model.User;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class EventCreationActivity extends AppCompatActivity {

    private AutoCompleteTextView mNameEventView;
    private EditText mDataView;
    private EditText mHourView;
    private EditText mAddressView;
    private EditText mDescriptionView;
    private ImageView mImageView;
    private View mGallery;
    private View mAll;
    private User utente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_creation);
        mNameEventView= (AutoCompleteTextView) findViewById(R.id.nome_evento);
        mDataView= (EditText) findViewById(R.id.start_date);
        mHourView= (EditText) findViewById(R.id.start_hour);
        mAddressView= (EditText) findViewById(R.id.location);
        mDescriptionView= (EditText) findViewById(R.id.detail);
        mGallery=findViewById(R.id.hiddengallery);
        mAll=findViewById(R.id.creationVisible);
        mGallery.setVisibility(View.GONE);
        utente=ObjectFactory.getLoggedUser(getApplicationContext());

        mImageView=(ImageView) findViewById(R.id.image_event);
        Button mCreation = (Button) findViewById(R.id.event_accept_buton);

        if(getIntent().getIntExtra("numberEvent",-1)!=-1){

            Event evento= ObjectFactory.getEventi().get(getIntent().getIntExtra("numberEvent", -1));
            mNameEventView.setText(evento.getNome());
            //mDataView.setText(new SimpleDateFormat("dd/MM/yyyy").format(evento.data.getTime()));
            mDataView.setText(DateUtils.formatDateExtended(evento.data));
            mHourView.setText(DateUtils.formatTime(evento.data));
            mImageView.setImageDrawable(evento.getEventPicture());
            mDescriptionView.setText(evento.getDescription());
            mAddressView.setText(evento.getStringPlace());

        }
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptInsertPic();
            }
        });
        mCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptCreation();
            }
        });
    }

    protected void attemptInsertPic(){
        mAll.setVisibility(View.GONE);
        mGallery.setVisibility(View.VISIBLE);
        findViewById(R.id.rock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageView.setImageResource(R.drawable.rock);
                mGallery.setVisibility(View.GONE);
                mAll.setVisibility(View.VISIBLE);
            }
        });
        mGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mGallery.setVisibility(View.GONE);
                //mAll.setVisibility(View.VISIBLE);
            }
        });
    }

    protected void attemptCreation(){

        // Reset errors.
        mDataView.setError(null);
        mHourView.setError(null);

        // Store values at the time of the login attempt.
        String data = mDataView.getText().toString().replace(" ","");
        String ora = mHourView.getText().toString().replace(" ","");
        String nome = mNameEventView.getText().toString();
        String descrizione = mDescriptionView.getText().toString();
        Address indirizzo=null;
        LatLng coordinate=null;
        String location = mAddressView.getText().toString();

        if(Geocoder.isPresent()){
            try {
                Geocoder gc = new Geocoder(this);
                List<Address> addresses= gc.getFromLocationName(location, 1); // get the found Address Objects

                List<LatLng> ll = new ArrayList<LatLng>(addresses.size()); // A list to save the coordinates if they are available
                for(Address a : addresses){
                    if(a.hasLatitude() && a.hasLongitude()){
                        ll.add(new LatLng(a.getLatitude(), a.getLongitude()));
                    }
                }
                indirizzo= addresses.get(0);
                coordinate= ll.get(0);
            } catch (IOException e) {
                // handle the exception
            }
        }

        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(nome)){
            mNameEventView.setError(getString(R.string.error_field_required));
            focusView = mNameEventView;
            cancel = true;
        }
        if (TextUtils.isEmpty(data)) {
            mDataView.setError(getString(R.string.error_field_required));
            focusView = mDataView;
            cancel = true;
        }else if(!DateUtils.isDataValid(data)){
            mDataView.setError(getString(R.string.error_invalid_data));
            focusView = mDataView;
            cancel = true;

        }
        if (TextUtils.isEmpty(ora)) {
            mHourView.setError(getString(R.string.error_field_required));
            focusView = mHourView;
            cancel = true;
        } else if (!DateUtils.isHourValid(ora)) {
            mHourView.setError(getString(R.string.error_invalid_hour));
            focusView = mHourView;
            cancel = true;
        }
        if (TextUtils.isEmpty(location)) {
            mAddressView.setError(getString(R.string.error_field_required));
            focusView = mAddressView;
            cancel = true;
        }
        if(!cancel){
            Intent i;
            if(getIntent().getIntExtra("numberEvent",-1)==-1){
            Event nuovo=new Event(nome,DateUtils.parseDateTime(data+" - "+ora),utente,descrizione);
            nuovo.setEventPicture(mImageView.getDrawable());
            nuovo.setStringPlace(mAddressView.getText().toString());
            if(indirizzo!=null){
                nuovo.setPlace(indirizzo);
                if(coordinate!=null){

                    nuovo.setCoordinates(coordinate);
                }
            }

            ObjectFactory.createEvent(nuovo);
            i = new Intent(EventCreationActivity.this, ClubHomeActivity.class);}
            else{
                int numberEvent=getIntent().getIntExtra("numberEvent",-1);
                ObjectFactory.eventi.get(numberEvent).setNome(nome);
                ObjectFactory.eventi.get(numberEvent).setStringPlace(mAddressView.getText().toString());
                ObjectFactory.eventi.get(numberEvent).setEventPicture(mImageView.getDrawable());
                ObjectFactory.eventi.get(numberEvent).data=DateUtils.parseDateTime(data+" - "+ora);
                if(indirizzo!=null){
                    ObjectFactory.eventi.get(numberEvent).setPlace(indirizzo);
                    if(coordinate!=null){

                        ObjectFactory.eventi.get(numberEvent).setCoordinates(coordinate);
                    }
                }
                ObjectFactory.eventi.get(numberEvent).setDescription(descrizione);
                i=new Intent(EventCreationActivity.this, EventPageActivity.class);
                i.putExtra("numberEvent",getIntent().getIntExtra("numberEvent",-1));
            }
            //ObjectFactory.addRegistered(new User(userName,email.toLowerCase(),password,accountType.equals("Locale")));

            startActivity(i);
        }
    }
}
