package com.example.b00047562.skyassistant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

public class Checkout extends AppCompatActivity implements TextView.OnEditorActionListener{
    boolean fieldsOK;
    private TextView FN;
    private TextView LN;
    private TextView AD;
    private TextView CI;
    private TextView SR;
    private TextView ZI;
    private TextView CO;
    private TextView PN;
    private TextView CT;
    private TextView CN;
    private TextView ED;
    private TextView CV;
    private EditText FNValue;
    private EditText LNValue;
    private EditText ADValue;
    private EditText CIValue;
    private EditText ZIValue;
    private EditText PNValue;
    private EditText CNValue;
    private EditText CVValue;
    private Spinner SRSpin;
    private Spinner COSpin;
    private Spinner CTSpin;
    private Spinner ED1Spin;
    private Spinner ED2Spin;
    private SharedPreferences savedValues;
    String FNString;
    String LNString;
    String ADString;
    String CIString;
    String ZIString;
    String PNString;
    String CNString;
    String CVString;
    String SRString;
    String COString;
    String CTString;
    String ED1String;
    String ED2String;

    MaterialDialog mdiag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        FNValue = (EditText) findViewById(R.id.Fn);
        LNValue = (EditText) findViewById(R.id.Ln);
        ADValue = (EditText) findViewById(R.id.address);
        CIValue = (EditText) findViewById(R.id.City);
        ZIValue = (EditText) findViewById(R.id.zc);
        PNValue = (EditText) findViewById(R.id.phone);
        CNValue = (EditText) findViewById(R.id.cn);
        CVValue = (EditText) findViewById(R.id.CVV);
        SRSpin = (Spinner) findViewById(R.id.state);
        COSpin = (Spinner) findViewById(R.id.country);
        CTSpin = (Spinner) findViewById(R.id.card);
        ED1Spin = (Spinner) findViewById(R.id.ex_day);
        ED2Spin = (Spinner) findViewById(R.id.ex_year);
        FNValue.setOnEditorActionListener(this);
        LNValue.setOnEditorActionListener(this);
        ADValue.setOnEditorActionListener(this);
        CIValue.setOnEditorActionListener(this);
        ZIValue.setOnEditorActionListener(this);
        PNValue.setOnEditorActionListener(this);
        CNValue.setOnEditorActionListener(this);
        CVValue.setOnEditorActionListener(this);
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.SR_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SRSpin.setAdapter(adapter1);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.CO_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        COSpin.setAdapter(adapter2);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.CT_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CTSpin.setAdapter(adapter3);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.ED1_array, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ED1Spin.setAdapter(adapter4);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.ED2_array, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ED2Spin.setAdapter(adapter5);

        Button completepurchase = (Button)findViewById(R.id.btn_submit);

        completepurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fieldsOK=validate(new EditText[]{FNValue, LNValue, ADValue,CIValue,ZIValue,PNValue,CNValue,CVValue});
                if(fieldsOK==true){
                    mdiag = new MaterialDialog.Builder(Checkout.this)
                            .title("Reservation")
                            .content("Purchasing...")
                            .progress(true, 0)
                            .show();
                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run() {
                            mdiag.dismiss();
                        }
                    }, 5000);
                    startActivity(new Intent(getApplicationContext(),Confirmation.class));
                }

                else
                {
                    new MaterialDialog.Builder(Checkout.this)
                            .title("Invalid")
                            .content("Please fill All fields")
                            .neutralText("OK")
                            .neutralColorRes(R.color.colorPrimary)
                            .show();
                }
            }
        });
    }

    public void StoreDB()
    {
        FNString = FNValue.getText().toString();
        LNString = LNValue.getText().toString();
        ADString = ADValue.getText().toString();
        CIString = CIValue.getText().toString();;
        ZIString = ZIValue.getText().toString();
        PNString = PNValue.getText().toString();
        CNString = CNValue.getText().toString();
        CVString = CVValue.getText().toString();
        SRString = (String) SRSpin.getSelectedItem();
        COString = (String) COSpin.getSelectedItem();
        CTString = (String) CTSpin.getSelectedItem();
        ED1String = (String) ED1Spin.getSelectedItem();
        ED2String = (String) ED2Spin.getSelectedItem();
        //store each of these values in the DataBase
    }

    @Override
    protected void onResume() {
        super.onResume();
        FNString = savedValues.getString("FNString", "");
        LNString = savedValues.getString("LNString", "");
        ADString = savedValues.getString("ADString", "");
        CIString = savedValues.getString("CIString", "");
        ZIString = savedValues.getString("ZIString", "");
        PNString = savedValues.getString("PNString", "");
        CNString = savedValues.getString("CNString", "");
        CVString = savedValues.getString("CVString", "");
        SRString = savedValues.getString("SRString", "");
        COString = savedValues.getString("COString", "");
        CTString = savedValues.getString("CTString", "");
        ED1String = savedValues.getString("ED1String", "");
        ED2String = savedValues.getString("ED2String", "");
    }

    @Override
    protected void onPause() {
        Editor editor = savedValues.edit();
        editor.putString("FNString", FNString);
        editor.putString("LNString", LNString);
        editor.putString("ADString", ADString);
        editor.putString("CIString", CIString);
        editor.putString("ZIString", ZIString);
        editor.putString("PNString", PNString);
        editor.putString("CNString", CNString);
        editor.putString("CVString", CVString);
        editor.putString("SRString", SRString);
        editor.putString("COString", COString);
        editor.putString("CTString", CTString);
        editor.putString("ED1String", ED1String);
        editor.putString("ED2String", ED2String);
        super.onPause();
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }

    private boolean validate(EditText[] fields){
        for(int i=0; i<fields.length; i++){
            EditText currentField=fields[i];
            if(TextUtils.isEmpty(currentField.getText().toString()))
            {
                return false;
            }
        }
        return true;
    }
}
