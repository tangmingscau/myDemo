package com.example.tonydemo.customView.wheel;

import android.app.Activity;
import android.os.Bundle;

import com.example.tonydemo.LogUtils;
import com.example.tonydemo.R;

import java.util.ArrayList;

/**
 * Created by tony on 16-4-14.
 */
public class WheelViewAcitivity extends Activity {
    private static String TAG = WheelViewAcitivity.class.getSimpleName();
    WheelView mWheelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wheelview);
        mWheelView = (WheelView) findViewById(R.id.wheelView);
        mWheelView.setData(getData1());
        mWheelView.setDefault(10);
        mWheelView.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                LogUtils.i(TAG, text);
            }

            @Override
            public void selecting(int id, String text) {

            }
        });
    }

    private ArrayList<String> getData1() {
        ArrayList<String> mDatas = new ArrayList<>();
        mDatas.add("LO");
        for (int i = 18; i <= 32; i++) {
            mDatas.add(i + "");
        }
        mDatas.add("HI");
        return mDatas;
    }
}
