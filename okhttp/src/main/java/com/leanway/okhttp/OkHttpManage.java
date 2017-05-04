package com.leanway.okhttp;

import android.app.Application;
import android.content.Context;

import com.leanway.okhttp.okhttp.cookie.CookieJarImpl;
import com.leanway.okhttp.okhttp.cookie.PersistentCookieStore;

/**
 * @author Xibo_Yue
 * @time 2017/3/30 15:20
 */
public class OkHttpManage {
    public static Context mContext;
    public static CookieJarImpl cookieJar;
    public static void init(Application context){
        mContext = context;
        cookieJar = new CookieJarImpl(new PersistentCookieStore(context));
    }
}
