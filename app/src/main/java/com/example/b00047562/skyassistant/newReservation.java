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
import android.widget.RelativeLayout;

import com.commit451.nativestackblur.NativeStackBlur;

public class newReservation extends AppCompatActivity {


    RelativeLayout itemviewback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reservation);



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
