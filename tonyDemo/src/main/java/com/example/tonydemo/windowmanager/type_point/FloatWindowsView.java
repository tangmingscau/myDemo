package com.example.tonydemo.windowmanager.type_point;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.tonydemo.R;

/**
 * Created by xinlyun on 16-3-8.
 */
public class FloatWindowsView {
    private Context context;
    private WindowManager mWindowManager;
    private int width, height;
    private LayoutParams wmParams;
    private View mMainLayout;
    private View mView;
    private boolean addIs = false;
    private LinearLayout mWindowLayout;

    public FloatWindowsView(Context context) {
        this.context = context;
//        mWindowManager  = ((Activity)context).getWindowManager();
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        width = mWindowManager.getDefaultDisplay().getWidth();
        height = mWindowManager.getDefaultDisplay().getHeight();

    }

    public void createParames() {
        wmParams = new LayoutParams();
//        wmParams.type   = 2018;
        wmParams.type = 2018;
        wmParams.memoryType = LayoutParams.MEMORY_TYPE_HARDWARE;
        wmParams.format = PixelFormat.RGBA_8888;
        wmParams.flags = LayoutParams.FLAG_NOT_FOCUSABLE | LayoutParams.FLAG_SPLIT_TOUCH | LayoutParams.FLAG_LAYOUT_NO_LIMITS | LayoutParams.FLAG_NOT_TOUCH_MODAL;
        wmParams.gravity = Gravity.TOP;
        wmParams.width = LayoutParams.FILL_PARENT;
        wmParams.height = height;
        wmParams.y = -168;

    }

    ImageView mImageView;

    public View createFloatView(final int mFloatWindowId) {
        LayoutInflater inflater = LayoutInflater.from(context);
        mMainLayout = inflater.inflate(R.layout.float_main, null);
        mView = inflater.inflate(mFloatWindowId, null);
        mWindowLayout = (LinearLayout) mMainLayout.findViewById(R.id.mWindowLayout);
        mWindowLayout.addView(mView);

//        mImageView      = (ImageView) mMainLayout.findViewById(R.id.imgtouch);
//        mImageView.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        mMainLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("MImageView", "OnToucheListener");
                FloatWindowsView.this.remove();
                return true;
            }
        });

//        mWindowManager.addView(mMainLayout, wmParams);
        mMainLayout.measure(View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
                .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        return mView;
    }


    public void createFloatView(final View view) {
        LayoutInflater inflater = LayoutInflater.from(context);
        mMainLayout = (LinearLayout) inflater.inflate(R.layout.float_main, null);
        mView = view;
        mMainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatWindowsView.this.remove();
            }
        });
        mWindowLayout = (LinearLayout) mMainLayout.findViewById(R.id.mWindowLayout);
        mWindowLayout.addView(mView);

        mMainLayout.measure(View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
                .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//        mWindowManager.addView(mMainLayout, wmParams);
    }

    public void show() {
        if (!addIs) {
            mWindowManager.addView(mMainLayout, wmParams);
            addIs = true;
        }
    }

    public void remove() {
        if (addIs) {
            mWindowManager.removeView(mMainLayout);
//            mMainLayout.removeAllViews();
            mView = null;
            addIs = false;
        }
    }
}
