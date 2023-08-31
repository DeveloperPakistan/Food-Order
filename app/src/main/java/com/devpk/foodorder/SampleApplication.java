package com.devpk.foodorder;

import android.app.Application;

import com.beardedhen.androidbootstrap.TypefaceProvider;

public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceProvider.registerDefaultIconSets();
    }

}
