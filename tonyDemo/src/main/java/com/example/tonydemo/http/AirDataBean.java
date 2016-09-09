package com.example.tonydemo.http;

/**
 * Created by tony on 16-6-1.
 */
public class AirDataBean {
    private String city;
    private String time;
    private int aqi;
    private String level;
    private String core;

    public AirDataBean(String city, String time, int aqi, String level, String core) {
        this.city = city;
        this.time = time;
        this.aqi = aqi;
        this.level = level;
        this.core = core;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getAqi() {
        return aqi;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    @Override
    public String toString() {
        return "AirDataBean{" +
                "city='" + city + '\'' +
                ", time='" + time + '\'' +
                ", aqi=" + aqi +
                ", level='" + level + '\'' +
                ", core='" + core + '\'' +
                '}';
    }
}
