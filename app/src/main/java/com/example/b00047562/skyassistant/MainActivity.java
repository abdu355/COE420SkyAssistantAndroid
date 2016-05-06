package com.example.b00047562.skyassistant;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.commit451.nativestackblur.NativeStackBlur;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import dbhandler.ParseFunctions;
import objects.Calendar;
import objects.Flight;

public class MainActivity extends AppCompatActivity {

    Button prefs,airview,sugg,myRes;
    ListView list;
    ArrayList<String> flights;
    ArrayAdapter<String> adapter;
    ParseUser currentUser;
    ProgressDialog mProgressDialog;
    RelativeLayout mainback;
    List<ParseObject> ob;
    ParseFunctions customParse;
    Calendar usercal;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.menu24);

        //setupDrawer();

        list=(ListView)findViewById(R.id.listView);
        prefs=(Button)findViewById(R.id.btn_prefs);
        myRes=(Button)findViewById(R.id.btn_myRes);
        airview=(Button)findViewById(R.id.btn_airlineview);
        sugg=(Button)findViewById(R.id.btn_suggestions);

        //------------------------------------------------------ Add background image
//        mainback=(RelativeLayout)findViewById(R.id.mainback);
//        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.skybackground2);
//        Bitmap bm = NativeStackBlur.process(back, 250);
//        BitmapDrawable ob = new BitmapDrawable(getResources(), bm);
//        mainback.setBackground(ob);
        //------------------------------------------------------ Add background image

        prefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),UserPreferences.class));
            }
        });
        airview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AirlineView.class));
            }
        });
        sugg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Suggestions.class));
            }
        });
        myRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),myReservations.class));
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent child0Intent = new Intent(getApplicationContext(), newReservation.class);
                child0Intent.putExtra("pointer",5-position);
                startActivity(child0Intent);
            }

        });


        //Dummy values


//        arritems.add("Flight 1\n\n\n\n\n\n4/11/16 13:20");
//        arritems.add("Flight 2\n\n\n\n\n\n4/11/16 13:20");
//        arritems.add("Flight 3\n\n\n\n\n\n4/11/16 13:20");
//        arritems.add("Flight 4\n\n\n\n\n\n4/11/16 13:20");
//        arritems.add("Flight 5\n\n\n\n\n\n4/11/16 13:20");
//        arritems.add("Flight 6\n\n\n\n\n\n4/11/16 13:20");
//        arritems.add("Flight 7\n\n\n\n\n\n4/11/16 13:20");
//        arritems.add("Flight 8\n\n\n\n\n\n4/11/16 13:20");
//        arritems.add("Flight 9\n\n\n\n\n\n4/11/16 13:20");


        // Adapter: You need three parameters 'the context, id of the layout (it will be where the data is shown),
        // and the array that contains the data

        customParse = new ParseFunctions();
        usercal = new Calendar("date","1234");
        //currentUser = ParseUser.getCurrentUser();//check if user logged in
        //} catch (ParseException e) {
        //    e.printStackTrace();
        //}
        if (currentUser == null) {
            //loadLoginView();
        }
        if(currentUser!=null)
             new RemoteDataTask().execute(); //get quick suggestion
    }
    public void loadLoginView() {
        Intent intent = new Intent(this, Login.class); //go to login activity
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.logout:
               //startActivity(new Intent(getApplicationContext(),LoginActivity.class)); //temporary logout
                ParseUser.logOut();//update Parse current user
                loadLoginView();//load login activity
                return true;
            case R.id.profile:
                startActivity(new Intent(this,UserProfile.class));
                return true;
            case R.id.settings:
                startActivity(new Intent(this,GeneralSettings.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);


        }
    }

    // RemoteDataTask AsyncTask
    class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(MainActivity.this);
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
            usercal = customParse.getParseCalendar(ParseUser.getCurrentUser(),"Calendar");
            String eventdateString = usercal.getEventDate();
            Date eventdate = customParse.returnDateformat(eventdateString);

            Log.d("CalendarMain",eventdateString);

            flights = new ArrayList<String>();
            try {

                // Locate the class table named "Chapters"
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Flight");
                // look only for objects witch column is equal to the ParseObject course.
                query.whereLessThanOrEqualTo("time",eventdate.getTime() );
                // get data in an ascending list
                query.orderByDescending("time");
                // execute the query and get back a list of the data you where looking for
                ob = query.find();
                // make a for inside the list and set the data to the adapter
                for (ParseObject flight : ob) {
                    // Locate images in flag column
                    String item = flight.getString("destinationLoc")+"\n\n\n\n\n\n"+flight.getString("departureDate");
                    Log.d("objectStringMain",item);
                    flights.add(item);
                }
                flights.add("New York\n\n\n\n\n\n4/11/16 13:20:00");
                flights.add("Sydney\n\n\n\n\n\n4/12/16 15:20:00");
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mProgressDialog.dismiss();
            adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, flights);
            list.setAdapter(adapter);

        }
    }
    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(currentUser!=null)
             new RemoteDataTask().execute();
    }

    @Override
    protected void onPause() {
        new RemoteDataTask().cancel(true);
        super.onPause();
    }

}
