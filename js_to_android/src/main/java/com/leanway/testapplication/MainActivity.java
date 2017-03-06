package com.leanway.testapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    private AlertDialog mAlertDialog;

    private String url = "file:///android_asset/index.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (WebView) findViewById(R.id.web_view);
        MyChromeWebViewClient chromeWebViewClient = new MyChromeWebViewClient(MainActivity.this);
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
        webSettings.setSupportZoom(true);//支持缩放
        webSettings.setBuiltInZoomControls(true);// 设置出现缩放工具
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);//自适应屏幕
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);        //支持JS
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);  //优先使用缓存：WebSettings
        // .LOAD_CACHE_ELSE_NETWORK
        mWebView.addJavascriptInterface(new JsInterface(), "control");
        mWebView.loadUrl(url);

    }


    public class JsInterface {
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(MainActivity.this, toast, Toast.LENGTH_SHORT).show();
            Log.d("html", "show toast success");
        }

        public void log(final String msg) {
            mWebView.post(new Runnable() {
                @Override
                public void run() {
                    mWebView.loadUrl("javascript log(" + "'" + msg + "'" + ")");
                }
            });
        }
        @JavascriptInterface
        public void login(String username,String password){
            Toast.makeText(MainActivity.this,username,Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,TestActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("username",username);
            bundle.putString("password",password);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }


    @Override
    //覆盖系统的回退键
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
