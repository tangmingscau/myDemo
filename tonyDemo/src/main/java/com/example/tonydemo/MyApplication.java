package com.example.tonydemo;

import android.app.Application;
import android.util.Log;
import com.example.tonydemo.greendao.dao.DaoMaster;
import com.example.tonydemo.greendao.dao.DaoSession;
import com.example.tonydemo.greendao.dao.Note;
import com.example.tonydemo.greendao.dao.NoteDao;
import com.example.tonydemo.util.LogUtil;

import java.util.Date;

/**
 * Created by tony on 16-8-18.
 */
public class MyApplication extends Application {
    public static MyApplication myApplication;
    public DaoSession daoSession;


    public DaoMaster.DevOpenHelper helper;

    public DaoMaster daoMaster;


    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        LogUtil.i("MyAppLication", "Oncreate");




    }


}
