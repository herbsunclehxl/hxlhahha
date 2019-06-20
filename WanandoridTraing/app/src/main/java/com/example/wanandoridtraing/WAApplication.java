package com.example.wanandoridtraing;

import android.app.Application;
import android.content.Context;

public class WAApplication extends Application {

    public static Application mApplicationContext;


    public static boolean mIsLogin = false;


    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = this;
    }
}
