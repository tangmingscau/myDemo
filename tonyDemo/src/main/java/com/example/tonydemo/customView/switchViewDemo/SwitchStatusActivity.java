package com.example.tonydemo.customView.switchViewDemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewAnimator;

import com.example.tonydemo.LogUtils;
import com.example.tonydemo.R;

/**
 * Created by tony on 16-1-20.
 */
public class SwitchStatusActivity extends Activity implements View.OnClickListener {
    private static String TAG=SwitchStatusActivity.class.getSimpleName();
    ViewAnimator mode=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.switch_statusview);
        mode= (ViewAnimator) findViewById(R.id.mode);
        mode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mode.showNext();
        LogUtils.i(TAG,"mode=%d,id=%s",mode.getDisplayedChild(),mode.getChildAt(mode.getDisplayedChild())+"");
    }
}
