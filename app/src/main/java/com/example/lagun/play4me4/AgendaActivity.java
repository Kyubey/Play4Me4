package com.example.lagun.play4me4;

import android.app.usage.UsageEvents;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
        Event ev1= new Event (Color.GREEN,  1526626800000L, "Teacher professional day");
        compactCalendar.addEvent(ev1);
        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context =getApplicationContext();

                if (dateClicked.toString().compareTo("Fri May 18 9:00:00 GMT 2018")==0 ){
                    Toast.makeText(context, "Teacher professional day", Toast.LENGTH_SHORT).show();
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
