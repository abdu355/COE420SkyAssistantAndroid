package com.example.b00047562.skyassistant;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.commit451.nativestackblur.NativeStackBlur;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button prefs,airview,sugg;
    ListView list;
    ArrayList<String> arritems;
    ArrayAdapter<String> adapter;

    RelativeLayout mainback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        list=(ListView)findViewById(R.id.listView);
        prefs=(Button)findViewById(R.id.btn_prefs);
        airview=(Button)findViewById(R.id.btn_airlineview);
        sugg=(Button)findViewById(R.id.btn_suggestions);

        //------------------------------------------------------ Add background image
        mainback=(RelativeLayout)findViewById(R.id.mainback);
        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.skybackground2);
        Bitmap bm = NativeStackBlur.process(back, 250);
        BitmapDrawable ob = new BitmapDrawable(getResources(), bm);
        mainback.setBackground(ob);
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

        arritems = new ArrayList<String>();

        //Dummy values
        arritems.add("Flight 1\n\n\n\n\n\n4/11/16 13:20");
        arritems.add("Flight 2\n\n\n\n\n\n4/11/16 13:20");
        arritems.add("Flight 3\n\n\n\n\n\n4/11/16 13:20");
        arritems.add("Flight 4\n\n\n\n\n\n4/11/16 13:20");
        arritems.add("Flight 5\n\n\n\n\n\n4/11/16 13:20");
        arritems.add("Flight 6\n\n\n\n\n\n4/11/16 13:20");
        arritems.add("Flight 7\n\n\n\n\n\n4/11/16 13:20");
        arritems.add("Flight 8\n\n\n\n\n\n4/11/16 13:20");
        arritems.add("Flight 9\n\n\n\n\n\n4/11/16 13:20");
        // Adapter: You need three parameters 'the context, id of the layout (it will be where the data is shown),
        // and the array that contains the data
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arritems);

        // Here, you set the data in your ListView
        list.setAdapter(adapter);


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
               startActivity(new Intent(getApplicationContext(),LoginActivity.class)); //temporary logout
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
