package com.leanway.okhttp;

/**
 * @author Xibo_Yue
 * @time 2017/2/13 17:03
 */
public class RootUrl {
    public static String ROOT_PATH;

    public static String ROOT_WEB = null;

    public static String setURL(String url, String port) {
        String seturl = null;
        if (port.length() == 0 || port.equals("null") || null == port) {
            seturl = "http://" + url + "/lnmes/";
        } else {
            seturl = "http://" + url + ":" + port + "/lnmes/";
        }
        ROOT_PATH = seturl;
        return seturl;
    }

    public static String setWebUrl(String url, String port) {
        String seturl = null;
        if (port.length() == 0 || port.equals("null") || null == port) {
            seturl = "http://" + url + "/";
        } else {
            seturl = "http://" + url + ":" + port + "/";
        }
        ROOT_WEB = seturl;
        return seturl;
    }

}
