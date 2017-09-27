package com.techno.matrimonial.Model.common;

/**
 * Created by arbaz on 19/7/16.
 */
public class DeviceInfo {
    public String device_model = "LenovoK3";
    public String device_platform = "Android";
    public String device_token = "New Token";
    public String device_unique_id = "No Id";
    public String os = "Android Marshmellow";

    public DeviceInfo(String device_model, String device_platform, String device_token, String device_unique_id, String os) {
        this.device_model = device_model;
        this.device_platform = device_platform;
        this.device_token = device_token;
        this.device_unique_id = device_unique_id;
        this.os = os;
    }
    public DeviceInfo()
    {
    }
    public String getDevice_model() {
        return device_model;
    }

    public void setDevice_model(String device_model) {
        this.device_model = device_model;
    }

    public String getDevice_platform() {
        return device_platform;
    }

    public void setDevice_platform(String device_platform) {
        this.device_platform = device_platform;
    }

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }

    public String getDevice_unique_id() {
        return device_unique_id;
    }

    public void setDevice_unique_id(String device_unique_id) {
        this.device_unique_id = device_unique_id;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }
}
