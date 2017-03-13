package com.leanway.testapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author Xibo_Yue
 * @time 2017/3/13 10:43
 */
public class VideoMonitorActivity extends AppCompatActivity{

    private WebView mWebView;
    private String url = "file:///android_asset/video_test.html";
    private boolean isOnPause = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videomonitor);
        mWebView = (WebView) findViewById(R.id.web_view);
        MyChromeWebViewClient chromeWebViewClient = new MyChromeWebViewClient(VideoMonitorActivity.this);
        mWebView.setWebChromeClient(chromeWebViewClient);
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setDefaultTextEncodingName("GB2312");
        webSettings.setUseWideViewPort(true);//设定支持viewport
        webSettings.setSupportZoom(false);//支持缩放
        webSettings.setBuiltInZoomControls(false);// 设置出现缩放工具
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);//自适应屏幕
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);        //支持JS
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);  //优先使用缓存：WebSettings
        // .LOAD_CACHE_ELSE_NETWORK
        //mWebView.addJavascriptInterface(new MainActivity.JsInterface(), "control");
        mWebView.loadUrl(url);
    }


    @Override
    //覆盖系统的回退键
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    /**
     * 当Activity执行onPause()时让WebView执行pause
     */
    @Override
    protected void onPause() {
        super.onPause();
        try {
            if (mWebView != null) {
                mWebView.getClass().getMethod("onPause").invoke(mWebView, (Object[]) null);
                isOnPause = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 当Activity执行onResume()时让WebView执行resume
     */
    @Override
    protected void onResume() {
        super.onResume();
        try {
            if (isOnPause) {
                if (mWebView != null) {
                    mWebView.getClass().getMethod("onResume").invoke(mWebView, (Object[]) null);
                }
                isOnPause = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 该处的处理尤为重要:
     * 应该在内置缩放控件消失以后,再执行mWebView.destroy()
     * 否则报错WindowLeaked
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.getSettings().setBuiltInZoomControls(true);
            mWebView.setVisibility(View.GONE);

            mWebView.destroy();
            mWebView = null;
//            long delayTime = ViewConfiguration.getZoomControlsTimeout();
//            new Timer().schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    mWebView.destroy();
//                    mWebView = null;
//                }
//            }, delayTime);
        }
        isOnPause = false;
    }
}
