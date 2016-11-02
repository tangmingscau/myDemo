package com.example.tonydemo.customView.circleWave;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import com.example.tonydemo.R;
import com.example.tonydemo.util.BessleUtil;
import com.example.tonydemo.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hackooo on 2015/11/7.
 * Email:hackooo@sina.cn
 */
public class WaveView extends View {
    private static final String TAG=WaveView.class.getSimpleName();
    enum STATE {
        IDLE, RESTART, RUNNING
    }

    private STATE state = STATE.IDLE;

    private float startProgress = 0.5f;         //Y轴起始偏移
    private float progress = 0.0f;              //Y轴当前加载的进度,范围从0~1f;

    private float lastFraction = 0f;            //animator动画周期的上一次的fraction
    private float currentFraction = 0f;         //此次重绘时的fraction

    private PorterDuffXfermode atTopMode = new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP);


    private int patternResId, waveResId;

    //图案
    private Bitmap patternBitmap;
    private int patternSrcWidth, patternSrcHeight;
    private int patternDstWidth, patternDstHeight;
    private float patternScaleFactor;           //中间图标的缩放系数

    //缩放后的中间图案
    private Bitmap scaledPatternBitmap;

    //波浪条
    private Bitmap waveBitmap;
    private int waveWidth, waveHeight;

    private BitmapShader shader;
    private Paint mPaint;

    private Paint mBesselPaint = null;
    private Path mBesselPath = null;

    //视图的宽高
    private int mLayoutWidth, mLayoutHeight;

    /**
     * 即将要穿越的点集合
     */
    private List<Point> mPoints = new ArrayList<Point>();
    /**
     * 中点集合
     */
    private List<Point> mMidPoints = new ArrayList<Point>();
    /**
     * 中点的中点集合
     */
    private List<Point> mMidMidPoints = new ArrayList<Point>();
    /**
     * 移动后的点集合(控制点)
     */
    private List<Point> mControlPoints = new ArrayList<Point>();

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WaveView);
        patternScaleFactor = a.getFloat(R.styleable.WaveView_patternScaleFactor, 0.618f);
        patternResId = a.getResourceId(R.styleable.WaveView_patternRes, 0);
        waveResId = a.getResourceId(R.styleable.WaveView_waveRes, 0);
        a.recycle();

        if (0 == patternResId) {
            throw new RuntimeException("you should set the patternResId!");
        }
        if (0 == waveResId) {
            throw new RuntimeException("you should set the waveResId!");
        }

        loadRes();
        mPaint = new Paint();
        initBessel();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        LogUtil.i(TAG,"onMeasure");
        mLayoutWidth = MeasureSpec.getSize(widthMeasureSpec);
        mLayoutHeight = MeasureSpec.getSize(heightMeasureSpec);

        float scale = patternSrcWidth / (float) patternSrcHeight;
        if (mLayoutWidth < mLayoutHeight) {
            patternDstWidth = (int) (mLayoutWidth * patternScaleFactor);
            patternDstHeight = (int) (patternDstWidth / scale);
        } else {
            patternDstHeight = (int) (mLayoutHeight * patternScaleFactor);
            patternDstWidth = (int) (patternDstHeight * scale);
        }

        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
            getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
        initBesselPoint();

    }

    protected void onPreDrawWave(Canvas canvas) {
    }

    protected void onPostDrawWave(Canvas canvas) {
    }

    @Override
    protected void onDraw(Canvas canvas) {
        LogUtil.i(TAG,"onDraw");

//        loadRes();
//        if (null == scaledPatternBitmap) {
//            scaledPatternBitmap = Bitmap.createScaledBitmap(patternBitmap, patternDstWidth, patternDstHeight, true);
//        }
//        if (null == shader) {
//            shader = new BitmapShader(waveBitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
//        }

//        onPreDrawWave(canvas);
        BessleUtil.getInstance().initPoint(mPoints, mMidPoints, mMidMidPoints, mControlPoints);
        BessleUtil.getInstance().drawBesselLine(canvas, mBesselPaint, mBesselPath);
//        //画中间层的颜色
//        int sc = canvas.saveLayer(0, 0, mLayoutWidth, mLayoutHeight, null, Canvas.ALL_SAVE_FLAG);
//        canvas.drawBitmap(scaledPatternBitmap, getStartX(patternDstWidth), getStartY(patternDstHeight), mPaint);
//        mPaint.setXfermode(atTopMode);
//        mPaint.setShader(shader);
//
//        float offsetX = currentFraction * patternDstWidth;
//        float translationX = getStartX(patternDstWidth) - offsetX;
//        canvas.translate(translationX, getStartY(patternDstHeight) + patternDstHeight - waveHeight - patternDstHeight * startProgress - patternDstHeight * (1 - startProgress) * progress);
//        canvas.drawRect(0, 0, patternDstWidth + offsetX, patternDstHeight + waveHeight, mPaint);
//        mPaint.setXfermode(null);
//        canvas.restoreToCount(sc);
//
//        onPostDrawWave(canvas);
    }

    public void initBessel() {
        mBesselPaint = new Paint();
        mBesselPaint.setStrokeWidth(3);
        mBesselPaint.setAntiAlias(true);
        mBesselPaint.setStyle(Paint.Style.FILL);
        mBesselPaint.setColor(Color.BLACK);
        mBesselPath = new Path();
    }

    private void initBesselPoint() {
        mPoints.clear();
        int pointWidthSpace = mLayoutWidth / 7;
        for (int i = 0; i < 7; i++) {
            Point point;
            // 一高一低7个点
            if (i % 2 != 0) {
                point = new Point((int) (pointWidthSpace * (i + 0.5)), mLayoutHeight/5 - 100);
            } else {
                point = new Point((int) (pointWidthSpace * (i + 0.5)), mLayoutHeight/5);
            }
            mPoints.add(point);
        }
        LogUtil.i(TAG,"initBesselPoint:mPoints=%s",mPoints.toString());
    }

    private void loadRes() {
        if (null == waveBitmap || waveBitmap.isRecycled()) {
            waveBitmap = BitmapFactory.decodeResource(getResources(), waveResId);
            waveWidth = waveBitmap.getWidth();
            waveHeight = waveBitmap.getHeight();
        }
        if (null == patternBitmap || patternBitmap.isRecycled()) {
            patternBitmap = BitmapFactory.decodeResource(getResources(), patternResId);
            patternSrcWidth = patternBitmap.getWidth();
            patternSrcHeight = patternBitmap.getHeight();
        }
    }

    //使目标图画出来处于中心
    private float getStartX(int w) {
        return (int) (mLayoutWidth / 2f - w / 2f);
    }

    //使目标图画出来处于中心
    private float getStartY(int h) {
        return (int) (mLayoutHeight / 2f - h / 2f);
    }

    @Override
    protected void onDetachedFromWindow() {
        setVisibility(GONE);
        super.onDetachedFromWindow();
    }

    private void recycleImg() {
        if (null != waveBitmap) {
            waveBitmap.recycle();
            waveBitmap = null;
        }
        if (null != patternBitmap) {
            patternBitmap.recycle();
            patternBitmap = null;
        }
    }

    public void setStartProgress(float startProgress) {
        this.startProgress = startProgress;
    }

    public void setPatternScaleFactor(float patternScaleFactor) {
        this.patternScaleFactor = patternScaleFactor;
    }

    public int getLayoutWidth() {
        return mLayoutWidth;
    }

    public int getLayoutHeight() {
        return mLayoutHeight;
    }

    public boolean isRunning() {
        return state == STATE.RUNNING;
    }

    public void start() {
        state = STATE.RESTART;
        setVisibility(VISIBLE);
    }

    public void pause() {
        state = STATE.IDLE;
    }

    public void stop() {
        state = STATE.IDLE;
        setVisibility(GONE);
    }

    public void stopAndRecycle() {
        stop();
        recycleImg();
    }

    public void update(float fraction) {
        update(fraction, -1f);
    }

    /**
     * @param progress Y轴偏移
     * @param fraction X轴偏移
     */
    public void update(float fraction, float progress) {
        if (getVisibility() == VISIBLE) {
            if (state == STATE.IDLE) {
                return;
            }
            if (progress > 0f) {
                this.progress = progress;
            }
            if (state == STATE.RESTART) {
                lastFraction = fraction;
                state = STATE.RUNNING;
            }
            currentFraction += (fraction + 1 - lastFraction) % 1;
            lastFraction = fraction;
            invalidate();
        } else {
            recycleImg();
        }
    }
}