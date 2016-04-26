package com.example.b00047562.skyassistant;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.commit451.nativestackblur.NativeStackBlur;

public class newReservation extends AppCompatActivity {


    RelativeLayout itemviewback;
    private TextView AirLine;
    private TextView Name;
    private TextView From;
    private TextView To;
    private TextView Carrier;
    private TextView Gate;
    private TextView Class;
    private TextView Date;
    private TextView Seat;
    private TextView BT;
    private TextView TicketNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reservation);
        AirLine = (TextView) findViewById(R.id.Airlines);
        Name = (TextView) findViewById(R.id.name);
        From = (TextView) findViewById(R.id.from);
        To = (TextView) findViewById(R.id.to);
        Carrier = (TextView) findViewById(R.id.carrier);
        Gate = (TextView) findViewById(R.id.gate);
        Class = (TextView) findViewById(R.id.textView11);
        Date = (TextView) findViewById(R.id.date);
        Seat = (TextView) findViewById(R.id.Seat);
        BT = (TextView) findViewById(R.id.bt);
        TicketNum = (TextView) findViewById(R.id.ticketnum);

        // replace "" with value you get from the database

        AirLine.setText("");
        Name.setText("");
        From.setText("");
        To.setText("");
        Carrier.setText("");
        Gate.setText("");
        Class.setText("");
        Date.setText("");
        Seat.setText("");
        BT.setText("");
        TicketNum.setText("");


        //------------------------------------------------------ Add background image
        itemviewback=(RelativeLayout)findViewById(R.id.itemviewback);
        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.skybackground2);
        Bitmap bm = NativeStackBlur.process(back, 250);
        BitmapDrawable ob = new BitmapDrawable(getResources(), bm);
        itemviewback.setBackground(ob);
        //------------------------------------------------------ Add background image


        Button checkout = (Button)findViewById(R.id.btn_reserve);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Checkout.class));
            }
        });
    }
}
