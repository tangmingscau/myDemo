package com.example.tonydemo.face;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.view.*;
import android.widget.Button;
import android.widget.ImageView;
import com.example.tonydemo.Config;
import com.example.tonydemo.R;
import com.example.tonydemo.util.BitmapDealUtil;
import com.example.tonydemo.util.LogUtil;
import com.example.tonydemo.util.SendBroadCastUtil;

/**
 * Created by tony on 16-9-5.
 */
public class FaceWindow implements View.OnClickListener {
    private static final String TAG = FaceWindow.class.getSimpleName();
    private static final int WINDOW_WIDTH = 640, WINDOW_HEIGHT = 1000, WINDOW_X = 0;

    private static Context mContext = null;
    private static FaceWindow faceWindow = null;
    Button takePictureBt = null;
    ImageView previewImg = null;

    WindowManager windowManager = null;
    Window window = null;
    View view = null;
    Camera mCamera = null;
    SurfaceView mPreview = null;
    SurfaceHolder mHolder = null;

    XPCameraManager cameraManager = null;
    XPBaseCameraHandler baseCameraHandler = null;

    public static FaceWindow getInstance(Context context) {
        if (faceWindow == null) {
            faceWindow = new FaceWindow();
            mContext = context;

        }
        return faceWindow;
    }

    private void removeCameraView() {
        if (view != null) {
            windowManager.removeView(view);
            view = null;
        }
    }

    private void addCameraView() {
        windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
        wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_SPLIT_TOUCH | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        wmParams.width = WINDOW_WIDTH;
        wmParams.height = WINDOW_HEIGHT;
        wmParams.gravity = Gravity.TOP;
        wmParams.format = PixelFormat.RGBA_8888;
        view = View.inflate(mContext, R.layout.camera_fr, null);
        windowManager.addView(view, wmParams);
        init();
    }

    private void init() {
        cameraManager = new XPCameraManager(mContext, view);
        takePictureBt = (Button) view.findViewById(R.id.takePictureBt);
        takePictureBt.setOnClickListener(this);
        previewImg = (ImageView) view.findViewById(R.id.previewImg);
        mPreview = (SurfaceView) view.findViewById(R.id.previewSf);
        mHolder = mPreview.getHolder();
        mHolder.addCallback(callback);
        cameraManager.openDrive(mHolder);
        if (baseCameraHandler == null) {
            baseCameraHandler = new XPBaseCameraHandler(cameraManager, cameraResultCallback);
        }
    }


    SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            cameraManager.setStartPreview();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            cameraManager.releaseCamera();
        }
    };

    /**
     * 开始人脸识别
     */
    public void startFace() {
        addCameraView();
    }

    /**
     * 停止人脸识别
     */
    public void stopFace() {
        removeCameraView();
        cameraManager.releaseCamera();
    }

    CameraResultCallback cameraResultCallback = new CameraResultCallback() {
        @Override
        public void doBitmap(byte[] bytes) {
            LogUtil.i(TAG, "doBitmap" + bytes);
            Bitmap bitmap = BitmapDealUtil.getInstance().decodeByteBitmap(bytes);
            if (bitmap != null) {
                previewImg.setImageBitmap(bitmap);
            }
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.takePictureBt:
                SendBroadCastUtil.getInstance(mContext).sendBroadCast(Config.ACTION_BROADCAST_STOP_FACE, null, null);
                break;
        }
    }
}
