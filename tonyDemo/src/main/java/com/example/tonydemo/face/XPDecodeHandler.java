package com.example.tonydemo.face;

import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.os.Handler;
import android.os.Message;
import com.example.tonydemo.util.LogUtil;

import java.io.ByteArrayOutputStream;

/**
 * Created by tony on 16-9-6.
 */
public class XPDecodeHandler extends Handler {
    private static final String TAG = XPDecodeHandler.class.getSimpleName();
    XPCameraManager mCameraManager = null;
    CameraResultCallback cameraResultCallback = null;

    public enum State {
        decode
    }

    public XPDecodeHandler(XPCameraManager cameraManager, CameraResultCallback mCameraResultCallback) {
        mCameraManager = cameraManager;
        cameraResultCallback = mCameraResultCallback;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case CameraConfig.DECODE:
                decodeArray((byte[]) msg.obj, msg.arg1, msg.arg2);
                break;
        }
    }

    /**
     * 处理摄像头预览得到的信息，并且提取人脸特征值,根据人脸提取结果做出不同的处理
     *
     * @param data   摄像头预览图片信息
     * @param width
     * @param height
     */
    private void decodeArray(byte[] data, int width, int height) {
        LogUtil.i(TAG, "data--》" + data);
        int[] hWdHi = new int[2];
        hWdHi[0] = width;
        hWdHi[1] = height;

        byte[] faceRgb24 = null;
        try {
            if (data != null) {
//                faceRgb24 = new byte[width * height * 3];
//                //将格式为yuv的data的数据转换成RGB格式
//                faceRecognize.TESO_ImgDataYuvToRgb(MyApplication.mApplication, data, hWdHi, faceRgb24);
//                faceRecognize.TESO_ImgDataDoRotate(MyApplication.mApplication, faceRgb24, hWdHi, -1000);
//                //将RGB格式转化成jpg格式
//                final byte[] faceJpg = new byte[hWdHi[0] * hWdHi[1] * 3 + 1024];
//                faceRecognize.TESO_ImgDataRgbToJpg(MyApplication.mApplication, faceRgb24, hWdHi, faceJpg);
                YuvImage yuvImage = new YuvImage(data, ImageFormat.NV21, width, height, null);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                yuvImage.compressToJpeg(new Rect(0, 0, width, height), 100, byteArrayOutputStream);

                cameraResultCallback.doBitmap(byteArrayOutputStream.toByteArray());
            }
        } catch (Exception e) {

        }

        mCameraManager.requestPreviewFrame(this, CameraConfig.DECODE);
//        mCameraManager.releaseCamera();
    }
}
