package com.mhraju.deviceinfo.Model;

/**
 * Created by Maksudul Hasan Raju on 11/5/2016.
 */
public class DeviceInfo {

    private String mTitle, mInfo;

    public DeviceInfo(){

    }

    public DeviceInfo(String mTitle, String mInfo) {
        this.mTitle = mTitle;
        this.mInfo = mInfo;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmInfo() {
        return mInfo;
    }

    public void setmInfo(String mInfo) {
        this.mInfo = mInfo;
    }
}
