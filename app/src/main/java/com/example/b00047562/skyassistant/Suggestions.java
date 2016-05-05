package com.example.b00047562.skyassistant;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

import com.commit451.nativestackblur.NativeStackBlur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Suggestions extends AppCompatActivity {

    ExpandableListView expListView;
    ExpandableListAdapter listAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    RelativeLayout suggback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        expListView = (ExpandableListView) findViewById(R.id.expandableListView);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {

                if(i==0) { //group 1
                    switch (i1) {
                        case 0:
                            // Go to child #0 specific class.
                            Intent child0Intent = new Intent(getApplicationContext(), newReservation.class);
                            child0Intent.putExtra("pointer",3);
                            startActivity(child0Intent);
                            break;
                        case 1:
                            // Go to child #1 specific class.
                            Intent child1Intent = new Intent(getApplicationContext(), newReservation.class);
                            child1Intent.putExtra("pointer",5);
                            startActivity(child1Intent);
                            break;
                    }
                }
                else if (i==1) //group 2
                {
                    switch (i1) {
                        case 0:
                            // Go to child #0 specific class.
                            Intent child0Intent = new Intent(getApplicationContext(), newReservation.class);
                            child0Intent.putExtra("pointer",4);
                            startActivity(child0Intent);
                            break;
                    }
                }
                else if (i==2) //group 3
                {
                    switch (i1) {
                        case 0:
                            // Go to child #0 specific class.
                            Intent child0Intent = new Intent(getApplicationContext(), newReservation.class);
                            child0Intent.putExtra("pointer",2);
                            startActivity(child0Intent);
                            break;
                        case 1:
                            // Go to child #1 specific class.
                            Intent child1Intent = new Intent(getApplicationContext(), newReservation.class);
                            child1Intent.putExtra("pointer",1);
                            startActivity(child1Intent);
                            break;
                        case 2:
                            // Go to child #1 specific class.
                            Intent child2Intent = new Intent(getApplicationContext(), newReservation.class);
                            child2Intent.putExtra("pointer",0);
                            startActivity(child2Intent);
                            break;
                    }
                }
                return false;
            }
        });
        //------------------------------------------------------ Add background image
        suggback=(RelativeLayout)findViewById(R.id.suggback);
        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.skybackground2);
        Bitmap bm = NativeStackBlur.process(back, 250);
        BitmapDrawable ob = new BitmapDrawable(getResources(), bm);
        suggback.setBackground(ob);
        //------------------------------------------------------ Add background image



    }
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Emirates Airlines");
        listDataHeader.add("Air Canada");
        listDataHeader.add("Etihad Airways");

        // Adding child data
        List<String> emirates = new ArrayList<String>();
        emirates.add("Luxembourg\n12/06/2017 02:00:00");
        emirates.add("Leipzig\n23/05/2016 11:30:00");


        List<String> aircanada = new ArrayList<String>();
        aircanada.add("Brazil\n12/06/2017 02:00:00");

        List<String> etihadairways = new ArrayList<String>();
        etihadairways.add("Luxembourg\n12/06/2017 02:00:00");
        etihadairways.add("Delhi\n03/02/2016 07:00:00");
        etihadairways.add("Sacremento\n03/03/2016 07:00:00");


        listDataChild.put(listDataHeader.get(0), emirates); // Header, Child data
        listDataChild.put(listDataHeader.get(1), aircanada);
        listDataChild.put(listDataHeader.get(2), etihadairways);
    }


}
