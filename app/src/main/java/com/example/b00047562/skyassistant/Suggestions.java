package com.example.b00047562.skyassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Suggestions extends AppCompatActivity {

    ExpandableListView expListView;
    ExpandableListAdapter listAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

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

                switch(i1) {
                    case 0:
                        // Go to child #0 specific class.
                        Intent child0Intent = new Intent(getApplicationContext(), SuggestedItemView.class);
                        startActivity(child0Intent);
                        break;
                    case 1:
                        // Go to child #1 specific class.
                        Intent child1Intent = new Intent(getApplicationContext(), SuggestedItemView.class);
                        startActivity(child1Intent);
                        break;
                }
                return false;
            }
        });



    }
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Emirates Airlines");
        listDataHeader.add("Air Canada");
        listDataHeader.add("Etihad Airways");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("Flight 1");
        top250.add("Flight 2");


        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("Flight 1");
        nowShowing.add("Flight 2");
        nowShowing.add("Flight 3");
        nowShowing.add("Flight 4");
        nowShowing.add("Flight 5");
        nowShowing.add("Flight 6");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("Flight 1");
        comingSoon.add("Flight 2");
        comingSoon.add("Flight 3");
        comingSoon.add("Flight 4");
        comingSoon.add("Flight 5");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }


}
