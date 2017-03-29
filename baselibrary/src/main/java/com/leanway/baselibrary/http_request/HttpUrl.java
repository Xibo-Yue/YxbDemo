package com.leanway.baselibrary.http_request;

/**
 * @author Xibo_Yue
 * @time 2017/2/13 13:56
 */
public class HttpUrl {
    /**
     * 应用更新地址
     */
    public static final String updateURL = "http://www.leanway.wang/lnfiles/download/app/tracking/updateInfo.json";

    /**
     * 获取公司列表
     */
    public static String getComList = "company?method=getCompanyByUserWsInterface";

    /**
     * 用户登录
     */
    public static String login = "user?method=login";

    /**
     * 获取派工单列表
     */
    public static String getPachOrderList = "dispatchingOrder?method=queryDispatchingOrderWsInterface";



    /**
     * 用户条码登录
     */
    public static String login_withbarcode = "workCenter?method=queryCenterByEmpWsInterface";


    /**
     * 开工
     */
    public static String start_work = "dispatchingOrderTrack?method=updateDispatchingStatus";

    /**
     * 追踪
     */
    public static String finish_work = "dispatchingOrderTrack?method=saveTrackBatchWsInterface";
}
