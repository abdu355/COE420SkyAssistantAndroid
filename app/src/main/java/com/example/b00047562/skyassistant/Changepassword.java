package com.example.b00047562.skyassistant;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;

public class Changepassword extends AppCompatActivity implements OnClickListener{

    private EditText previouspass;
    private EditText newpass;
    private EditText confirmpass;
    private Button changepass;
    private String correctpreviouspasswordstring;
    String newdummy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        previouspass=(EditText)findViewById(R.id.previouspass);
        newpass=(EditText)findViewById(R.id.newpass);
        confirmpass=(EditText)findViewById(R.id.confirmpass);
        changepass=(Button)findViewById(R.id.changepass);
        changepass.setOnClickListener(this);

        //-----------------------------------------DATABASE NEEDED HERE----------------------------------------------
        correctpreviouspasswordstring="password"; //get correct previous password from database
    }


    @Override
    public void onClick(View v) {
        String previousdummy=previouspass.getText().toString();
        newdummy=newpass.getText().toString();
        String confirmdummy=confirmpass.getText().toString();
        if (previousdummy=="" || newdummy=="" || confirmdummy==""){
            Toast.makeText(getApplicationContext(),"One of the fields is empty!", Toast.LENGTH_SHORT);
        }
        else if(previousdummy!=correctpreviouspasswordstring) {
            Toast.makeText(getApplicationContext(),"Please Enter the correct Password!", Toast.LENGTH_SHORT);
        }
        else if (newdummy!=newdummy) {
            Toast.makeText(getApplicationContext(),"The confirmation password doesn't match the new one!", Toast.LENGTH_SHORT);
        }
        else{
            changepassword();
        }
    }
    public void changepassword()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("confirm change");
        builder.setMessage("Are you sure you want to change your password?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //-----------------------------------------DATABASE NEEDED HERE----------------------------------------------
                ParseUser.getCurrentUser().setPassword(newdummy);

                Toast.makeText(getApplicationContext(),"Password Changed!", Toast.LENGTH_SHORT);
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

}
