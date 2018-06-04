package com.example.lagun.play4me4;

import android.content.Intent;
import android.graphics.Color;
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
        String data = mDataView.getText().toString();
        String ora = mHourView.getText().toString();
        String nome = mNameEventView.getText().toString();
        String descrizione = mDescriptionView.getText().toString();

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
        if(!cancel){
            Event nuovo=new Event(nome,DateUtils.parseDateTime(data+" - "+ora),utente,descrizione);
            nuovo.setEventPicture(mImageView.getDrawable());
            ObjectFactory.createEvent(nuovo);
            //ObjectFactory.addRegistered(new User(userName,email.toLowerCase(),password,accountType.equals("Locale")));
           Intent i = new Intent(EventCreationActivity.this, ClubHomeActivity.class);
            startActivity(i);
        }
    }
}
