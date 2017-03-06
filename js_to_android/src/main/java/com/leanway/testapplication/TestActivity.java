package com.leanway.testapplication;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity {

    private WebView webView;
    private Handler handler = new Handler();
    private Button button;
    private String mUsername;
    private String mPassword;
    private TextView login_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Bundle bundle = getIntent().getExtras();
        mUsername = bundle.getString("username");
        mPassword = bundle.getString("password");
        login_info = (TextView) findViewById(R.id.login_info);
        login_info.setText("用户名:"+mUsername+"   密码:"+mPassword);
        webView = (WebView) findViewById(R.id.webView);
        //自定义webView设置
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new MyJavaScriptInterface(TestActivity.this),
                "javaInterface");
        //如果注释了，javaScript中的alert弹窗等就会失效，不显示
        webView.setWebChromeClient(new MyChromeWebViewClient(TestActivity.this));
        //webView.setWebChromeClient(new MyWebChromeClient());
        //测试webView加载是否正常
        //webView.loadUrl("http://www.baidu.com/");
        webView.setWebViewClient(new HelloWebView());
        webView.loadUrl("file:///android_asset/test.html");
        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String param = "bb";
                webView.loadUrl("javascript:showTitle('" + param + "')");
            }
        });
    }

    private class HelloWebView extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    /**
     * 在主线程中定义JavaScript可以调用的安卓接口
     *
     * @author CHQ
     *         API 以后，每个被调用java函数都要叫声明 @JavascriptInterface
     */
    public class MyJavaScriptInterface {
        private Context context;

        public MyJavaScriptInterface(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public String toString() {
            return "this is interface";
        }

        @JavascriptInterface
        public void clickOnAndroid() {
            Toast.makeText(context, "js调用安卓：....", Toast.LENGTH_SHORT).show();
        }

        /**
         * 安卓调用JS接口，要开启子线程调用
         */
        @JavascriptInterface
        public void call() {
            Toast.makeText(context, "安卓客户端再调用JavaScript接口", Toast.LENGTH_SHORT).show();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    String param = "bb";
                    webView.loadUrl("javascript:showTitle('" + param + "')");
                }
            });
        }
    }
}


