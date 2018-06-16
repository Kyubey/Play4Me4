package com.example.lagun.play4me4;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.io.InputStream;

public class UserProfileActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private User utente=null;
    private User profileOwner=null;
    private ImageView mImageView;
    private TextView mNameUser;
    private ImageButton mModificaImageButton;
    private Button mModificaButton;
    private TextView mPlace;
    private TextView mPhone;
    private TextView mInfo;
    private TextView mDesctiption;
    private LinearLayout mMapWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        new BitmapFactory();
        utente= ObjectFactory.getLoggedUser(getApplicationContext());
        profileOwner=ObjectFactory.getFromEmail(getIntent().getStringExtra("userMail"));
        Resources res= getResources();
        ImageView imageView=(ImageView) findViewById(R.id.image_user);
        mNameUser=findViewById(R.id.event_name);
        mModificaImageButton=findViewById(R.id.modifica_image);
        mModificaButton=findViewById(R.id.modifica);
        mPlace=findViewById(R.id.place);
        mPhone=findViewById(R.id.telephone_number);
        mInfo=findViewById(R.id.info);
        mDesctiption=findViewById(R.id.description);
        mMapWindow=findViewById(R.id.map_window);
        imageView.setImageDrawable(profileOwner.getProPicture());
        mNameUser.setText(profileOwner.getName());

        if(profileOwner.getIndirizzo()!=null) {
            mPlace.setText(profileOwner.getIndirizzo().getThoroughfare() + " " + profileOwner.getIndirizzo().getFeatureName()+" " + profileOwner.getIndirizzo().getLocality());
        }else if(profileOwner.getIndirizzoString()!=null && !profileOwner.getIndirizzoString().isEmpty()){
            mPlace.setText(profileOwner.getIndirizzoString());
        }else{
            mPlace.setText("Luogo Sconosciuto");
        }

        if(profileOwner.getPhoneNumber()!=null && !profileOwner.getPhoneNumber().isEmpty()){
            mPhone.setText(profileOwner.getPhoneNumber());
        }else{
            mPhone.setText("Numero non pervenuto");
        }

        if(profileOwner.getInfo()!=null && !profileOwner.getInfo().isEmpty()){
            mInfo.setText(profileOwner.getInfo());
        }else{
            mInfo.setText("-");
        }

        if(profileOwner.getDescription()!=null && !profileOwner.getDescription().isEmpty()){
            mDesctiption.setText(profileOwner.getInfo());
        }else{
            mDesctiption.setText("-");
        }


        //File drawableFile = new     File(getApplicationContext().getFilesDir().getAbsolutePath()+"/ic_calendar.png");
        /*imageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_calendar, null));
        {
            Bitmap bm = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.ic_calendar);
            File dir = new File(Environment.getExternalStorageDirectory() + File.separator);
            String filename = "myfile";
            String fileContents = "Hello world!";
            FileOutputStream outputStream;

            try {
                outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.write(fileContents.getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            boolean doSave = true;
            if (!dir.exists()) {
                doSave = dir.mkdirs();
            }

            if (doSave) {
                dir.setWritable(true);
                dir.setExecutable(true);
                dir.setReadable(true);
                //ObjectFactory.saveBitmapToFile(dir,"theNameYouWant.png",bm, Bitmap.CompressFormat.PNG,100);
            }
            else {
                Log.e("app","Couldn't create target directory.");
            }
        }
        try {
            InputStream inputstr;
            getFilesDir().listFiles();
            inputstr=new FileInputStream(new File("src/main/res/drawable"));
            Drawable drawable = Drawable.createFromStream(inputstr, "drawable");
            imageView.setImageDrawable(drawable);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

        //ObjectFactory.initializeClub();
        //imageView.setImageDrawable(Drawable.createFromPath("R//drawable//ic_calendar");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if(profileOwner.getIndirizzo()!=null){

            LatLng coordinate = new LatLng(profileOwner.getIndirizzo().getLatitude(), profileOwner.getIndirizzo().getLongitude());
            mMap.addMarker(new MarkerOptions().position(coordinate).title(profileOwner.getIndirizzo().getThoroughfare() + " " + profileOwner.getIndirizzo().getFeatureName()+" " + profileOwner.getIndirizzo().getLocality()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinate));
        }else{
            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(-34, 151);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMapWindow.setVisibility(View.GONE);
        }

    }
}
