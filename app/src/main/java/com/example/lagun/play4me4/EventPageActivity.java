package com.example.lagun.play4me4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lagun.play4me4.model.DateUtils;
import com.example.lagun.play4me4.model.Event;
import com.example.lagun.play4me4.model.ObjectFactory;

import java.util.GregorianCalendar;

public class EventPageActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);
        evento= ObjectFactory.getEventi().get(getIntent().getIntExtra("numberEvent", -1));
        mImage= findViewById(R.id.image_event);
        mMese= findViewById(R.id.data_month);
        mGiorno= findViewById(R.id.data_day);
        mModifica= findViewById(R.id.modifica);
        mData= findViewById(R.id.data);
        mLuogo= findViewById(R.id.posto);
        mDescrizione= findViewById(R.id.description);
        mNome= findViewById(R.id.event_name);
        mOwner= findViewById(R.id.event_owner);
        mBands= findViewById(R.id.bands_partecipanti);
        mGestioneBands= findViewById(R.id.gestione_bands);

        mImage.setImageDrawable(evento.getEventPicture());
        mNome.setText(evento.getNome());
        mMese.setText(DateUtils.getMese(DateUtils.formatDate(evento.data).split("/")[1]));
        mData.setText(DateUtils.getGiorno(evento.data.get(GregorianCalendar.DAY_OF_WEEK))+" "+(DateUtils.formatDate(evento.data).split("/")[0])+" "+DateUtils.getMese(DateUtils.formatDate(evento.data).split("/")[1])+" alle ore "+ "00:00");
        mGiorno.setText(DateUtils.formatDate(evento.data).split("/")[0]);


    }
}
