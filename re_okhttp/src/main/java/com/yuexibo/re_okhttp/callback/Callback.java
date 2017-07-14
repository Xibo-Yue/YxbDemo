package com.yuexibo.re_okhttp.callback;

import android.util.Log;

public abstract class Callback<T> {

    public abstract void onSuccess(T t);

    public void onError(Throwable e) {
        Log.d("net_error---->", e.toString());
    }

}
