package com.example.tonydemo.customView.mySeekbar;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
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
public class ChargeSeekbar extends FrameLayout {
    private static String TAG = ChargeSeekbar.class.getSimpleName();
    private Context mContext = null;
    FrameLayout view = null;
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

    public ChargeSeekbar(Context context) {
        super(context);
        init(context);
    }

    public ChargeSeekbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ChargeSeekbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
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
    }

    public void initData(int current, int max, int currentDistance) {
        this.current = current;
        this.max = max;
        this.currentDistance = currentDistance;
        reDrawCurrentView();
        reDrawSetMaxView();
        reDrawCursorView();
    }

    /**
     * 开始充电
     */
    private void startCharge() {

    }

    /**
     * 重画设置最大充电view
     */
    private void reDrawSetMaxView() {
        if (isPressed) {
            ViewGroup.LayoutParams params = dashImg.getLayoutParams();
            params.width = computePosition(max - current);
            dashImg.setImageResource(R.drawable.dash_pressed);
            dashImg.setLayoutParams(params);
            dashImg.requestLayout();
        } else {
            ViewGroup.LayoutParams params = dashImg.getLayoutParams();
            params.width = computePosition(max - current);
            dashImg.setImageResource(R.drawable.dash_normal);
            dashImg.setLayoutParams(params);
            dashImg.requestLayout();
        }
        invalidate();
    }

    /**
     * 重画当前view
     */
    private void reDrawCurrentView() {
        if (current < 10) {
            ViewGroup.LayoutParams params = currentChargeImg.getLayoutParams();
            params.width = computePosition(current);
            currentChargeImg.setImageResource(R.color.red);
            currentChargeImg.setLayoutParams(params);
            currentChargeImg.requestLayout();
        } else if (current < 20) {
            ViewGroup.LayoutParams params = currentChargeImg.getLayoutParams();
            params.width = computePosition(current);
            currentChargeImg.setImageResource(R.color.yellow);
            currentChargeImg.setLayoutParams(params);
            currentChargeImg.requestLayout();
        } else {
            ViewGroup.LayoutParams params = currentChargeImg.getLayoutParams();
            params.width = computePosition(current);
            currentChargeImg.setImageResource(R.color.yellow);
            currentChargeImg.setLayoutParams(params);
            currentChargeImg.requestLayout();
        }
        invalidate();
    }

    /**
     * 重画当前游标
     */
    private void reDrawCursorView() {

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
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        paddingLeft = bigllayout.getPaddingLeft();
        paddingRight = bigllayout.getPaddingRight();
        paddingBottom = bigllayout.getPaddingBottom();
        paddingTop = bigllayout.getPaddingTop();
        width = getMeasuredWidth() - paddingLeft - paddingRight;
        height = getMeasuredHeight() - paddingTop - paddingBottom;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
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

    @Override
    public boolean isPressed() {
        return isPressed;
    }

    @Override
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
}
