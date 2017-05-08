package com.example.uniwien.gesunderleben;

import android.app.Application;
import android.content.Context;

/**
 * Helper class to get Application Context
 */

public class MyApplication extends Application {

    private static Context context;
    private static int selectedtColor = 3;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();

        //Initialize User
        User.loadUser();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }

    public static int getSelectedtColor() {
        return MyApplication.selectedtColor;
    }
}