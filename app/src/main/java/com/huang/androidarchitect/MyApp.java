package com.huang.androidarchitect;

import android.app.Application;
import android.util.Log;

public class MyApp extends Application {

    public static volatile  boolean isHotInit = false;
    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.i("hjq","onTerminate");
    }

}
