package com.example.mywallet;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ObjectBox.create(this);
    }
}