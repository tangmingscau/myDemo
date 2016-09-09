package com.example.tonydemo.face.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.example.tonydemo.Config;
import com.example.tonydemo.MyApplication;
import com.example.tonydemo.face.FaceService;
import com.example.tonydemo.util.LogUtil;
import com.example.tonydemo.util.SericeUtil;


/**
 * Created by tony on 16-9-5.
 */
public class FaceBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        LogUtil.i("FaceBroadcast", "receive --》" + intent.getAction());
        if (action != null) {
            if (action.equals(Config.ACTION_BROADCAST_START_FACE)) {
                //开始人脸识别
//                SericeUtil.getInstance(MyApplication.myApplication).startService(Config.ACTION_SERVICE_START_FACE);
                Intent intent1=new Intent(context, FaceService.class);
                intent1.setAction(Config.ACTION_SERVICE_START_FACE);
                context.startService(intent1);
            } else if (action.equals(Config.ACTION_BROADCAST_STOP_FACE)) {
                //停止人脸识别
                SericeUtil.getInstance(MyApplication.myApplication).stopService(Config.ACTION_SERVICE_START_FACE);
            }
        }
    }
}
