package com.example.lagun.play4me4;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lagun.play4me4.model.ObjectFactory;
import com.example.lagun.play4me4.model.User;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UserProfileModifierActivity extends AppCompatActivity {

    private User utente=null;
    private User profileOwner=null;
    private ImageView mImageView;
    private Button mNameUser;
    private TextView mPlace;
    private TextView mPhone;
    private TextView mInfo;
    private TextView mDesctiption;
    private Button mApplyChanges;
    private LinearLayout mAll;
    private View mGallery;
    private ImageView mGalleryImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Crasha qui
        setContentView(R.layout.activity_user_profile_modifier);
        utente= ObjectFactory.getLoggedUser(getApplicationContext());
        profileOwner=ObjectFactory.getFromEmail(getIntent().getStringExtra("userMail"));
        Resources res= getResources();
        mImageView=(ImageView) findViewById(R.id.image_user);
        mNameUser=findViewById(R.id.event_name);
        mPlace=findViewById(R.id.place);
        mPhone=findViewById(R.id.telephone_number);
        mInfo=findViewById(R.id.info);
        mDesctiption=findViewById(R.id.description);
        mApplyChanges=findViewById(R.id.accept_buton);
        mAll=findViewById(R.id.everything);
        mGallery=findViewById(R.id.hidden_gallery);

        mImageView.setImageDrawable(profileOwner.getProPicture());
        mNameUser.setText(profileOwner.getName());


        if(profileOwner.getIndirizzo()!=null) {
            mPlace.setText(profileOwner.getIndirizzo().getThoroughfare() + " " + profileOwner.getIndirizzo().getFeatureName()+" " + profileOwner.getIndirizzo().getLocality());
        }else if(profileOwner.getIndirizzoString()!=null && !profileOwner.getIndirizzoString().isEmpty()){
            mPlace.setText(profileOwner.getIndirizzoString());
        }else{
            mPlace.setText("");
        }

        if(profileOwner.getPhoneNumber()!=null && !profileOwner.getPhoneNumber().isEmpty()){
            mPhone.setText(profileOwner.getPhoneNumber());
        }else{
            mPhone.setText("");
        }

        if(profileOwner.getInfo()!=null && !profileOwner.getInfo().isEmpty()){
            mInfo.setText(profileOwner.getInfo());
        }else
        if(profileOwner.isClub()){
            mInfo.setText("Gestore");
        }else{
            mInfo.setText("Band");

        }

        if(profileOwner.getDescription()!=null && !profileOwner.getDescription().isEmpty()){
            mDesctiption.setText(profileOwner.getDescription());
        }else{
            mDesctiption.setText("");
        }


        mImageView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                attemptInsertPic();
            }
        });
//Trova l'indirizzo e le coordinate data la stringa
        mApplyChanges.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                utente.setProPicture(mImageView.getDrawable());
                utente.setPhoneNumber(mPhone.getText().toString());
                utente.setInfo(mInfo.getText().toString());
                utente.setDescription(mDesctiption.getText().toString());


                Address indirizzo=null;
                LatLng coordinate=null;
                String location = mPlace.getText().toString();

                if(Geocoder.isPresent()){
                    try {
                        Geocoder gc = new Geocoder(getApplicationContext());
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

                if(indirizzo!=null){
                    utente.setIndirizzo(indirizzo);
                    if(coordinate!=null){

                        utente.getIndirizzo().setLatitude(coordinate.latitude);
                        utente.getIndirizzo().setLongitude(coordinate.longitude);
                    }
                }


                Intent i = new Intent(UserProfileModifierActivity.this, UserProfileActivity.class);
                i.putExtra("userMail", profileOwner.getMail());
                startActivity(i);
            }
        });;

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

}
