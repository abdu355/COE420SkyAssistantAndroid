package com.example.b00047562.skyassistant;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
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
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;

public class SignUp extends AppCompatActivity {

    RelativeLayout signupback;
    EditText usrdob;
    EditText usremail;
    EditText usrpass;
    EditText usrname;
    Spinner usrcountry;
    private ProgressDialog mProgressDialog;
    Button signup;

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

        usrdob=(EditText)findViewById(R.id.DOB);
        usrdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.show();
            }
        });

        Locale[] locale = Locale.getAvailableLocales();
        final ArrayList<String> countries = new ArrayList<String>();
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
        assert citizenship != null;
        citizenship.setAdapter(adapter);

        signup = (Button)findViewById(R.id.signupbtn);
        usrname = (EditText)findViewById(R.id.name);
        usrpass =(EditText)findViewById(R.id.password);
        usremail = (EditText)findViewById(R.id.email);
        usrcountry = (Spinner)findViewById(R.id.country);



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getApplicationContext(),MainActivity.class));
                String username = usrname.getText().toString();
                String password = usrpass.getText().toString();
                String email = usremail.getText().toString();
                String dob = usrdob.getText().toString();
                String country = usrcountry.getSelectedItem().toString();

                username = username.trim();
                password = password.trim();
                email = email.trim();

                if (username.isEmpty() || password.isEmpty() || email.isEmpty() || dob.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                    builder.setMessage(R.string.signup_error_message)
                            .setTitle(R.string.signup_error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    //setProgressBarIndeterminateVisibility(true);
                    mProgressDialog = new ProgressDialog(SignUp.this);
                    // Set progressdialog title
                    mProgressDialog.setTitle("Creating User");
                    // Set progressdialog message
                    mProgressDialog.setMessage("Hang on...");
                    mProgressDialog.setIcon(R.mipmap.ic_launcher);
                    mProgressDialog.setIndeterminate(false);
                    // Show progressdialog
                    mProgressDialog.show();

                    ParseUser newUser = new ParseUser();//create new user data
                    newUser.setUsername(username);
                    newUser.setPassword(password);
                    newUser.setEmail(email);
                    newUser.put("DOB", usrdob.getText().toString());
                    newUser.put("Country", country);

                    //newUser.pinInBackground();
                    newUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            //setProgressBarIndeterminateVisibility(false);

                            if (e == null) {
                                // Success!
                                mProgressDialog.dismiss();
                                Intent intent = new Intent(SignUp.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                try {
                                    mProgressDialog.dismiss();
                                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                                    builder.setMessage(e.getMessage())
                                            .setTitle(R.string.signup_error_title)
                                            .setPositiveButton(android.R.string.ok, null);
                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    });
                }
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
                usrdob.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }



}
