package com.example.tonydemo.face;

import android.content.Context;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.*;
import android.widget.Button;
import android.widget.ImageView;
import com.example.tonydemo.Config;
import com.example.tonydemo.R;
import com.example.tonydemo.util.LogUtil;
import com.example.tonydemo.util.SendBroadCastUtil;

import java.io.IOException;


/**
 * Created by tony on 16-8-9.
 */
public class CameraActivity extends FragmentActivity implements View.OnClickListener {
    private static final String TAG = CameraActivity.class.getSimpleName();
    private Button openCameraBt = null;
    WindowManager windowManager = null;
    Window window = null;
    View view = null;
    static Context context = null;
    Camera mCamera = null;
    SurfaceView mPreview = null;
    SurfaceHolder mHolder = null;

    Button takePictureBt = null;
    ImageView previewImg = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.camera_ac);
        openCameraBt = (Button) findViewById(R.id.openCameraBt);
        openCameraBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.openCameraBt:
//                addCameraView();
                SendBroadCastUtil.getInstance(getApplicationContext()).sendBroadCast(Config.ACTION_BROADCAST_START_FACE, null, null);
//                Intent intent=new Intent(this,FaceService.class);
//                startService(intent);
                break;
        }
    }

    private void addCameraView() {
        windowManager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
        wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            | WindowManager.LayoutParams.FLAG_SPLIT_TOUCH | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        wmParams.width = Config.DIMVIEW_WIDTH;
        wmParams.height = Config.DIMVIEW_HEIGHT;
        wmParams.gravity = Gravity.TOP;
        wmParams.format = PixelFormat.RGBA_8888;
        wmParams.y = Config.DIMVIEW_Y;
        view = View.inflate(this, R.layout.camera_fr, null);
        init();
        windowManager.addView(view, wmParams);
    }

    private void init() {
        previewImg = (ImageView) view.findViewById(R.id.previewImg);
        takePictureBt = (Button) view.findViewById(R.id.takePictureBt);
        takePictureBt.setOnClickListener(this);
        mPreview = (SurfaceView) view.findViewById(R.id.previewSf);
        mHolder = mPreview.getHolder();
        mHolder.addCallback(callback);
        if (mCamera == null) {
            mCamera = getCamera();
            if (mHolder != null) {
                setStartPreview();
            }
        }
    }

    public Camera getCamera() {
        try {
            if (mCamera == null) {
                mCamera = mCamera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
            }
        } catch (Exception e) {
            LogUtil.e(TAG, e.getMessage());
        }
        return mCamera;
    }

    /**
     * 开启预览
     */
    public void setStartPreview() {
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.setDisplayOrientation(90);
            mCamera.startPreview();
        } catch (IOException e) {
            LogUtil.e(TAG, e.getMessage());
        }
    }

    /**
     * 释放摄像头资源
     */
    public void releaseCamera() {
        try {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        } catch (Exception e) {
            LogUtil.e(TAG, e.getMessage());
        }
    }

    SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            setStartPreview();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            mCamera.stopPreview();
            setStartPreview();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            releaseCamera();
        }
    };
}
