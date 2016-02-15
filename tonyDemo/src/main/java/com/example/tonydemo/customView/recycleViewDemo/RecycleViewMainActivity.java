package com.example.tonydemo.customView.recycleViewDemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.tonydemo.R;
import com.example.tonydemo.customView.recycleViewDemo.ui.AnimationGridActivity;
import com.example.tonydemo.customView.recycleViewDemo.ui.AnimationListActivity;
import com.example.tonydemo.customView.recycleViewDemo.ui.GridActivity;
import com.example.tonydemo.customView.recycleViewDemo.ui.ListActivity;


public class RecycleViewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview_activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
    }

    public void linear(View viw){
        Intent intent = new Intent(this,ListActivity.class);
        startActivity(intent);
    }

    public void grid(View viw){
        Intent intent = new Intent(this,GridActivity.class);
        startActivity(intent);
    }

    public void adapterList(View viw){
        Intent intent = new Intent(this, AnimationListActivity.class);
        startActivity(intent);
    }

    public void adapterGrid(View viw){
        Intent intent = new Intent(this, AnimationGridActivity.class);
        startActivity(intent);
    }

}
