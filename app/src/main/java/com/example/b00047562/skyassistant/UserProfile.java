package com.example.b00047562.skyassistant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.Toast;

import com.commit451.nativestackblur.NativeStackBlur;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class UserProfile extends AppCompatActivity implements OnClickListener{
    private EditText profilename;
    private EditText profileemail;
    private EditText profilephone;
    private Button changepassword;
    private SharedPreferences savedprofiledata;
    private ImageView profilepicture;
    private Button save;
    TableLayout userprofileback;
    String TAG="ParseErrorUserProfile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        profilename=(EditText)findViewById(R.id.name);
        profileemail=(EditText)findViewById(R.id.email);
        profilephone=(EditText)findViewById(R.id.phone);
        changepassword=(Button)findViewById(R.id.changepass);
        profilepicture=(ImageView)findViewById(R.id.profilepicture);
        save=(Button)findViewById(R.id.save);

        changepassword.setOnClickListener(this);
        save.setOnClickListener(this);
        savedprofiledata=getSharedPreferences("profiledata",MODE_PRIVATE);


        userprofileback=(TableLayout)findViewById(R.id.userprofileback);
        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.skybackground2);
        Bitmap bm = NativeStackBlur.process(back, 250);
        BitmapDrawable ob = new BitmapDrawable(getResources(), bm);
        userprofileback.setBackground(ob);



        //-----------------------------------------DATABASE NEEDED HERE----------------------------------------------

            String namefromdb = ParseUser.getCurrentUser().getUsername(); //get name from db
            String emailfromdb = ParseUser.getCurrentUser().getEmail(); //get email from db
            String phonefromdb = "+971-501956784"; //get phone from db
            //String piclocation = ""; //get pic from db

            profilename.setText(namefromdb);
            profileemail.setText(emailfromdb);
            profilephone.setText(phonefromdb);



    }

    @Override
    protected void onPause() {
        SharedPreferences.Editor edit = savedprofiledata.edit();
        edit.putString("profilename",profilename.getText().toString());
        edit.putString("profileemail",profileemail.getText().toString());
        edit.putString("profilephone",profilephone.getText().toString());
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
            fetchImage();
//            profilename.setText(savedprofiledata.getString("profilename", ""));
//            profileemail.setText(savedprofiledata.getString("profileemail", ""));
//            profilephone.setText(String.valueOf(savedprofiledata.getInt("profilephone", 0)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_user_profile, menu);
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
                //ParseUser.getCurrentUser().setPassword();
                break;
            case R.id.save:
                String nametodb= profilename.getText().toString();
                String emailtodb= profileemail.getText().toString();
                String phonetodb= profilephone.getText().toString();
                ParseUser.getCurrentUser().setEmail(emailtodb);
                ParseUser.getCurrentUser().setUsername(nametodb);

                Toast.makeText(UserProfile.this, "Profile Saved !", Toast.LENGTH_SHORT).show();
                finish();

                //-----------------------------------------DATABASE NEEDED HERE----------------------------------------------
                //update the db with changes
              ;
                break;
        }
    }

    private void fetchImage() {


        ParseUser currentUser = ParseUser.getCurrentUser();
        currentUser.fetchInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    ParseFile file = (ParseFile) object.get("displaypic");
                    if (file != null) {
                        file.getDataInBackground(new GetDataCallback() {
                            public void done(byte[] data, ParseException e) {
                                if (e == null) {
                                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                    if (bitmap != null) {
                                        profilepicture.setImageBitmap(bitmap);
                                    } else {
                                        Log.d(TAG, "file null?");
                                    }
                                } else {
                                    Log.d(TAG, "ParseFile ParseException: " + e.toString());
                                }
                            }
                        });
                    } else {
                        Log.d(TAG, "ParseFile is null");
                    }
                } else {
                    Log.d(TAG, "ParseException: " + e.toString());
                }
            }
        });
    }
}
