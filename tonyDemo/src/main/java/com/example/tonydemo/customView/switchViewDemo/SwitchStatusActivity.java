package com.example.tonydemo.customView.switchViewDemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ViewAnimator;

import com.example.tonydemo.util.LogUtils;
import com.example.tonydemo.R;

/**
 * Created by tony on 16-1-20.
 */
public class SwitchStatusActivity extends Activity implements View.OnClickListener {
    private static String TAG = SwitchStatusActivity.class.getSimpleName();
    ViewAnimator mode = null;
    LinearLayout myLinear = null;
    ViewAnimator mode2;
    Button buttonNormal=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.switch_statusview);
        mode = (ViewAnimator) findViewById(R.id.mode);
        mode2 = (ViewAnimator) findViewById(R.id.mode2);
        myLinear = (LinearLayout) findViewById(R.id.myLinear);
        buttonNormal= (Button) findViewById(R.id.buttonNormal);
        mode2.setOnClickListener(this);
        myLinear.setOnClickListener(this);
        mode.setOnClickListener(this);
        buttonNormal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mode:
                mode.setDisplayedChild(1);
                LogUtils.i(TAG, "mode=%d,id=%s", mode.getDisplayedChild(), mode.getChildAt(mode.getDisplayedChild()) + "");
                break;
            case R.id.mode2:
                LogUtils.i(TAG, "mode=%d,id=%s", mode2.getDisplayedChild(), mode2.getChildAt(mode2.getDisplayedChild()) + "");
                mode2.showNext();
                break;
            case R.id.buttonNormal:
                LogUtils.i(TAG,"点击了normal事件");
                mode2.showNext();
                break;
        }

    }
}
