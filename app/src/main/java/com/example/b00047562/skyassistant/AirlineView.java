package com.example.b00047562.skyassistant;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.commit451.nativestackblur.NativeStackBlur;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class AirlineView extends AppCompatActivity {
    BarChart chart;
    LineChart lineChart;
    private Typeface mTf;
    RelativeLayout airlineback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airline_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//------------------------------------------------------ Add background image
        airlineback=(RelativeLayout)findViewById(R.id.airlineback);
        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.rsz_1itemback);
        Bitmap bm = NativeStackBlur.process(back, 250);
        BitmapDrawable ob = new BitmapDrawable(getResources(), bm);
        airlineback.setBackground(ob);
        //------------------------------------------------------ Add background image

        // in this example, a LineChart is initialized from xml
        chart = (BarChart) findViewById(R.id.chart);
        lineChart=(LineChart)findViewById(R.id.chart2);




        // creating list of entry
        ArrayList<Entry> lineentries = new ArrayList<>();
        lineentries.add(new Entry(4f, 0));
        lineentries.add(new Entry(8f, 1));
        lineentries.add(new Entry(6f, 2));
        lineentries.add(new Entry(2f, 3));
        lineentries.add(new Entry(18f, 4));
        lineentries.add(new Entry(9f, 5));
        lineChart.animateXY(2000, 2000);
        lineChart.setTouchEnabled(true);
        lineChart.setPinchZoom(true);
        lineChart.setScaleEnabled(true);
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        LineDataSet dataset = new LineDataSet(lineentries, "# of Reservations");
        dataset.setLineWidth(10);
        dataset.setValueTextSize(10);
        LineData linedata = new LineData(labels, dataset);
        lineChart.setData(linedata); // set the data and list of lables into chart
        lineChart.setDescription("");  // set the description



        BarData data = new BarData(getXAxisValues(),getDataSet());
        chart.setData(data);
        chart.setDescription("");
        chart.animateXY(2000, 2000);
        chart.invalidate();
        chart.setTouchEnabled(true);
        chart.setPinchZoom(true);
        chart.setScaleEnabled(true);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(9f, 5));
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("JAN");
        xAxis.add("FEB");
        xAxis.add("MAR");
        xAxis.add("APR");
        xAxis.add("MAY");
        xAxis.add("JUN");
        return xAxis;
    }
    private ArrayList<IBarDataSet> getDataSet() {
        ArrayList<IBarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(110.000f, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(40.000f, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(60.000f, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(30.000f, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(90.000f, 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(100.000f, 5); // Jun
        valueSet1.add(v1e6);

        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(150.000f, 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(90.000f, 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(120.000f, 2); // Mar
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(60.000f, 3); // Apr
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(20.000f, 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(80.000f, 5); // Jun
        valueSet2.add(v2e6);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Emirates Airlines");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        barDataSet1.setValueTextSize(10);
        barDataSet1.setValueTextColor(R.color.textColor);
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Other Airlines");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet2.setValueTextSize(10);
        barDataSet2.setValueTextColor(R.color.textColor);


        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;



    }
}
