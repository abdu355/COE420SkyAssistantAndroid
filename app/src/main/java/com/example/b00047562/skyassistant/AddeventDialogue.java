package com.example.b00047562.skyassistant;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.parse.ParseUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import dbhandler.ParseFunctions;

public class AddeventDialogue extends AppCompatActivity {

    EditText event_date,event_name,event_time,event_loc,event_desc;
    Long date,time;
    String loc;
    Calendar myCalendar;
    private ParseFunctions customParse;
    private int fhour,fmin;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addevent_dialogue);




        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //addEvent();
                addtoDB();
                Snackbar.make(view, "Day Preference Added", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

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
                        fhour = selectedHour;
                        fmin = selectedMinute;
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

    }
    public void addtoDB()
    {
        String newdate = getDateDB(date,"dd/MM/yyyy hh:mm:ss",fhour,fmin);
        Date newdatev2 = getDateDBv2(date,"dd/MM/yyyy hh:mm:ss",fhour,fmin);
        customParse = new ParseFunctions();
        customParse.pushCalendarData(ParseUser.getCurrentUser(),newdatev2,"Calendar",newdate);
        fab.setEnabled(false);
        //fab.setBackgroundColor(Color.GREEN);

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


    public static String getDateDB(long milliSeconds, String dateFormat,int hour,int min)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(milliSeconds);
        cal.set(Calendar.HOUR_OF_DAY,hour);
        cal.set(Calendar.MINUTE,min);

        Date d = cal.getTime();
        String datestring = formatter.format(d);
        Log.d("Date",datestring);
        return datestring;

    }
    public static Date getDateDBv2(long milliSeconds, String dateFormat,int hour,int min)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(milliSeconds);
        cal.set(Calendar.HOUR_OF_DAY,hour);
        cal.set(Calendar.MINUTE,min);

        Date d = cal.getTime();
        return d;

    }
//to parse date from String
//    String startDateString = "06/27/2007";
//    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
//    Date startDate;
//    try {
//        startDate = df.parse(startDateString);
//        String newDateString = df.format(startDate);
//        System.out.println(newDateString);
//    } catch (ParseException e) {
//        e.printStackTrace();
//    }

}



