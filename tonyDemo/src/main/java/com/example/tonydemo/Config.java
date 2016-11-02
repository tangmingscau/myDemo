package com.example.tonydemo;

/**
 * Created by tony on 16-7-19.
 */
public class Config {
    public final static int SUCCESS = 1; //成功
    public final static int FAIL = -1;    //失败
    public final static int SURPASS = -2; //超出限制
    public final static int FINISH=-3;
    /**
     * 进入密码框的name
     */
    public static final String Behavior = "behavior";
    /**
     * 退出临客行为
     */
    public static final int EXIT_TEMP = 1;
    /**
     * 进入车主账号行为
     */
    public static final int ENTER_OWNER = 2;

    public static final String ActivationCode = "ActivationCode";

    public static final String CODE = "Code";
    public final static String ActiveCodeDefault = "06C0A60DF256451A";
    public static final int OWER_NOUSER_FRAGMENT = 0, OWNER_FRAGMENT = 1, USER_FRAGMENT = 2, TEMPUSER_FRAGMENT = 3;

    public static final float DIM_AMOUNT = 0.6f;
    public static final int DIMVIEW_TYPE = 2016, DIMVIEW_WIDTH = 400, DIMVIEW_HEIGHT = 400, DIMVIEW_Y = 200;
    public static final int CAMERA_MASK_TYPE=2016;

    public static final String ACTION_BROADCAST_START_FACE = "com.example.tonydemo.START_FACE"; //开启人脸识别
    public static final String ACTION_BROADCAST_STOP_FACE = "com.example.tonydemo.STOP_FACE";
    public static final String ACTION_SERVICE_START_FACE="com.example.tonydemo.service.START_FACE";

    public static final String ACTION_BROADCAST_INSERT_CUSTOMER1="com.example.tonydemo.insert_customer1";
    public static final String ACTION_BROADCAST_DELETE_CUSTOMER1="com.example.tonydemo.delete_customer1";
    public static final String ACTION_BROADCAST_INSERT_ORDER1="com.example.tonydemo.insert_order1";
    public static final String ACTION_BROADCAST_DELETE_ORDER1="com.example.tonydemo.delete_order1";
    public static final String ACTION_BROADCAST_INSERT_ORDER2="com.example.tonydemo.insert_order2";
    public static final String ACTION_BROADCAST_UPDATE_ORDER2="com.example.tonydemo.update_order2";
}
