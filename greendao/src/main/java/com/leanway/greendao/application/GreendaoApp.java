package com.leanway.greendao.application;

import android.app.Application;

/**
 * @author Xibo_Yue
 * @time 2017/5/24 11:30
 */

public class GreendaoApp extends Application {

    private static GreendaoApp mApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }


    /**
     * 获得Application
     * @return application
     */
    public static GreendaoApp getInstance() {
        return mApplication;
    }
}
