package com.example.tonydemo.http;

/**
 * Created by tony on 16-6-1.
 */
public class AirBean {
    private int errNum;
    private String retMsg;
    private AirDataBean retData;

    public AirBean() {
    }

    public AirBean(int errNum, String retMsg, AirDataBean retData) {
        this.errNum = errNum;
        this.retMsg = retMsg;
        this.retData = retData;
    }

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public AirDataBean getRetData() {
        return retData;
    }

    public void setRetData(AirDataBean retData) {
        this.retData = retData;
    }

    @Override
    public String toString() {
        return "AirBean{" +
                "errNum=" + errNum +
                ", retMsg='" + retMsg + '\'' +
                ", retData=" + retData +
                '}';
    }
}
