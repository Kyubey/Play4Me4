package com.example.lagun.play4me4;

import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lagun.play4me4.model.DateUtils;
import com.example.lagun.play4me4.model.Event;
import com.example.lagun.play4me4.model.ObjectFactory;
import com.example.lagun.play4me4.model.User;
import com.google.android.gms.maps.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

public class EventPageActivityBand extends AppCompatActivity {
    private ImageView mImage;
    private TextView mMese;
    private TextView mGiorno;
    private  TextView mNome;
    private  TextView mOwner;
    private LinearLayout mModifica;
    private TextView mData;
    private TextView mLuogo;
    private TextView mDescrizione;
    private RecyclerView mBands;
    private Button mGestioneBands;
    private Event evento;
    private LatLng coordinates;
    private User utente;
    //private Geocoder coder= new Geocoder(context);

    Geocoder geocoder = new Geocoder(this, Locale.getDefault());


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page_band);
        final int numEvent=getIntent().getIntExtra("numberEvent", -1);
        evento= ObjectFactory.getEventi().get(numEvent);
        mImage= findViewById(R.id.image_event);
        mMese= findViewById(R.id.data_month);
        mGiorno= findViewById(R.id.data_day);
        mData= findViewById(R.id.data);
        mLuogo= findViewById(R.id.posto);
        mDescrizione= findViewById(R.id.description);
        mNome= findViewById(R.id.event_name);
        mOwner= findViewById(R.id.event_owner);
        utente=ObjectFactory.getLoggedUser(getApplicationContext());
        final Button mButton=findViewById(R.id.proponiti);


        mImage.setImageDrawable(evento.getEventPicture());
        mNome.setText(evento.getNome());
        mMese.setText(DateUtils.getMese(new SimpleDateFormat("dd/MM/yyyy").format(evento.getData().getTime()).split("/")[1]));
        mData.setText(DateUtils.getGiorno(evento.getData().get(GregorianCalendar.DAY_OF_WEEK))+" "+(new SimpleDateFormat("dd/MM/yyyy").format(evento.getData().getTime()).split("/")[1])+" "+DateUtils.getMese(new SimpleDateFormat("dd/MM/yyyy").format(evento.getData().getTime()).split("/")[0], 1)+" "+" alle ore "+ DateUtils.formatTime(evento.getData()));
        mGiorno.setText(new SimpleDateFormat("dd/MM/yyyy").format(evento.getData().getTime()).split("/")[0]);
        if(evento.getPlace()!=null) {
            mLuogo.setText(evento.getPlace().getThoroughfare() + " " + evento.getPlace().getFeatureName()+" " + evento.getPlace().getLocality());
        }else{
            mLuogo.setText(evento.getStringPlace());
        }
        mDescrizione.setText(evento.getDescription());
        mOwner.setText(evento.getOwner().getName());


        RecyclerView.Adapter adapter = new EventPageAdapter(evento.getAccettati());
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.bands_partecipanti);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(adapter);

        final int negColor = this.getResources().getColor(R.color.colorNegativeButton);
        final int posColor = this.getResources().getColor(R.color.colorPrimary);


        if(!ObjectFactory.getEventiNuovi(utente).containsValue(evento)){
            mButton.setText("Ritirati");
            mButton.setBackgroundColor(negColor);
        }

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ObjectFactory.getEventiNuovi(utente).containsValue(evento)){
                    mButton.setText("Ritirati");
                    mButton.setBackgroundColor(negColor);
                    ObjectFactory.getEventi().get(numEvent).proponiNuovo(utente,numEvent);

                }
                else{
                    if(ObjectFactory.getEventiAccettati(utente).containsValue(evento))
                        ObjectFactory.getEventi().get(numEvent).rimuoviDaAccettati(utente,numEvent);
                    else
                        ObjectFactory.getEventi().get(numEvent).rimuoviProposto(utente);
                    mButton.setText("Proponiti");
                    mButton.setBackgroundColor(posColor);
                }
            }
        });
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(EventPageActivityBand.this,DrawBands.class));
        finish();
    }

}
