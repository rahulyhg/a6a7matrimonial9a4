package com.techno.matrimonial.Model.common;

/**
 * Created by arbaz on 19/7/16.
 */
public class Login {
    public String email;
    public String password;
    public DeviceInfo deviceInfo;

    public Login(String email, String password, DeviceInfo deviceInfo) {
        this.email = email;
        this.password = password;
        this.deviceInfo = deviceInfo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
}
