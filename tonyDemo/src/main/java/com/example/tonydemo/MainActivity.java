package com.example.tonydemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.tonydemo.customView.recycleViewDemo.RecycleViewMainActivity;
import com.example.tonydemo.instanceofTest.animal;
import com.example.tonydemo.instanceofTest.dog;

public class MainActivity extends Activity {
private static String TAG=MainActivity.class.getSimpleName();
    TextView recycleViewAC=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        recycleViewAC= (TextView) findViewById(R.id.recycleViewAC);
        recycleViewAC.setOnClickListener(myclick);
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
            }
        }
    };

}
