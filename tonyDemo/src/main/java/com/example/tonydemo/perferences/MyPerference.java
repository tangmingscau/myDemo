package com.example.tonydemo.perferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tony on 16-6-13.
 */
public class MyPerference {
    public static void setMaxCharge(Context context, int maxSet) {
        SharedPreferences preferences = context.getSharedPreferences("charge", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("chargeMax", maxSet);
        editor.commit();
    }

    public static int getMaxCharge(Context context) {
        int maxSet = 85;
        SharedPreferences preferences = context.getSharedPreferences("charge", Context.MODE_PRIVATE);
        maxSet = preferences.getInt("chargeMax", 85);
        return maxSet;
    }
}
