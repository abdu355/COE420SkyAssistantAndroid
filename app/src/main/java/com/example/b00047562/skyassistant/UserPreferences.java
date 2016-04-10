package com.example.b00047562.skyassistant;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.w3c.dom.Text;

import java.util.List;

public class UserPreferences extends AppCompatActivity implements View.OnClickListener {

    Button addevent;
    MaterialCalendarView cal;
    EditText eventname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        cal = (MaterialCalendarView) findViewById(R.id.calendarView);
        eventname = (EditText)findViewById(R.id.editText);
        addevent = (Button) findViewById(R.id.btn_addevent);
        addevent.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.btn_addevent:

                Long time = null;
                try {
                    CalendarDay calday = cal.getSelectedDate();
                    time = calday.getDate().getTime();


                    Intent intent = new Intent(Intent.ACTION_EDIT);
                    intent.setType("vnd.android.cursor.item/event");
                    intent.putExtra("beginTime", time);
                    intent.putExtra("allDay", true);
                    intent.putExtra("rrule", "FREQ=YEARLY");
                    intent.putExtra("endTime", time+60*60*1000);
                    intent.putExtra("title", "SkyAssistantEvent "+eventname.getText());;

                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Select a Date first !",Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}
