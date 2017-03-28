package com.leanway.video_player;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVideoView = (VideoView) findViewById(R.id.video_view);
        Uri uri = Uri.parse("http://hlscdn.lechange.cn/LCO/2D02BD7PAK00825/0/0/20170311105414/dev_20170311105414_0ckh6s9kp1ch8tg3.m3u8");
        //mVideoView.setMediaController(new MediaController(this));
        mVideoView.setVideoURI(uri);
        mVideoView.start();
    }
}
