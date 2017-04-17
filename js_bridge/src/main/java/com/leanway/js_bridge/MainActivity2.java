package com.leanway.js_bridge;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.widget.Button;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.DefaultHandler;
import com.google.gson.Gson;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

/**
 * @author Xibo_Yue
 * @time 2017/4/17 12:21
 */
public class MainActivity2 extends Activity implements View.OnClickListener {
    private final String TAG = "MainActivity2";

    BridgeWebView webView;

    Button button;

    int RESULT_CODE = 0;

    ValueCallback<Uri> mUploadMessage;

    static class Location {
        String address;
    }

    static class User {
        String name;
        Location location;
        String testStr;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        webView = (BridgeWebView) findViewById(R.id.webView);

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(this);

        webView.setDefaultHandler(new DefaultHandler());

        webView.setWebChromeClient(new WebChromeClient() {

            @SuppressWarnings("unused")
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType, String capture) {
                this.openFileChooser(uploadMsg);
            }

            @SuppressWarnings("unused")
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType) {
                this.openFileChooser(uploadMsg);
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                mUploadMessage = uploadMsg;
                pickFile();
            }
        });
        //加载本地网页

        webView.loadUrl("file:///android_asset/demo.html");
        //加载服务器网页
       // webView.loadUrl("https://www.baidu.com");

        //必须和js同名函数，注册具体执行函数，类似java实现类。
        webView.registerHandler("submitFromWeb", new BridgeHandler() {

            @Override
            public void handler(String data, CallBackFunction function) {

                String str ="这是html返回给java的数据:" + data;
                // 例如你可以对原始数据进行处理
                makeText(MainActivity2.this, str, LENGTH_SHORT).show();

                Log.i(TAG, "handler = submitFromWeb, data from web = " + data);
                function.onCallBack( str + ",Java经过处理后截取了一部分："+ str.substring(0,5));
            }

        });
        //模拟用户获取本地位置
        User user = new User();
        Location location = new Location();
        location.address = "上海";
        user.location = location;
        user.name = "Bruce";

        webView.callHandler("functionInJs", new Gson().toJson(user), new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                makeText(MainActivity2.this, "网页在获取你的位置", LENGTH_SHORT).show();

            }
        });

        webView.send("hello");

    }

    public void pickFile() {
        Intent chooserIntent = new Intent(Intent.ACTION_GET_CONTENT);
        chooserIntent.setType("image/*");
        startActivityForResult(chooserIntent, RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == RESULT_CODE) {
            if (null == mUploadMessage){
                return;
            }
            Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        }
    }

    @Override
    public void onClick(View v) {
        if (button.equals(v)) {
            webView.callHandler("functionInJs", "data from Java", new CallBackFunction() {

                @Override
                public void onCallBack(String data) {
                    // TODO Auto-generated method stub
                    Log.i(TAG, "reponse data from js " + data);
                }

            });
        }

    }

}
