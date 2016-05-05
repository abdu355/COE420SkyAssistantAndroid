package com.example.b00047562.skyassistant;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.commit451.nativestackblur.NativeStackBlur;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dbhandler.ParseFunctions;

public class mySchedule extends AppCompatActivity {

    ListView myschedule;
    ArrayList<String> schedules;
    ArrayAdapter<String> adapter;
    ParseUser currentUser;
    ProgressDialog mProgressDialog;
    RelativeLayout scheduleback;
    List<ParseObject> ob;
    ParseFunctions customParse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //------------------------------------------------------ Add background image
        scheduleback=(RelativeLayout)findViewById(R.id.scheduleback);
        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.skybackground2);
        Bitmap bm = NativeStackBlur.process(back, 250);
        BitmapDrawable ob = new BitmapDrawable(getResources(), bm);
        scheduleback.setBackground(ob);
        //------------------------------------------------------ Add background image


        myschedule=(ListView)findViewById(R.id.myschedulelist);

        new RemoteDataTask().execute();

        myschedule.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                // TODO Auto-generated method stub
                //Log.v("long clicked","pos: " + pos);
                showAlert();
                return true;
            }
        });
    }


    // RemoteDataTask AsyncTask
    class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(mySchedule.this);
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
            schedules = new ArrayList<String>();
            try {

                // Locate the class table named "Chapters"
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Calendar");
                // look only for objects witch column is equal to the ParseObject course.
                // get data in an ascending list
                query.orderByDescending("time");
                // execute the query and get back a list of the data you where looking for
                ob = query.find();
                // make a for inside the list and set the data to the adapter
                for (ParseObject cal : ob) {
                    // Locate images in flag column
                    String item = cal.getString("eventDate");
                    //Log.d("objectStringMain",item);
                    schedules.add(item);
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
            adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, schedules);
            myschedule.setAdapter(adapter);

        }
    }

    public void showAlert()
    {
        new MaterialDialog.Builder(this)
                .title("Delete")
                .content("Are you sure you want to delete?")
                .positiveText("Yes")
                .negativeText("NO")
                .negativeColorRes(R.color.colorPrimary)
                .positiveColorRes(R.color.colorPrimary)
                .show();
    }

}
