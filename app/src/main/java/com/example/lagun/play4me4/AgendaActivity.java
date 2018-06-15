package com.example.lagun.play4me4;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.lagun.play4me4.model.Event;

import com.example.lagun.play4me4.model.ObjectFactory;
import com.example.lagun.play4me4.model.User;
import com.github.sundeepk.compactcalendarview.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class AgendaActivity extends AppCompatActivity {

    CompactCalendarView compactCalendar;

    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        final User utente = ObjectFactory.getLoggedUser(getApplicationContext());
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);


        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);
        //Set an event for
        if(!utente.isClub()){
            for (Map.Entry<Integer, Event> next : ObjectFactory.getEventiAccettati(utente).entrySet()) {
                compactCalendar.addEvent(new com.github.sundeepk.compactcalendarview.domain.Event(Color.GREEN, next.getValue().getData().getTimeInMillis(), next.getValue().getNome()));
            }
        }else {
            for (Map.Entry<Integer, Event> next : ObjectFactory.getTuoiEventi(utente).entrySet()) {
                compactCalendar.addEvent(new com.github.sundeepk.compactcalendarview.domain.Event(Color.GREEN, next.getValue().getData().getTimeInMillis(), next.getValue().getNome()));
            }
        }

        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context =getApplicationContext();

                for(Map.Entry<Integer,Event> evento:ObjectFactory.getEventiAccettati(utente).entrySet()){
                    if(dateClicked.toString().substring(0,10).equals(evento.getValue().getData().getTime().toString().substring(0,10)))
                        Toast.makeText(context,evento.getValue().getNome(), Toast.LENGTH_SHORT).show();
                }
                for(Map.Entry<Integer,Event> evento:ObjectFactory.getTuoiEventi(utente).entrySet()){
                    if(dateClicked.toString().substring(0,10).equals(evento.getValue().getData().getTime().toString().substring(0,10)))
                        Toast.makeText(context,evento.getValue().getNome(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));

            }
        });
        actionBar.setTitle(dateFormatMonth.format(compactCalendar.getFirstDayOfCurrentMonth()));
    }

}
