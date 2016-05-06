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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

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

import objects.MyValueFormatter;

public class AirlineView extends AppCompatActivity {
    BarChart chart;
    LineChart lineChart;
    private Typeface mTf;
    RelativeLayout airlineback;
    Spinner airline,yearspinner;
    ArrayList<Entry> lineentries;
    LineDataSet dataset;
    LineData linedata;
    BarDataSet barDataSet1;
    BarDataSet barDataSet2;
    BarData data;


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
        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.skybackground2);
        Bitmap bm = NativeStackBlur.process(back, 250);
        BitmapDrawable ob = new BitmapDrawable(getResources(), bm);
        airlineback.setBackground(ob);
        //------------------------------------------------------ Add background image

        // in this example, a LineChart is initialized from xml
        chart = (BarChart) findViewById(R.id.chart);
        lineChart=(LineChart)findViewById(R.id.chart2);
        airline=(Spinner)findViewById(R.id.airlineselect);
        yearspinner=(Spinner)findViewById(R.id.yearspinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Airlines, R.layout.spinner_items);
        adapter.setDropDownViewResource(R.layout.spinner_items_list);
        airline.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.Years, R.layout.spinner_items);
        adapter2.setDropDownViewResource(R.layout.spinner_items_list);
        yearspinner.setAdapter(adapter2);

        yearspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch(position)
                {
                    case 0:
                        updateGraphs("Air Canada",  new float[]{234f,90f,788f,46f,543f,109f,65f,52f,289f,367f,121f,445f},getDataSet3() );

                        break;

                    case 1:
                        updateGraphs("Emirates Airlines",new float[]{28f,90f,34f,46f,98f,109f,340f,23f,560f,367f,347f,97f},getDataSet());
                        break;


                    case 2:
                        updateGraphs("Etihad Airways",new float[]{956f,123f,786f,344f,988f,777f,555f,786f,560f,888f,657f,986f},getDataSet2());

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });


        airline.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch(position)
                {
                    case 0:
                        updateGraphs("Air Canada",new float[]{28f,90f,34f,46f,98f,109f,340f,23f,560f,367f,347f,97f},getDataSet());

                        break;

                    case 1:
                        updateGraphs("Emirates Airlines",new float[]{956f,123f,786f,344f,988f,777f,555f,786f,560f,888f,657f,986f},getDataSet2());
                        break;


                    case 2:
                        updateGraphs("Etihad Airways",new float[]{234f,90f,78f,46f,543f,109f,656f,52f,232f,367f,121f,345f},getDataSet3());

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });


        // creating list of entry

        //--------------------------------------------------------------------------------------------


