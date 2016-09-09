package com.example.tonydemo.face;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.tonydemo.R;
import com.example.tonydemo.util.LogUtil;

import java.io.IOException;

/**
 * Created by tony on 16-8-8.
 */
public class SelfCameraFragment extends Fragment implements SurfaceHolder.Callback, View.OnClickListener {
    private static String TAG = SelfCameraFragment.class.getSimpleName();
    View mView = null;
    Camera mCamera = null;
    SurfaceView mPreview = null;
    SurfaceHolder mHolder = null;

    Button takePictureBt = null;
    ImageView previewImg = null;
    Bitmap bitmap = null;
    private final static int FaceScore = 60;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.camera_fr, container, false);
        init();
        return mView;
    }

    private void init() {
        previewImg = (ImageView) mView.findViewById(R.id.previewImg);
        takePictureBt = (Button) mView.findViewById(R.id.takePictureBt);
        takePictureBt.setOnClickListener(this);
        mPreview = (SurfaceView) mView.findViewById(R.id.previewSf);
        mHolder = mPreview.getHolder();
        mHolder.addCallback(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mCamera == null) {
            mCamera = getCamera();
            if (mHolder != null) {
                setStartPreview();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        releaseCamera();
    }

    /**
     * 获取camera对象
     *
     * @return
     */
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

    public void onClick(View view) {
        final Camera.Parameters parameters = mCamera.getParameters();
        parameters.setPictureFormat(ImageFormat.JPEG);
        parameters.setPictureSize(800,400);
        parameters.setPreviewSize(800, 400);
        mCamera.setFaceDetectionListener(new Camera.FaceDetectionListener() {
            @Override
            public void onFaceDetection(Camera.Face[] faces, Camera camera) {
                LogUtil.i(TAG, "faces={%s},MaxNumDetectedFaces=%d", toStringFaces(faces), camera.getParameters().getMaxNumDetectedFaces());
                for (Camera.Face face : faces) {
                    if (face != null) {
                        if (face.score > FaceScore) {
                            mCamera.takePicture(null, null, pictureCallback);
//                            mCamera.stopFaceDetection();
//                            mCamera.stopPreview();
                        }
                    }
                }
            }
        });
        mCamera.startFaceDetection();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            previewImg.setImageBitmap(bitmap);
            LogUtil.i(TAG, "handleMessage");
        }
    };
    Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            LogUtil.i(TAG, "onPictureTaken");
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            handler.sendEmptyMessage(1);
        }
    };

    public String toStringFaces(Camera.Face[] faces) {
        String str = "";
        for (Camera.Face face : faces) {
            str += " Rect= " + face.rect.toString() +
                    " score= " + face.score +
                    " id= " + face.id +
                    " leftEye= " + face.leftEye.toString() +
                    " rightEye= " + face.rightEye.toString() +
                    " mouth= " + face.mouth.toString();
        }
        return str;
    }
}
