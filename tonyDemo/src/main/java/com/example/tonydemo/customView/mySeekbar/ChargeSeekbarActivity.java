package com.example.tonydemo.customView.mySeekbar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tonydemo.R;
import com.example.tonydemo.util.LogUtils;

/**
 * Created by tony on 16-6-21.
 */
public class ChargeSeekbarActivity extends Activity implements View.OnTouchListener {
    private static String TAG = ChargeSeekbar.class.getSimpleName();
    private Context mContext = null;
    LinearLayout bigllayout = null, currentChargeLlayout;
    FrameLayout currentChargeFlayout = null, setChargeflayout;
    ImageView currentChargeImg = null, dashImg, setChargeImg, cursorImg;
    TextView currentChargeTxt = null;
    private int current = 10;        //当前点亮
    private int max = 85;         //最大充电亮
    private int currentDistance;  //当前可行驶距离
    private int width = 0;  //整体bigllayoutr宽度
    private int height = 0;    //整体bigllayout高度
    private int paddingLeft = 0, paddingRight, paddingTop, paddingBottom;  //整体bigllayout
    private int currentWidth = 0;   //当前点亮宽度
    private int setMaxWidth = 0;  //最大充电宽度
    private boolean isPressed = false;//是否按下
    private boolean isCharge = false; //是否在充电
    private int chargeSpeed = 1;   //这个值决定了充电动画的速度
    private int chargeValue = 0;      //充电动画所变化的值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.charge_main);
        init(getApplicationContext());
    }

    private void init(Context context) {
        mContext = context;
        initView();
    }

    private void initView() {
        bigllayout = (LinearLayout) findViewById(R.id.bigllayout);
        currentChargeLlayout = (LinearLayout) findViewById(R.id.currentChargeLlayout);
        currentChargeFlayout = (FrameLayout) findViewById(R.id.currentChargeFlayout);
        setChargeflayout = (FrameLayout) findViewById(R.id.setChargeflayout);
        currentChargeImg = (ImageView) findViewById(R.id.currentChargeImg);
        dashImg = (ImageView) findViewById(R.id.dashImg);
        setChargeImg = (ImageView) findViewById(R.id.setChargeImg);
        cursorImg = (ImageView) findViewById(R.id.cursorImg);
        currentChargeTxt = (TextView) findViewById(R.id.currentChargeTxt);
        bigllayout.setOnTouchListener(this);
    }

    public void initData(int current, int max, int currentDistance) {

        this.current = current;
        this.max = max;
        this.currentDistance = currentDistance;
        paddingLeft = bigllayout.getPaddingLeft();
        paddingRight = bigllayout.getPaddingRight();
        paddingBottom = bigllayout.getPaddingBottom();
        paddingTop = bigllayout.getPaddingTop();
        width = bigllayout.getMeasuredWidth() - paddingLeft - paddingRight;
        height = bigllayout.getMeasuredHeight() - paddingTop - paddingBottom;
        reDrawCurrentView();
        reDrawSetMaxView();
        reDrawCursorView();
    }

    /**
     * 开始充电
     */
    private void startCharge() {
        handler.postDelayed(runnable, 100);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //在这里处理充电ui逻辑
            if (isCharge) {
                if (chargeValue % (max - current) != 0) {
                    chargeValue += chargeSpeed;
                } else {
                    chargeValue = 1;
                }
                reDrawCurrentView();
                reDrawSetMaxView();
                handler.postDelayed(runnable, 100);
            } else {
                chargeValue = 0;
            }
        }
    };

    /**
     * 停止充电
     */
    private void stopCharge() {
        handler.removeCallbacks(runnable);
    }

    /**
     * 重画设置最大充电view
     */
    private void reDrawSetMaxView() {
        if (isPressed) {
            ViewGroup.LayoutParams params = dashImg.getLayoutParams();
            params.width = computePosition(max - current - chargeValue);
            dashImg.setImageResource(R.drawable.dash_pressed);
            dashImg.setLayoutParams(params);
            dashImg.requestLayout();
            setChargeImg.setVisibility(View.VISIBLE);
        } else {
            ViewGroup.LayoutParams params = dashImg.getLayoutParams();
            params.width = computePosition(max - current - chargeValue);
            dashImg.setImageResource(R.drawable.dash_normal);
            dashImg.setLayoutParams(params);
            dashImg.requestLayout();
            setChargeImg.setVisibility(View.GONE);
        }

    }

    /**
     * 重画当前电量view
     */
    private void reDrawCurrentView() {

        if (isCharge) {
            currentChargeLlayout.setVisibility(View.GONE);
        } else {
            currentChargeLlayout.setVisibility(View.VISIBLE);
            if (current < 10) {
                ViewGroup.LayoutParams params = currentChargeImg.getLayoutParams();
                params.width = computePosition(current + chargeValue);
                currentChargeImg.setImageResource(R.color.red);
                currentChargeImg.setLayoutParams(params);
                currentChargeImg.requestLayout();
                currentChargeTxt.setGravity(Gravity.LEFT);
                currentChargeTxt.setText(current + "");
            } else if (current < 20) {
                ViewGroup.LayoutParams params = currentChargeImg.getLayoutParams();
                params.width = computePosition(current + chargeValue);
                currentChargeImg.setImageResource(R.color.yellow);
                currentChargeImg.setLayoutParams(params);
                currentChargeImg.requestLayout();
                currentChargeTxt.setGravity(Gravity.CENTER);
                currentChargeTxt.setText(current + "");
            } else {
                ViewGroup.LayoutParams params = currentChargeImg.getLayoutParams();
                params.width = computePosition(current + chargeValue);
                currentChargeImg.setImageResource(R.color.green);
                currentChargeImg.setLayoutParams(params);
                currentChargeImg.requestLayout();
                currentChargeTxt.setGravity(Gravity.CENTER);
                currentChargeTxt.setText(current + "");
            }
        }
    }

    /**
     * 重画当前游标
     */
    private void reDrawCursorView() {
        if (isPressed) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) cursorImg.getLayoutParams();
            params.setMarginStart(computePosition(max) - cursorImg.getWidth() / 2);
            cursorImg.setImageResource(R.drawable.cursor_pressed);
            cursorImg.setLayoutParams(params);
            cursorImg.requestLayout();
        } else {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) cursorImg.getLayoutParams();
            params.setMarginStart(computePosition(max) - cursorImg.getWidth() / 2);
            cursorImg.setImageResource(R.drawable.cursor_pressed);
            cursorImg.setLayoutParams(params);
            cursorImg.requestLayout();
        }
    }

    /**
     * 根据当前点击的x坐标值计算max
     *
     * @param event
     */
    private void computeMax(MotionEvent event) {
        int x = (int) (event.getX() * 100);
        if (x % width == 0) {
            max = x / width;
        } else {
            max = x / width + 1;
        }
        if (max >= 100) {
            max = 100;
        } else if (max <= 60) {
            max = 60;
        }
    }

    /**
     * 根据所传入的值计算坐标
     *
     * @param value
     */
    private int computePosition(int value) {
        int x = 0;
        x = (int) (width * 1.0 / 100 * value);
        return x;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

        handler.sendEmptyMessageDelayed(1, 300);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            initData(current, max, currentDistance);
        }
    };

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getCurrentDistance() {
        return currentDistance;
    }

    public void setCurrentDistance(int currentDistance) {
        this.currentDistance = currentDistance;
    }

    public boolean isCharge() {
        return isCharge;
    }

    public void setCharge(boolean charge) {
        isCharge = charge;
    }

    @Override
    public String toString() {
        return ", current=" + current +
                ", max=" + max +
                ", currentDistance=" + currentDistance +
                ", width=" + width +
                ", height=" + height +
                ", paddingLeft=" + paddingLeft +
                ", paddingRight=" + paddingRight +
                ", paddingTop=" + paddingTop +
                ", paddingBottom=" + paddingBottom +
                ", currentWidth=" + currentWidth +
                ", setMaxWidth=" + setMaxWidth +
                ", isPressed=" + isPressed +
                ", isCharge=" + isCharge +
                '}';
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        computeMax(event);
        LogUtils.i(TAG, toString());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setPressed(true);
                reDrawSetMaxView();
                reDrawCursorView();
                break;
            case MotionEvent.ACTION_MOVE:
                reDrawSetMaxView();
                reDrawCursorView();
                break;
            case MotionEvent.ACTION_UP:
                setPressed(false);
                reDrawSetMaxView();
                reDrawCursorView();
                break;
        }
        return true;
    }
}
