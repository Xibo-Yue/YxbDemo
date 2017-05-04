package com.leanway.okhttp.okhttp;


import com.leanway.okhttp.OkHttpManage;
import com.leanway.okhttp.okhttp.https.HttpsUtils;
import com.leanway.okhttp.okhttp.listener.DisposeDataHandle;
import com.leanway.okhttp.okhttp.response.CommonFileCallback;
import com.leanway.okhttp.okhttp.response.CommonJsonCallback;
import com.leanway.okhttp.okhttp.response.SyncJsonCallback;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author 岳希波
 * @function 用来发送get, post请求的工具类，包括设置一些请求的共用参数
 */
public class CommonOkHttpClient {
    private static final int TIME_OUT = 20;  //普通请求超时时间


    private static final int QTIME_OUT = 2;  //检查版本号的超时时间


    private static OkHttpClient mOkHttpClient;  //普通okhttpclient

    private static OkHttpClient qOkHttpClient;  //检查版本号的okhttpclient



    static {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        /**
         * 添加对所有https网站的信任
         *
         */
        okHttpClientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        okHttpClientBuilder.sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager());

        /**
         *  为所有请求添加请求头，看个人需求
         */
        okHttpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("User-Agent", "yxb-Mobile") // 标明发送本次请求的客户端
                        .build();
                return chain.proceed(request);
            }
        });

        //缓存文件夹
//        File cacheFile = new File(OkHttpManage.mContext.getExternalCacheDir().toString(),"cache");
//        //缓存大小为10M
//        int cacheSize = 10 * 1024 * 1024;
//        //创建缓存对象
//        Cache cache = new Cache(cacheFile,cacheSize);

//        okHttpClientBuilder.cache(cache);
        okHttpClientBuilder.cookieJar(OkHttpManage.cookieJar);
        okHttpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.followRedirects(true); //是否自动重定向
        mOkHttpClient = okHttpClientBuilder.build();
    }

    /**
     * 检查版本号的qOkHttpClient，超时时间为5s
     */
    public static void getQokHttpClient(){
        OkHttpClient.Builder qokHttpClientBuilder = new OkHttpClient.Builder();
        qokHttpClientBuilder.connectTimeout(QTIME_OUT, TimeUnit.SECONDS);
        qokHttpClientBuilder.readTimeout(QTIME_OUT, TimeUnit.SECONDS);
        qokHttpClientBuilder.writeTimeout(QTIME_OUT, TimeUnit.SECONDS);
        qOkHttpClient = qokHttpClientBuilder.build();
    }

    public static OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    /**
     * 指定cilent信任指定证书
     *
     * @param certificates
     */
    public static void setCertificates(InputStream... certificates) {
        mOkHttpClient.newBuilder().sslSocketFactory(HttpsUtils.getSslSocketFactory(certificates, null, null)).build();
    }


    /**
     * 检查版本号的get请求，超时时间为5s
     * @param request
     * @param handle
     * @return
     */
    public static Call qget(Request request, DisposeDataHandle handle) {
        getQokHttpClient();
        Call call = qOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }



    /**
     * 普通get请求
     * @param request
     * @param handle
     * @return
     */
    public static Call get(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    /**
     * 普通post请求
     * @param request
     * @param handle
     * @return
     */
    public static Call post(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    /**
     * 请求结果仍在子线程，需要转发
     * @param request
     * @param handle
     * @return
     */
    public static Call syncpost(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new SyncJsonCallback(handle));
        return call;
    }

    /**
     * 文件下载
     * @param request
     * @param handle
     * @return
     */
    public static Call downloadFile(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonFileCallback(handle));
        return call;
    }

    /**
     * 文件上传
     * @param request
     * @param handle
     * @return
     */
    public static Call uploadFile(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }


}