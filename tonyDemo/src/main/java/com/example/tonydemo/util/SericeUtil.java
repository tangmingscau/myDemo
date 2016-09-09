package com.example.tonydemo.util;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tony on 16-9-7.
 */
public class SericeUtil {
    private static Context mContext = null;
    private static SericeUtil sericeUtil = null;
    private Intent mIntent = null;
    private static ArrayList<Map<String, Object>> mDatas = null;
    private static final String INTENT = "intent";
    private static final String ACTION = "action";

    public static SericeUtil getInstance(Context context) {
        if (sericeUtil == null) {
            sericeUtil = new SericeUtil();
            mDatas = new ArrayList<>();
            mContext = context;
        }
        return sericeUtil;
    }

    public void startService(String action) {
        mIntent = new Intent();
        mIntent.setPackage(mContext.getPackageName());
        mIntent.setAction(action);
        mContext.startService(mIntent);
        Map<String, Object> map = new HashMap<>();
        map.put(INTENT, mIntent);
        map.put(ACTION, action);
        mDatas.add(map);
    }

    public void stopService(String action) {
        for (Map<String, Object> map : mDatas) {
            if (map.get(ACTION).equals(action)) {
                Intent intent = (Intent) map.get(INTENT);
                mContext.stopService(intent);
            }
        }
    }
}
