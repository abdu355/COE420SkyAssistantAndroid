package com.example.b00047562.skyassistant;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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
    //EditText eventname;
    CalendarDay calday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        cal = (MaterialCalendarView) findViewById(R.id.calendarView);
        //eventname = (EditText)findViewById(R.id.editText);
        addevent = (Button) findViewById(R.id.btn_addevent);
        addevent.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.btn_addevent:

                try {
                    calday = cal.getSelectedDate();

                    Intent sendtoDiag = new Intent(getApplicationContext(),AddeventDialogue.class);
                    sendtoDiag.putExtra("calendarTime",calday.getDate().getTime());
                    startActivity(sendtoDiag);


                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Select a Date first !",Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }



}
