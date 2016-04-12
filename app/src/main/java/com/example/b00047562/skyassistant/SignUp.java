package com.example.b00047562.skyassistant;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.commit451.nativestackblur.NativeStackBlur;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;

public class SignUp extends AppCompatActivity {

    RelativeLayout signupback;
    EditText dob;
    private DatePickerDialog DatePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        //------------------------------------------------------ Add background image
        signupback=(RelativeLayout)findViewById(R.id.signupback);
        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.skybackground2);
        Bitmap bm = NativeStackBlur.process(back, 250);
        BitmapDrawable ob = new BitmapDrawable(getResources(), bm);
        signupback.setBackground(ob);
        //------------------------------------------------------ Add background image

        dob=(EditText)findViewById(R.id.DOB);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.show();
            }
        });

        Locale[] locale = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        String country;
        for( Locale loc : locale ){
            country = loc.getDisplayCountry();
            if( country.length() > 0 && !countries.contains(country) ){
                countries.add( country );
            }
        }
        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);

        Spinner citizenship = (Spinner)findViewById(R.id.country);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_items, countries);
        citizenship.setAdapter(adapter);

        Button email_signup_button = (Button)findViewById(R.id.email_signup_button);
        email_signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        setDateTimeField();
    }

    private void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dob.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }



}
