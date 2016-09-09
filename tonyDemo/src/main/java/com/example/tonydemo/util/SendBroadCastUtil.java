package com.example.tonydemo.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @desc 广播发送通用类
 */
public class SendBroadCastUtil {

    private Context context;
    private static SendBroadCastUtil mUtil;
    private Intent mIntent;
    private static final String TAG = "SendBroadCastUtil";

    private SendBroadCastUtil(Context mContext) {
        this.mIntent = new Intent();
        context = mContext;
    }

    /**
     * @return SendBroadCastUtil实例
     */
    public static synchronized SendBroadCastUtil getInstance(Context mContext) {

        if (null == mUtil) {
            mUtil = new SendBroadCastUtil(mContext);

        }
        return mUtil;
    }

    /**
     * 广播发送方法
     *
     * @param action 广播action
     * @param name   数据 key
     * @param data   携带 String 数据
     */
    public void sendBroadCast(String action, String name, String data) {
        LogUtil.i(TAG, "action:" + action + ",data[key:" + name + ",value:" + data + "]");
        mIntent = new Intent();
        mIntent.setAction(action);
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(data)) {//如果携带有数据

            mIntent.putExtra(name, data);
        }
        context.sendBroadcast(mIntent);
    }


    public void sendBroadCast(String action, Bundle bundle) {
        LogUtil.i(TAG, "action:" + action + ",bundle[" + bundle.toString() + "]");
        mIntent = new Intent();
        mIntent.setAction(action);
        mIntent.putExtras(bundle);
        context.sendBroadcast(mIntent);
    }

    /**
     * 延迟发送广播
     *
     * @param action 广播action
     * @param name   数据 key
     * @param data   携带 String 数据
     * @param delay  携带 String 数据
     */
    public void sendBroadCast(final String action, final String name, final String data, long delay) {
        LogUtil.i(TAG, "延迟" + delay + "毫秒广播");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                sendBroadCast(action, name, data);
            }
        }, delay);
    }

}