//        ArrayList<BarEntry> entries = new ArrayList<>();
//        entries.add(new BarEntry(4f, 0));
//        entries.add(new BarEntry(8f, 1));
//        entries.add(new BarEntry(6f, 2));
//        entries.add(new BarEntry(12f, 3));
//        entries.add(new BarEntry(18f, 4));
//        entries.add(new BarEntry(9f, 5));
//        entries.add(new BarEntry(9f, 6));
//        entries.add(new BarEntry(9f, 7));
//        entries.add(new BarEntry(9f, 8));
//        entries.add(new BarEntry(9f, 9));
//        entries.add(new BarEntry(9f, 10));
//        entries.add(new BarEntry(9f, 11));
    }


    private void updateGraphs(String airline,float[]arr,ArrayList<IBarDataSet> bardataset)
    {
        data = new BarData(getXAxisValues(),bardataset);
        chart.setData(data);
        chart.setDescription("");
        chart.animateXY(2000, 2000);
        chart.invalidate();
        chart.setTouchEnabled(true);
        chart.setPinchZoom(true);
        chart.setScaleEnabled(true);


        lineentries = new ArrayList<>();
        lineentries.add(new Entry(arr[0], 0));
        lineentries.add(new Entry(arr[1], 1));
        lineentries.add(new Entry(arr[2], 2));
        lineentries.add(new Entry(arr[3], 3));
        lineentries.add(new Entry(arr[4], 4));
        lineentries.add(new Entry(arr[5], 5));
        lineentries.add(new Entry(arr[6], 6));
        lineentries.add(new Entry(arr[7], 7));
        lineentries.add(new Entry(arr[8], 8));
        lineentries.add(new Entry(arr[9], 9));
        lineentries.add(new Entry(arr[10], 10));
        lineentries.add(new Entry(arr[11], 11));

        lineChart.animateXY(2000, 2000);
        lineChart.setTouchEnabled(true);
        lineChart.setPinchZoom(true);
        lineChart.setScaleEnabled(true);
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("JAN");
        labels.add("FEB");
        labels.add("MAR");
        labels.add("APR");
        labels.add("MAY");
        labels.add("JUN");
        labels.add("JUL");
        labels.add("AUG");
        labels.add("SEP");
        labels.add("OCT");
        labels.add("NOV");
        labels.add("DEC");

        dataset = new LineDataSet(lineentries, "Average no. of Reservations for "+airline);
        dataset.setLineWidth(10);
        dataset.setValueTextSize(10);

        linedata = new LineData(labels, dataset);
        lineChart.setData(linedata); // set the data and list of lables into chart
        lineChart.setDescription("");  // set the description


        lineChart.notifyDataSetChanged();
        chart.notifyDataSetChanged(); // let the chart know it's data changed
        chart.invalidate(); // refresh
        lineChart.invalidate();
    }






    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("JAN");
        xAxis.add("FEB");
        xAxis.add("MAR");
        xAxis.add("APR");
        xAxis.add("MAY");
        xAxis.add("JUN");
        xAxis.add("JUL");
        xAxis.add("AUG");
        xAxis.add("SEP");
        xAxis.add("OCT");
        xAxis.add("NOV");
        xAxis.add("DEC");
        return xAxis;
    }
    private ArrayList<IBarDataSet> getDataSet() {
        ArrayList<IBarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(5600.000f, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(5700.000f, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(4700.000f, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(12000.000f, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(7800.000f, 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(6540.000f, 5); // Jun
        valueSet1.add(v1e6);
        BarEntry v1e7 = new BarEntry(9000.000f, 6);
        valueSet1.add(v1e7);
        BarEntry v1e8 = new BarEntry(14000.000f, 7);
        valueSet1.add(v1e8);
        BarEntry v1e9 = new BarEntry(10000.000f, 8);
        valueSet1.add(v1e9);
        BarEntry v1e10 = new BarEntry(9300.000f, 9);
        valueSet1.add(v1e10);
        BarEntry v1e11= new BarEntry(9500.000f, 10);
        valueSet1.add(v1e11);
        BarEntry v1e12= new BarEntry(13000.000f, 11);
        valueSet1.add(v1e12);

        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(10500.000f, 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(12400.000f, 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(1200.000f, 2); // Mar
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(6000.000f, 3); // Apr
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(8700.000f, 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(3400.000f, 5); // Jun
        valueSet2.add(v2e6);
        BarEntry v2e7 = new BarEntry(2200.000f, 6);
        valueSet2.add(v2e7);
        BarEntry v2e8 = new BarEntry(4560.000f, 7);
        valueSet2.add(v2e8);
        BarEntry v2e9 = new BarEntry(5677.000f, 8);
        valueSet2.add(v2e9);
        BarEntry v2e10 = new BarEntry(4300.000f, 9);
        valueSet2.add(v2e10);
        BarEntry v2e11 = new BarEntry(12500.000f, 10);
        valueSet2.add(v2e11);
        BarEntry v2e12 = new BarEntry(8000.000f, 11);
        valueSet2.add(v2e12);

        barDataSet1 = new BarDataSet(valueSet1, "Air Canada");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        barDataSet1.setValueTextSize(10);
        barDataSet1.setValueTextColor(R.color.textColor);
        barDataSet2 = new BarDataSet(valueSet2, "Other Airlines");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet2.setValueTextSize(10);
        barDataSet2.setValueTextColor(R.color.textColor);


        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;
    }
    private ArrayList<IBarDataSet> getDataSet2() {
        ArrayList<IBarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(8700.000f, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(12700.000f, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(5700.000f, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(17000.000f, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(4500.000f, 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(9540.000f, 5); // Jun
        valueSet1.add(v1e6);
        BarEntry v1e7 = new BarEntry(5060.000f, 6);
        valueSet1.add(v1e7);
        BarEntry v1e8 = new BarEntry(11000.000f, 7);
        valueSet1.add(v1e8);
        BarEntry v1e9 = new BarEntry(9800.000f, 8);
        valueSet1.add(v1e9);
        BarEntry v1e10 = new BarEntry(10200.000f, 9);
        valueSet1.add(v1e10);
        BarEntry v1e11= new BarEntry(9580.000f, 10);
        valueSet1.add(v1e11);
        BarEntry v1e12= new BarEntry(13200.000f, 11);
        valueSet1.add(v1e12);

        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(1500.000f, 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(9000.000f, 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(12000.000f, 2); // Mar
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(6000.000f, 3); // Apr
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(2000.000f, 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(8700.000f, 5); // Jun
        valueSet2.add(v2e6);
        BarEntry v2e7 = new BarEntry(8560.000f, 6);
        valueSet2.add(v2e7);
        BarEntry v2e8 = new BarEntry(6700.000f, 7);
        valueSet2.add(v2e8);
        BarEntry v2e9 = new BarEntry(10000.000f, 8);
        valueSet2.add(v2e9);
        BarEntry v2e10 = new BarEntry(12300.000f, 9);
        valueSet2.add(v2e10);
        BarEntry v2e11 = new BarEntry(5700.000f, 10);
        valueSet2.add(v2e11);
        BarEntry v2e12 = new BarEntry(2300.000f, 11);
        valueSet2.add(v2e12);

        barDataSet1 = new BarDataSet(valueSet1, "Emirates Airlines");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        barDataSet1.setValueTextSize(10);
        barDataSet1.setValueTextColor(R.color.textColor);
        barDataSet2 = new BarDataSet(valueSet2, "Other Airlines");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet2.setValueTextSize(10);
        barDataSet2.setValueTextColor(R.color.textColor);


        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;

    }
    private ArrayList<IBarDataSet> getDataSet3() {
        ArrayList<IBarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(8700.000f, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(9300.000f, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(4200.000f, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(6000.000f, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(19000.000f, 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(4840.000f, 5); // Jun
        valueSet1.add(v1e6);
        BarEntry v1e7 = new BarEntry(9500.000f, 6);
        valueSet1.add(v1e7);
        BarEntry v1e8 = new BarEntry(12000.000f, 7);
        valueSet1.add(v1e8);
        BarEntry v1e9 = new BarEntry(10500.000f, 8);
        valueSet1.add(v1e9);
        BarEntry v1e10 = new BarEntry(9900.000f, 9);
        valueSet1.add(v1e10);
        BarEntry v1e11= new BarEntry(13450.000f, 10);
        valueSet1.add(v1e11);
        BarEntry v1e12= new BarEntry(8650.000f, 11);
        valueSet1.add(v1e12);

        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(10050.000f, 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(9670.000f, 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(12600.000f, 2); // Mar
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(6800.000f, 3); // Apr
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(2050.000f, 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(4567.000f, 5); // Jun
        valueSet2.add(v2e6);
        BarEntry v2e7 = new BarEntry(4500.000f, 6);
        valueSet2.add(v2e7);
        BarEntry v2e8 = new BarEntry(8090.000f, 7);
        valueSet2.add(v2e8);
        BarEntry v2e9 = new BarEntry(14560.000f, 8);
        valueSet2.add(v2e9);
        BarEntry v2e10 = new BarEntry(13500.000f, 9);
        valueSet2.add(v2e10);
        BarEntry v2e11 = new BarEntry(14600.000f, 10);
        valueSet2.add(v2e11);
        BarEntry v2e12 = new BarEntry(8000.000f, 11);
        valueSet2.add(v2e12);

        barDataSet1 = new BarDataSet(valueSet1, "Eithad Airways");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        barDataSet1.setValueTextSize(10);
        barDataSet1.setValueTextColor(R.color.textColor);
        barDataSet1.setValueFormatter(new MyValueFormatter());

        barDataSet2 = new BarDataSet(valueSet2, "Other Airlines");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet2.setValueTextSize(10);
        barDataSet2.setValueTextColor(R.color.textColor);
        barDataSet2.setValueFormatter(new MyValueFormatter());


        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;

    }
}
