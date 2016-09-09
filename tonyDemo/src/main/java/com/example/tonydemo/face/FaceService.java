package com.example.tonydemo.face;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.example.tonydemo.Config;
import com.example.tonydemo.util.LogUtil;


/**
 * Created by tony on 16-9-7.
 */
public class FaceService extends Service {
    private static final String TAG = FaceService.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String action = intent.getAction();
            LogUtil.i(TAG, "onStartCommand,receiver---》" + action);
            if (action != null && action.equals(Config.ACTION_SERVICE_START_FACE)) {
                FaceWindow.getInstance(getApplicationContext()).startFace();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        FaceWindow.getInstance(getApplicationContext()).stopFace();
        super.onDestroy();
    }
}
