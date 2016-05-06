package com.example.b00047562.skyassistant;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.commit451.nativestackblur.NativeStackBlur;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class UserPreferences extends AppCompatActivity implements View.OnClickListener {

    Button addevent,viewevents;
    MaterialCalendarView cal;
    //EditText eventname;
    CalendarDay calday;
    RelativeLayout userprefback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //cal = (MaterialCalendarView) findViewById(R.id.calendarView);
        //eventname = (EditText)findViewById(R.id.editText);
        addevent = (Button) findViewById(R.id.btn_addevent);
        viewevents=(Button)findViewById(R.id.btn_submit);
        addevent.setOnClickListener(this);
        viewevents.setOnClickListener(this);

//        //------------------------------------------------------ Add background image
//        userprefback=(RelativeLayout)findViewById(R.id.userprefback);
//        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.skybackground2);
//        Bitmap bm = NativeStackBlur.process(back, 250);
//        BitmapDrawable ob = new BitmapDrawable(getResources(), bm);
//        userprefback.setBackground(ob);
//        //------------------------------------------------------ Add background image


    }


    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.btn_addevent:

                try {
                    //calday = cal.getSelectedDate();

                    Intent sendtoDiag = new Intent(getApplicationContext(),AddeventDialogue.class);
                    sendtoDiag.putExtra("calendarTime",calday.getDate().getTime());
                    startActivity(sendtoDiag);


                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Select a Date first !",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_submit:
                startActivity(new Intent(this,mySchedule.class));
                break;
        }
    }



}
