package com.yuexibo.camera_demo.application;

import android.app.Application;

import com.leanway.okhttp.OkHttpManage;

/**
 * @author Xibo_Yue
 * @time 2017/5/4 11:31
 */

public class CameraApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpManage.init(this);
    }
}
