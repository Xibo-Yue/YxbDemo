package com.leanway.video_player;

import android.app.Application;

import com.leanway.video_player.core.AdSDKManager;

/**
 * @author Xibo_Yue
 * @time 2017/3/14 11:45
 */
public class VideoApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AdSDKManager.init(this);
    }
}
