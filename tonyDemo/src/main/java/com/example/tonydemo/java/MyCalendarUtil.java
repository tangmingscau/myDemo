package com.example.tonydemo.java;


import com.example.tonydemo.util.LogUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tony on 16-4-26.
 */
public class MyCalendarUtil {
    private static String TAG = MyCalendarUtil.class.getSimpleName();
    private static boolean DEBUG = true;

    /**
     * 由yyyy-MM-dd格式化到M月d日
     *
     * @return
     */
    public static String getMonthDay(String dateString) {
        String result = null;
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M月d日");
            result = simpleDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LogUtils.i(TAG, result);
        return result;
    }
}
