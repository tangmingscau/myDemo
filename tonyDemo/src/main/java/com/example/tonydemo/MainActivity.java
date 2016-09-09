package com.example.tonydemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tonydemo.customView.floatButton.FloatActivity;
import com.example.tonydemo.customView.floatButton.FloatWindowTest;
import com.example.tonydemo.customView.keyboard.KeyboardActivity;
import com.example.tonydemo.customView.mySeekbar.ChargeSeekbarActivity;
import com.example.tonydemo.customView.mySeekbar.MySeekbarActivity;
import com.example.tonydemo.customView.pickview.PickViewActivity;
import com.example.tonydemo.customView.recycleViewDemo.RecycleViewMainActivity;
import com.example.tonydemo.customView.switchViewDemo.SwitchStatusActivity;
import com.example.tonydemo.customView.wheel.WheelViewAcitivity;
import com.example.tonydemo.face.CameraActivity;
import com.example.tonydemo.http.AirActivtity;
import com.example.tonydemo.java.MyCalendarUtil;
import com.example.tonydemo.map.indoor.IndoorMainActivity;
import com.example.tonydemo.util.LogUtils;

public class MainActivity extends Activity {
    private static String TAG = MainActivity.class.getSimpleName();
    TextView recycleViewAC = null;
    TextView indoorMap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        recycleViewAC = (TextView) findViewById(R.id.recycleViewAC);
        recycleViewAC.setOnClickListener(myclick);

        indoorMap = (TextView) findViewById(R.id.indoorMap);
        indoorMap.setOnClickListener(myclick);

        TextView floatWindow = (TextView) findViewById(R.id.floatWindow);
        floatWindow.setOnClickListener(myclick);

        TextView appFloat = (TextView) findViewById(R.id.appFloatWindow);
        appFloat.setOnClickListener(myclick);
        TextView switchState = (TextView) findViewById(R.id.switchState);
        switchState.setOnClickListener(myclick);

        TextView mySeekbar = (TextView) findViewById(R.id.mySeekbar);
        mySeekbar.setOnClickListener(myclick);
        TextView myWheelView = (TextView) findViewById(R.id.myWheelView);
        myWheelView.setOnClickListener(myclick);
        TextView mydialog = (TextView) findViewById(R.id.mydialog);
        mydialog.setOnClickListener(myclick);
        TextView keyboard = (TextView) findViewById(R.id.keyboardview);
        keyboard.setOnClickListener(myclick);
        TextView airView = (TextView) findViewById(R.id.airView);
        airView.setOnClickListener(myclick);
        TextView chargeSeekbar = (TextView) findViewById(R.id.chargeSeekbar);
        chargeSeekbar.setOnClickListener(myclick);
        TextView myPickView= (TextView) findViewById(R.id.myPickView);
        myPickView.setOnClickListener(myclick);
        TextView myFace= (TextView) findViewById(R.id.myFace);
        myFace.setOnClickListener(myclick);
        LogUtils.i(TAG, "dpi=%d", getResources().getDisplayMetrics().densityDpi);
        MyCalendarUtil.getMonthDay("2016-05-05");
        MyCalendarUtil.getMonthDay("2016-12-05");
        MyCalendarUtil.getMonthDay("2016-12-15");
        String s = getApplicationContext().getResources().getString(R.string.charge_max, 10);
        LogUtils.i(TAG, "s=%s", s);
    }

    View.OnClickListener myclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.recycleViewAC:
                    intent = new Intent(MainActivity.this, RecycleViewMainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.indoorMap:
                    intent = new Intent(MainActivity.this, IndoorMainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "室内地图有问题,被杀掉了", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.floatWindow:
                    intent = new Intent(MainActivity.this, FloatWindowTest.class);
                    startActivity(intent);
                    break;
                case R.id.appFloatWindow:
                    intent = new Intent(MainActivity.this, FloatActivity.class);
                    startActivity(intent);
                    break;
                case R.id.switchState:
                    intent = new Intent(MainActivity.this, SwitchStatusActivity.class);
                    startActivity(intent);
                    break;
                case R.id.mySeekbar:
                    intent = new Intent(MainActivity.this, MySeekbarActivity.class);
                    startActivity(intent);
                    break;
                case R.id.myWheelView:
                    intent = new Intent(MainActivity.this, WheelViewAcitivity.class);
                    startActivity(intent);
                    break;
                case R.id.mydialog:
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("你好")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // FIRE ZE MISSILES!
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                                }
                            });
                    builder.create();
                    builder.show();
                    break;
                case R.id.keyboardview:
                    intent = new Intent(MainActivity.this, KeyboardActivity.class);
                    startActivity(intent);
                    break;
                case R.id.airView:
                    intent = new Intent(MainActivity.this, AirActivtity.class);
                    startActivity(intent);
                    break;
                case R.id.chargeSeekbar:
                    intent = new Intent(MainActivity.this, ChargeSeekbarActivity.class);
                    startActivity(intent);
                    break;
                case R.id.myPickView:
                    intent = new Intent(MainActivity.this, PickViewActivity.class);
                    startActivity(intent);
                    break;
                case R.id.myFace:
                    intent = new Intent(MainActivity.this, CameraActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

    }


}
