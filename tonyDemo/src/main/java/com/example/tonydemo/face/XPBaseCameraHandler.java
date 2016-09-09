package com.example.tonydemo.face;

import android.os.Handler;
import android.os.Message;

/**
 * Created by tony on 16-9-6.
 */
public class XPBaseCameraHandler extends Handler {
    XPCameraManager mCameraManager = null;
    XPDecodeHandler decodeHandler = null;
    private CameraResultCallback cameraResultCallback = null;

    public XPBaseCameraHandler(XPCameraManager cameraManager , CameraResultCallback mCameraResultCallback) {
        mCameraManager = cameraManager;
        cameraResultCallback = mCameraResultCallback;
        decodeHandler = new XPDecodeHandler(cameraManager, cameraResultCallback);
        mCameraManager.setStartPreview();
        mCameraManager.requestPreviewFrame(decodeHandler, CameraConfig.DECODE);
    }

    @Override
    public void handleMessage(Message msg) {

    }
}
