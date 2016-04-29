package com.example.b00047562.skyassistant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class UserProfile extends AppCompatActivity implements OnClickListener{
    private EditText profilename;
    private EditText profileemail;
    private EditText profilephone;
    private Button changepassword;
    private SharedPreferences savedprofiledata;
    private ImageView profilepicture;
    private Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        profilename=(EditText)findViewById(R.id.name);
        profileemail=(EditText)findViewById(R.id.email);
        profilephone=(EditText)findViewById(R.id.phone);
        changepassword=(Button)findViewById(R.id.changepass);
        profilepicture=(ImageView)findViewById(R.id.profilepicture);
        save=(Button)findViewById(R.id.save);

        changepassword.setOnClickListener(this);
        save.setOnClickListener(this);
        savedprofiledata=getSharedPreferences("profiledata",MODE_PRIVATE);
        //-----------------------------------------DATABASE NEEDED HERE----------------------------------------------
            String namefromdb = ""; //get name from db
            String emailfromdb = ""; //get email from db
            int phonefromdb = 0; //get phone from db
            String piclocation = ""; //get pic from db

            profilename.setText(namefromdb);
            profileemail.setText(emailfromdb);
            profilephone.setText(Integer.toString(phonefromdb));



    }

    @Override
    protected void onPause() {
        SharedPreferences.Editor edit = savedprofiledata.edit();
        edit.putString("profilename",profilename.getText().toString());
        edit.putString("profileemail",profileemail.getText().toString());
        edit.putInt("profilephone",Integer.parseInt(profilephone.getText().toString()));
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
            profilename.setText(savedprofiledata.getString("profilename", ""));
            profileemail.setText(savedprofiledata.getString("profileemail", ""));
            profilephone.setText(savedprofiledata.getInt("profilephone", 0));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changepass:
                startActivity(new Intent(getApplicationContext(), Changepassword.class));
                break;
            case R.id.save:
                String nametodb= profilename.getText().toString();
                String emailtodb= profileemail.getText().toString();
                int phonetodb= Integer.parseInt(profilephone.getText().toString());

                //-----------------------------------------DATABASE NEEDED HERE----------------------------------------------
                //update the db with changes
                break;
        }
    }
}
