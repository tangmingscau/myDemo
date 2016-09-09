package com.example.tonydemo.face;

import android.content.Context;
import android.hardware.Camera;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import com.example.tonydemo.util.LogUtil;

import java.io.IOException;
import java.util.List;

/**
 * Created by tony on 16-9-6.
 */
public class XPCameraManager {
    private static String TAG = XPCameraManager.class.getSimpleName();
    private Context mContext = null;
    private View mView = null;
    private XPPreviewCallback previewCallback = null;
    private Camera mCamera = null;
    private SurfaceView mPreview = null;
    private SurfaceHolder mHolder = null;

    public XPCameraManager(Context context, View view) {
        mContext = context;
        mView = view;
        previewCallback = new XPPreviewCallback();
    }

    public synchronized void openDrive(SurfaceHolder holder) {
        mHolder = holder;
        try {
            if (mCamera == null) {
                mCamera = getCamera();
                if (mHolder != null) {
                    mCamera.setPreviewDisplay(mHolder);
                    mCamera.setDisplayOrientation(CameraConfig.ROTATE);
                    mCamera.startPreview();
                }
                //设置参数
                Camera.Parameters parameters = mCamera.getParameters();
                List<Camera.Size> list = parameters.getSupportedPreviewSizes();
                for (Camera.Size cur : list) {
                    LogUtil.i(TAG, "cur.size(%d,%d)", cur.width, cur.height);
                }
                parameters.setPreviewSize(CameraConfig.PREVIEW_WIDTH, CameraConfig.PREVIEW_HEIGHT);
                parameters.setPictureSize(CameraConfig.PICTURE_WIDTH, CameraConfig.PICTURE_HEIGHT);
                mCamera.setParameters(parameters);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Camera getCamera() {
        try {
            if (mCamera == null) {
                mCamera = mCamera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mCamera;
    }

    /**
     * 开启预览
     */
    public void setStartPreview() {
        try {
            mCamera.setPreviewDisplay(mHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCamera.setDisplayOrientation(CameraConfig.ROTATE);
        mCamera.startPreview();
    }

    public synchronized void requestPreviewFrame(Handler handler, int message) {
        previewCallback.setHandler(handler, message);
        mCamera.setOneShotPreviewCallback(previewCallback);
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
}
