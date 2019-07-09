package com.healthmall.sail.cat_doctor.bean;

/**
 * Created by maijuntian on 2018/7/14.
 */
public class Message {
    String type;
    String mallId;
    String deviceId;
    String ssid;
    String pwd;

    public Message(String type) {
        this.type = type;
    }

    public Message(String type, String deviceId, String mallId) {
        this.type = type;
        this.mallId = mallId;
        this.deviceId = deviceId;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMallId() {
        return mallId;
    }

    public void setMallId(String mallId) {
        this.mallId = mallId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
