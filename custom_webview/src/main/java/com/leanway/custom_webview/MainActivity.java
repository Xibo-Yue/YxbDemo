package com.leanway.custom_webview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ProgressBar bar = (ProgressBar)findViewById(R.id.myProgressBar);

        final WebView webView = (WebView)findViewById(R.id.myWebView);
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    bar.setVisibility(View.INVISIBLE);
                } else {
                    if (View.INVISIBLE == bar.getVisibility()) {
                        bar.setVisibility(View.VISIBLE);
                    }
                    bar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

        });

        findViewById(R.id.myButton).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                webView.reload();
            }

        });
        final String url = "http://www.baidu.com/";
        webView.loadUrl(url);
    }

}
