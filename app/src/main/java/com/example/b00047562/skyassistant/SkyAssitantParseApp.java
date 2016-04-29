package com.example.b00047562.skyassistant;

import android.app.Application;


import com.parse.Parse;


public class SkyAssitantParseApp extends Application {
    public static final String YOUR_APPLICATION_ID = "ZHzyMaMOpDocDFPYYIVAcxESDA7mc0p5rtBub73n";
    public static final String YOUR_CLIENT_KEY = "7H5F27AYraSwVRetIck16s7se4AX9pMyA86paHPu";

    @Override
    public void onCreate() {
        super.onCreate();
        // Add your initialization code here
            Parse.enableLocalDatastore(this);
            Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);

        // ParseObject testObject = new ParseObject("TestObject");
        // testObject.put("object1","Spiral");
        // testObject.saveInBackground();

    }
}
