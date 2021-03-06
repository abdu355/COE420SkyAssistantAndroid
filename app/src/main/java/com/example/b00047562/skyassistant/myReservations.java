package com.example.b00047562.skyassistant;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.commit451.nativestackblur.NativeStackBlur;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import dbhandler.ParseFunctions;

public class myReservations extends AppCompatActivity {

    ListView myreservastionlist;
    ArrayList<String> reservations;
    ArrayAdapter<String> adapter;
    ParseUser currentUser;
    ProgressDialog mProgressDialog;
    RelativeLayout scheduleback;
    List<ParseObject> ob;
    ParseFunctions customParse;
    RelativeLayout myresback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservations);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //------------------------------------------------------ Add background image
        myresback=(RelativeLayout)findViewById(R.id.myresback);
        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.skybackground2);
        Bitmap bm = NativeStackBlur.process(back, 250);
        BitmapDrawable ob = new BitmapDrawable(getResources(), bm);
        myresback.setBackground(ob);
        //------------------------------------------------------ Add background image
        myreservastionlist = (ListView)findViewById(R.id.myReservationlist);
        myreservastionlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i)
                {
                    case 0:
                        startActivity(new Intent(myReservations.this,ticketView.class));
                        break;
                    case 1:
                        //next ticket
                        break;

                    case 2:
                        //next ticket
                        break;
                }
            }
        });


        new RemoteDataTask().execute();

    }


    class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(myReservations.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Looking for Flights");
            // Set progressdialog message
            mProgressDialog.setMessage("Updating...");
            // Show progressdialog
            //mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create the array
//            usercal = customParse.getParseCalendar(ParseUser.getCurrentUser(),"Calendar");
//            String eventdateString = usercal.getEventDate();
//            Date eventdate = customParse.returnDateformat(eventdateString);
            //Log.d("CalendarMain",eventdateString);
            reservations = new ArrayList<String>();
            try {

                // Locate the class table named "Chapters"
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Reservation");
                // look only for objects witch column is equal to the ParseObject course.
                // get data in an ascending list
                query.orderByDescending("createdAt");
                // execute the query and get back a list of the data you where looking for
                ob = query.find();
                // make a for inside the list and set the data to the adapter
                for (ParseObject res : ob) {
                    // Locate images in flag column
                    String item = res.getString("ticketno")+"\n\n\n\n\n\n"+res.getString("class")+"\n\n\n\n\n\n"+res.getString("gateno")+"\n\n\n\n\n"+res.getString("seat");
                    //Log.d("objectStringMain",item);
                    reservations.add(item);
                }
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mProgressDialog.dismiss();
            adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, reservations);
            myreservastionlist.setAdapter(adapter);

        }
    }

}
