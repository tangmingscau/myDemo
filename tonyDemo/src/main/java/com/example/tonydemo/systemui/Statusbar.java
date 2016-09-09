package com.example.tonydemo.systemui;

import android.app.Activity;
import android.os.Bundle;

import com.example.tonydemo.util.LogUtils;

/**
 * Created by tony on 16-3-19.
 */
public class Statusbar extends Activity{
    private static String TAG=Statusbar.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String key="status_bar_height";
        int resourceId = getResources().getIdentifier(key, "dimen", "android");
        int result = getResources().getDimensionPixelSize(resourceId);
        LogUtils.i(TAG,"result=%d",result);
    }
}
