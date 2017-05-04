package com.leanway.okhttp.okhttp;


import com.leanway.okhttp.okhttp.listener.DisposeDataHandle;
import com.leanway.okhttp.okhttp.listener.DisposeDataListener;
import com.leanway.okhttp.okhttp.listener.DisposeDownloadListener;
import com.leanway.okhttp.okhttp.request.CommonRequest;
import com.leanway.okhttp.okhttp.request.RequestParams;

import okhttp3.Request;

import static com.leanway.okhttp.okhttp.request.CommonRequest.createGetRequest;

/**
 * Created by 岳希波 on 16/10/27.
 *
 * @function sdk请求发送中心
 */
public class RequestCenter {
    /**
     * 下载apk文件
     * @param apkUrl
     * @param disposeDownloadListener
     * @param filePath mContext.getExternalCacheDir().getAbsolutePath()+"/"+appName   加上文件名称
     */
    public static void downApk(String apkUrl, DisposeDownloadListener disposeDownloadListener, String filePath){
        Request request = createGetRequest(apkUrl,null);
        CommonOkHttpClient.downloadFile(request,new DisposeDataHandle(disposeDownloadListener,filePath));
    }


    /**
     * 获取网络最新版本号
     * @param url 网络版本号的地址    {"code":12,"apkurl":"http://www.leanway.wang/lnfiles/download/app/tracking/tracking.apk","des":"3.3Bug修复"}
     * @param listener
     */
    public static void checkVersion(String url,DisposeDataListener listener) {
        CommonOkHttpClient.qget(CommonRequest.
                createGetRequest(url, null), new DisposeDataHandle(listener));
    }


    /**
     * 请求结果仍在在子线程，需要转发
     * @param url
     * @param params
     * @param listener
     * @param clazz
     */
    public static void syncPost(String url, RequestParams params,DisposeDataListener listener,Class<?> clazz) {
        CommonOkHttpClient.syncpost(CommonRequest.
                createGetRequest(url, params), new DisposeDataHandle(listener,clazz));
    }

}
