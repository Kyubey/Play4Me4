package com.example.lagun.play4me4;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.lagun.play4me4.model.DateUtils;
import com.example.lagun.play4me4.model.Event;
import com.example.lagun.play4me4.model.Notify;
import com.example.lagun.play4me4.model.ObjectFactory;
import com.example.lagun.play4me4.model.User;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
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
    private ImageView mGalleryImage;
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
        ImageView imageTool = (ImageView) findViewById(R.id.noticeMe);
        imageTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EventCreationActivity.this, Notification.class);
                startActivity(i);
            }
        });
        Button numberNote = findViewById(R.id.numberNote);
        if(utente.numNotifiche()!=0) {
            numberNote.setVisibility(View.VISIBLE);
            numberNote.setText(Integer.toString(ObjectFactory.getLoggedUser(this).numNotifiche()));
        }
        else
            numberNote.setVisibility(View.INVISIBLE);

        mImageView=(ImageView) findViewById(R.id.image_event);
        Button mCreation = (Button) findViewById(R.id.event_accept_buton);
        Button mDelete = (Button) findViewById(R.id.event_delete);
        if(getIntent().getIntExtra("numberEvent",-1)!=-1){

            final Event evento= ObjectFactory.getEventi().get(getIntent().getIntExtra("numberEvent", -1));
            mNameEventView.setText(evento.getNome());
            //mDataView.setText(new SimpleDateFormat("dd/MM/yyyy").format(evento.data.getTime()));
            mDataView.setText(DateUtils.formatDateExtended(evento.getData()));
            mHourView.setText(DateUtils.formatTime(evento.getData()));
            mImageView.setImageDrawable(evento.getEventPicture());
            mDescriptionView.setText(evento.getDescription());
            mAddressView.setText(evento.getStringPlace());
            mDelete.setOnClickListener(new View.OnClickListener() {
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
                                    for(User accettato: evento.getAccettati()){
                                        accettato.disableNotifiche(getIntent().getIntExtra("numberEvent", -1));
                                        accettato.addNotify(new Notify(-1,"L'evento "+evento.getNome()+" a cui partecipavi è stato cancellato"));
                                    }
                                    ObjectFactory.getEventi().remove(getIntent().getIntExtra("numberEvent", -1));
                                    Intent i=new Intent(EventCreationActivity.this, DrawClub.class);
                                    i.putExtra("Deleted",true);
                                    startActivity(i);
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
        else
            mDelete.setVisibility(View.INVISIBLE);
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
                mGalleryImage=findViewById(R.id.rock);
                mImageView.setImageDrawable(mGalleryImage.getDrawable());
                mGallery.setVisibility(View.GONE);
                mAll.setVisibility(View.VISIBLE);
            }
        });
        findViewById(R.id.foto1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGalleryImage=findViewById(R.id.foto1);
                mImageView.setImageDrawable(mGalleryImage.getDrawable());
                mGallery.setVisibility(View.GONE);
                mAll.setVisibility(View.VISIBLE);
            }
        });
        findViewById(R.id.foto2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGalleryImage=findViewById(R.id.foto2);
                mImageView.setImageDrawable(mGalleryImage.getDrawable());
                mGallery.setVisibility(View.GONE);
                mAll.setVisibility(View.VISIBLE);
            }
        });
        findViewById(R.id.foto3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGalleryImage=findViewById(R.id.foto3);
                mImageView.setImageDrawable(mGalleryImage.getDrawable());
                mGallery.setVisibility(View.GONE);
                mAll.setVisibility(View.VISIBLE);
            }
        });
        findViewById(R.id.foto4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGalleryImage=findViewById(R.id.foto4);
                mImageView.setImageDrawable(mGalleryImage.getDrawable());
                mGallery.setVisibility(View.GONE);
                mAll.setVisibility(View.VISIBLE);
            }
        });
        findViewById(R.id.foto5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGalleryImage=findViewById(R.id.foto5);
                mImageView.setImageDrawable(mGalleryImage.getDrawable());
                mGallery.setVisibility(View.GONE);
                mAll.setVisibility(View.VISIBLE);
            }
        });
        findViewById(R.id.foto6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGalleryImage=findViewById(R.id.foto6);
                mImageView.setImageDrawable(mGalleryImage.getDrawable());
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
            i = new Intent(EventCreationActivity.this, DrawClub.class);}
            else{
                int numberEvent=getIntent().getIntExtra("numberEvent",-1);
                ObjectFactory.eventi.get(numberEvent).setNome(nome);
                ObjectFactory.eventi.get(numberEvent).setStringPlace(mAddressView.getText().toString());
                ObjectFactory.eventi.get(numberEvent).setEventPicture(mImageView.getDrawable());
                ObjectFactory.eventi.get(numberEvent).setData(DateUtils.parseDateTime(data+" - "+ora));
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
