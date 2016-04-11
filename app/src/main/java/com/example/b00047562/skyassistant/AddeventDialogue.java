package com.example.b00047562.skyassistant;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddeventDialogue extends AppCompatActivity {

    EditText event_date,event_name,event_time,event_loc,event_desc;
    Long date,time;
    String loc;
    Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addevent_dialogue);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                addEvent();
            }
        });
        event_date = (EditText)findViewById(R.id.event_date);
        event_name=(EditText)findViewById(R.id.event_name);
        event_time=(EditText)findViewById(R.id.event_time);
        event_loc=(EditText)findViewById(R.id.event_location);
        event_desc=(EditText)findViewById(R.id.event_desc);


        myCalendar = Calendar.getInstance();
        Intent getInfo = getIntent();
        date = getInfo.getLongExtra("calendarTime",myCalendar.getTimeInMillis());

        event_date.setText( getDate(date,"dd/MM/yyyy"));
        //time= Long.parseLong(event_time.getText().toString());
        event_time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
                int minute = myCalendar.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddeventDialogue.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        event_time.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

    }
    public void callCalendar()
    {

        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", date);
        intent.putExtra("allDay", true);
        intent.putExtra("rrule", "FREQ=YEARLY");
        intent.putExtra("endTime", date+60*60*1000);
        intent.putExtra("title", "SkyAssistantEvent "+ event_name.getText().toString());;

        startActivity(intent);
    }
    public void addEvent()
    {
        Intent calIntent = new Intent(Intent.ACTION_INSERT);
        calIntent.setType("vnd.android.cursor.item/event");
        calIntent.putExtra(CalendarContract.Events.TITLE, event_name.getText().toString());
        calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, event_loc.getText().toString());
        calIntent.putExtra(CalendarContract.Events.DESCRIPTION, event_desc.getText().toString());

        //GregorianCalendar calDate = new GregorianCalendar(2012, 7, 15);
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false);
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                date);
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
               date+60*60*1000);

        startActivity(calIntent);
    }
    public static String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}



