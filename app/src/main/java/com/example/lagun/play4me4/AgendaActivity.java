package com.example.lagun.play4me4;

import android.app.usage.UsageEvents;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.text.ParseException;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AgendaActivity extends AppCompatActivity {

    CompactCalendarView compactCalendar;

    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(null);

        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);
        //Set an event for
        Event ev1 = new Event(Color.GREEN,1527033600000L, "Teachers' Professional Day");
        compactCalendar.addEvent(ev1);
        Event ev2 = new Event(Color.GREEN,1527206400000L, "Giornata del cazzeggio");
        compactCalendar.addEvent(ev2);
        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context =getApplicationContext();

                if (dateClicked.toString().compareTo("Wed May 23 00:00:00 GMT+00:00 2018")==0 ){
                    Toast.makeText(context, "Teacher professional day", Toast.LENGTH_SHORT).show();
                }else if(dateClicked.toString().compareTo("Fri May 25 00:00:00 GMT+00:00 2018")==0 ){
                    Toast.makeText(context, "Giornata del cazzeggio", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "No events planned for that day day", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));

            }
        });
    }

}
