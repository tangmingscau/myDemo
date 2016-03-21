package com.example.tonydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tonydemo.customView.floatButton.FloatActivity;
import com.example.tonydemo.customView.floatButton.FloatWindowTest;
import com.example.tonydemo.customView.recycleViewDemo.RecycleViewMainActivity;
import com.example.tonydemo.map.indoor.IndoorMainActivity;

public class MainActivity extends Activity {
private static String TAG=MainActivity.class.getSimpleName();
    TextView recycleViewAC=null;
    TextView indoorMap=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        recycleViewAC= (TextView) findViewById(R.id.recycleViewAC);
        recycleViewAC.setOnClickListener(myclick);

        indoorMap= (TextView) findViewById(R.id.indoorMap);
        indoorMap.setOnClickListener(myclick);

        TextView floatWindow= (TextView) findViewById(R.id.floatWindow);
        floatWindow.setOnClickListener(myclick);

        TextView appFloat= (TextView) findViewById(R.id.appFloatWindow);
        appFloat.setOnClickListener(myclick);
    }
    View.OnClickListener myclick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=null;
            switch (v.getId()){
                case R.id.recycleViewAC:
                    intent=new Intent(MainActivity.this, RecycleViewMainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.indoorMap:
                    intent =new Intent(MainActivity.this, IndoorMainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"室内地图有问题,被杀掉了",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.floatWindow:
                    intent =new Intent(MainActivity.this, FloatWindowTest.class);
                    startActivity(intent);
                    break;
                case R.id.appFloatWindow:
                    intent =new Intent(MainActivity.this, FloatActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

}
