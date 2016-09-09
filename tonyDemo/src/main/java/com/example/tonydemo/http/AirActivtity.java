package com.example.tonydemo.http;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tonydemo.util.LogUtils;
import com.example.tonydemo.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by tony on 16-6-1.
 */
public class AirActivtity extends Activity implements View.OnClickListener {
    private static String TAG = AirActivtity.class.getSimpleName();
    private TextView airResult = null;
    private Button airStart = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.air_main);
        airResult = (TextView) findViewById(R.id.airResult);
        airStart = (Button) findViewById(R.id.airButton);
        airStart.setOnClickListener(this);

    }

    /**
     * @param httpUrl :请求接口
     * @param httpArg :参数
     * @return 返回结果
     */
    public static String requestAir(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");

            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey", "45e4d2bf4912b6052287ea1eded1e0f4");
//            connection.setRequestProperty("Accept", "application/json,text/javascript");
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset-GBK");
//            connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
//            connection.setRequestProperty("Accept-Charset", "GBK");
            connection.setConnectTimeout(5000);
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            LogUtils.i(TAG, e.getMessage());
        }
        return result;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String result = (String) msg.obj;
            String newResult = null;
            try {
                newResult = new String(result.getBytes(), "GBK");
            } catch (UnsupportedEncodingException e) {
                LogUtils.e(TAG, e.getMessage());
            }
//            try {
//                result = new String(result.getBytes(), "GBK");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
            switch (msg.what) {
                case 0:
                    airResult.setText(newResult);
                    LogUtils.i(TAG, "result=%s", result);
                    Gson gson = new Gson();
                    AirBean airBean1 = new AirBean();
                    airBean1.setErrNum(1);
                    airBean1.setRetMsg("ss");
                    LogUtils.i(TAG, "自定义=%s", gson.toJson(airBean1, AirBean.class));
//                    AirBean airBean = gson.fromJson(result, AirBean.class);
//                    AirDataBean airDataBean = airBean.getRetData();
//                    LogUtils.i(TAG, "airBean=%s", airBean.toString());
//                    try {
////                        String snew = new String(airDataBean.getLevel().getBytes(), "UTF-8");
////                        airResult.setText(snew);
//                    } catch (UnsupportedEncodingException e) {
//                        LogUtils.i(TAG, "snew");
//                    }

                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        String s = "\u826f";
        try {
            String snew = new String(s.getBytes(), "UTF-8");
            LogUtils.i(TAG, "snew=%s", snew);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        switch (v.getId()) {
            case R.id.airButton:
                new Thread() {
                    @Override
                    public void run() {
                        String httpUrl = "http://apis.baidu.com/apistore/aqiservice/aqi";
                        String httpArg = "";
                        try {
                            httpArg = "city=" + URLEncoder.encode("北京", "utf-8");
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }
                        Log.i(TAG, "空气质量开始查询");
                        String jsonResult = "";
                        jsonResult = requestAir(httpUrl, httpArg);
                        Message msg = Message.obtain();
                        msg.obj = jsonResult.toString();
                        msg.what = 0;
                        handler.sendMessage(msg);
                        LogUtils.i(TAG, "空气质量=%s", jsonResult.toString());
                    }
                }.start();
                break;
        }
    }
}
