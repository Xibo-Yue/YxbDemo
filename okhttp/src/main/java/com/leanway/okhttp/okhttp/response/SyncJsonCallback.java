package com.leanway.okhttp.okhttp.response;

import android.os.Handler;
import android.os.Looper;

import com.leanway.okhttp.base.JSONUtils;
import com.leanway.okhttp.base.OkHttpException;
import com.leanway.okhttp.okhttp.exception.RequestError;
import com.leanway.okhttp.okhttp.listener.DisposeDataHandle;
import com.leanway.okhttp.okhttp.listener.DisposeDataListener;
import com.leanway.okhttp.okhttp.listener.DisposeHandleCookieListener;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;

/**
 * @author Xibo_Yue
 * @time 2017/4/25 9:38
 */

public class SyncJsonCallback implements Callback {

    protected final String RESULT_CODE = "ecode"; // 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";
    protected final String COOKIE_STORE = "Set-Cookie"; // decide the server it
    // can has the value of
    // set-cookie2

    /**
     * the java layer exception, do not same to the logic error
     */
    protected final int NETWORK_ERROR = -1; // 网络错误
    protected final int JSON_ERROR = -2; // JSON解析错误
    protected final int OTHER_ERROR = -3; // 其它未知错误
    protected final int LOGINOUT_ERROR = -4; // 登录超时
    /**
     * 将其它线程的数据转发到UI线程
     */
    private Handler mDeliveryHandler;
    private DisposeDataListener mListener;
    private Class<?> mClass;

    public SyncJsonCallback(DisposeDataHandle handle) {
        this.mListener = handle.mListener;
        this.mClass = handle.mClass;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(final Call call, final IOException ioexception) {
        mListener.onFailure(new OkHttpException(NETWORK_ERROR, ioexception));

    }

    @Override
    public void onResponse(final Call call, final Response response) throws IOException {
        final String result = response.body().string();
        final ArrayList<String> cookieLists = handleCookie(response.headers());

        handleResponse(result);
        /**
         * handle the cookie
         */
        if (mListener instanceof DisposeHandleCookieListener) {
            ((DisposeHandleCookieListener) mListener).onCookie(cookieLists);
        }

    }

    private ArrayList<String> handleCookie(Headers headers) {
        ArrayList<String> tempList = new ArrayList<String>();
        for (int i = 0; i < headers.size(); i++) {
            if (headers.name(i).equalsIgnoreCase(COOKIE_STORE)) {
                tempList.add(headers.value(i));
            }
        }
        return tempList;
    }

    private void handleResponse(Object responseObj) {
        if (responseObj == null || responseObj.toString().trim().equals("")) {
            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }

        if (responseObj.toString().trim().contains(RequestError.LOGIN_TIMEOUT)) {
            mListener.onFailure(new OkHttpException(LOGINOUT_ERROR, EMPTY_MSG));
            return;
        }


        try {
            if (responseObj.toString().startsWith("[")) {
                mListener.onSuccess(responseObj.toString());
                return;
            }
            JSONObject result = new JSONObject(responseObj.toString());
            if (mClass == null) {
                mListener.onSuccess(result);
            } else {
                Object obj = JSONUtils.readJson2Object(result.toString(), mClass);
                if (obj != null) {
                    mListener.onSuccess(obj);
                } else {
                    mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                }
            }
        } catch (Exception e) {
            mListener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
            e.printStackTrace();
        }
    }
}
