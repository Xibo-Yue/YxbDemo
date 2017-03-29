package com.leanway.baselibrary.okhttp;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @author Xibo_Yue
 * @time 2017/2/13 12:14
 */
public class OKHttpManager {
    public static void initOkhttp(Application application){
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(application));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .cookieJar(cookieJar)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }
}
