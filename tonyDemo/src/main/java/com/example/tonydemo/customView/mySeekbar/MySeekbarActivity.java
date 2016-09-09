package com.example.tonydemo.customView.mySeekbar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.tonydemo.util.LogUtils;
import com.example.tonydemo.R;

/**
 * Created by tony on 16-4-8.
 */
public class MySeekbarActivity extends Activity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {
    private static String TAG = MySeekbarActivity.class.getSimpleName();
    SeekBar chargeSeekbar = null;
    Button chargeAdd, startCharge, endCharge;
    boolean isChargeing = false;
    int batteryActual = 9;
    int batterySet = 80;
    boolean startTouch = false;
    ImageView dash = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seekbar);
        chargeSeekbar = (SeekBar) findViewById(R.id.verifySeekbar);
        chargeAdd = (Button) findViewById(R.id.chargeAdd);
        startCharge = (Button) findViewById(R.id.startCharge);
        endCharge = (Button) findViewById(R.id.endCharge);
        dash = (ImageView) findViewById(R.id.dash);
        startCharge.setOnClickListener(this);
        endCharge.setOnClickListener(this);
        chargeAdd.setOnClickListener(this);
        chargeSeekbar.setOnSeekBarChangeListener(this);
        if (batteryActual <= 10) {
            chargeSeekbar.setProgressDrawable(getResources().getDrawable(R.drawable.charge_seekbar_track_normal_low));
        } else {
            chargeSeekbar.setProgressDrawable(getResources().getDrawable(R.drawable.charge_seekbar_track_normal));
        }
        chargeSeekbar.setProgress(batterySet);
        chargeSeekbar.setSecondaryProgress(batteryActual);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        LogUtils.i(TAG, "onProgressChanged progress=%d", progress);
        if (isChargeing) {
            chargeSeekbar.setSecondaryProgress(progress);
            chargeSeekbar.setProgress(progress);
        } else {
            chargeSeekbar.setSecondaryProgress(batteryActual);
        }
        if (startTouch) {
            chargeSeekbar.setProgress(progress);
            batterySet = progress;
            dash.setVisibility(View.VISIBLE);
            ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(dash.getLayoutParams());
            marginLayoutParams.setMargins(chargeSeekbar.getSecondaryProgress() * 7, 0, 0, 0);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(marginLayoutParams);
            params.width = (progress - chargeSeekbar.getSecondaryProgress()) * 7;
            params.height = 90;
            dash.setLayoutParams(params);
            dash.setImageResource(R.drawable.dash);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        startTouch = true;
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        startTouch = false;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (isChargeing) {
                        chargeSeekbar.setSecondaryProgress(msg.arg1);
                        chargeSeekbar.setProgress(msg.arg1);
                        int progressValue = msg.arg1;
                        Message message = Message.obtain();
                        message.what = 0;
                        if (progressValue < batterySet) {
                            progressValue++;
                        } else {
                            progressValue = batteryActual;
                        }
                        message.arg1 = progressValue;
                        handler.sendMessageDelayed(message, 200);
                    }
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startCharge:
                isChargeing = true;
                chargeSeekbar.setProgressDrawable(getResources().getDrawable(R.drawable.charge_seekbar_track_charging));
                int progressValue = batteryActual;
                Message message = Message.obtain();
                message.what = 0;
                if (progressValue < batterySet) {
                    progressValue++;
                } else {
                    progressValue = batteryActual;
                }
                message.arg1 = progressValue;
                handler.sendMessageDelayed(message, 200);
                break;
            case R.id.endCharge:
                isChargeing = false;
                if (batteryActual <= 10) {
                    chargeSeekbar.setProgressDrawable(getResources().getDrawable(R.drawable.charge_seekbar_track_normal_low));
                } else {
                    chargeSeekbar.setProgressDrawable(getResources().getDrawable(R.drawable.charge_seekbar_track_normal));
                }
                chargeSeekbar.setSecondaryProgress(batteryActual);
                chargeSeekbar.setProgress(batterySet);
                break;
            case R.id.chargeAdd:
                batteryActual++;
                if (isChargeing) {
                    chargeSeekbar.setProgressDrawable(getResources().getDrawable(R.drawable.charge_seekbar_track_charging));
                } else {
                    if (batteryActual <= 10) {
                        chargeSeekbar.setProgressDrawable(getResources().getDrawable(R.drawable.charge_seekbar_track_normal_low));
                    } else {
                        chargeSeekbar.setProgressDrawable(getResources().getDrawable(R.drawable.charge_seekbar_track_normal));
                    }
                }
                chargeSeekbar.setSecondaryProgress(batteryActual);
                break;
        }
    }
}
