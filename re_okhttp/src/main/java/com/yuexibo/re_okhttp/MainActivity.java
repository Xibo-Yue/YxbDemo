package com.yuexibo.re_okhttp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.yuexibo.re_okhttp.bean.OneBean;
import com.yuexibo.re_okhttp.callback.Callback;
import com.yuexibo.re_okhttp.request.RequestManager;
import com.yuexibo.re_okhttp.request.RxRequest;
import com.yuexibo.re_okhttp.request.RxSubscriber;

import java.util.HashMap;

import rx.Observable;

public class MainActivity extends RxAppCompatActivity {
    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniViews();
    }

    private void iniViews() {
        tvData = (TextView) findViewById(R.id.tv_data);
    }

    @SuppressWarnings("unchecked")
    public void getData(View view) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("strDate", "2017-03-25");
        Observable<OneBean> weather = RxRequest.getInstance().getProxy(false).postData(hashMap);
        RxSubscriber subscriber = new RxSubscriber(this, new Callback<OneBean>() {
            @Override
            public void onSuccess(OneBean oneBean) {
                tvData.setText(oneBean.getHpEntity().getStrContent());
            }
        });
        RequestManager.getInstance().sendRequest(weather, subscriber);
    }


}
