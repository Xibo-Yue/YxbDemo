package com.leanway.baselibrary.http_request;

/**
 * @author Xibo_Yue
 * @time 2017/2/13 13:31
 */

public interface RequestListener {
    public void requestSuccess(String reponse);
    public void requestFail();
    public void loginTimeOut();
}
