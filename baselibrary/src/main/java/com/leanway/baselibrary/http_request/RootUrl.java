package com.leanway.baselibrary.http_request;

/**
 * @author Xibo_Yue
 * @time 2017/2/13 17:03
 */
public class RootUrl {
    public static String ROOT_PATH;
    public static String setURL(String url, String port) {
        String seturl = null;
        if (port.length() == 0 || port.equals("null") || null == port) {
            seturl = "http://" + url + "/lnmes/";
        } else {
            seturl = "http://" + url + ":" + port + "/lnmes/";
        }
        return seturl;
    }
}
