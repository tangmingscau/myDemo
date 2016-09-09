package com.example.tonydemo;

import android.app.Application;

import com.example.tonydemo.util.LogUtil;

/**
 * Created by tony on 16-8-18.
 */
public class MyApplication extends Application {
   public static MyApplication myApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication=this;
        LogUtil.i("MyAppLication","Oncreate");
    }
}
