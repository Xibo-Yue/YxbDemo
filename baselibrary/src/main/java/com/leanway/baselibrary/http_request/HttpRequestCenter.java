package com.leanway.baselibrary.http_request;

import com.leanway.baselibrary.utils.Md5;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * @author Xibo_Yue
 * @time 2017/2/13 13:56
 */
public class HttpRequestCenter {

    /**
     * 用户登录
     *
     * @param url
     * @param username
     * @param userpass
     * @param comid
     * @param listener
     */
    public static void login(String url, String username, String userpass, String
            comid, final RequestListener listener) {
        OkHttpUtils.post().url(url).addParams("userName", username)
                .addParams("password", Md5.getMd5(userpass)).addParams("compId", comid).build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.requestFail();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response.contains(RequestError.LOGIN_TIMEOUT)) {
                            listener.loginTimeOut();
                        } else {
                            listener.requestSuccess(response);
                        }
                    }
                });

    }

    /**
     * 获取公司列表
     *
     * @param getComList
     * @param user_name_name
     * @param getComIdListener
     */
    public static void getComId(String getComList, String user_name_name, final RequestListener
            getComIdListener) {
        OkHttpUtils.post().url(getComList).addParams("userName", user_name_name).build().execute
                (new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        getComIdListener.requestFail();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        getComIdListener.requestSuccess(response);
                    }
                });
    }

    /**
     * 获取SqlDatas
     *
     * @param productionchildsearchno 查询号
     * @param startTime               开始时间
     * @param endTime                 结束时间
     * @param workCenterName          工作组
     * @param produceName             工序名称
     * @return SqlDatas
     */
    public static String getSqlDatas(String productionchildsearchno, String startTime, String
            endTime, String workCenterName, String produceName) {
        try {
            JSONObject object = new JSONObject();
            JSONObject childObject = null;
            JSONArray array = new JSONArray();
            if (!"".equals(productionchildsearchno)) {
                childObject = new JSONObject();
                childObject.put("fieldname", "dco.productionchildsearchno");
                childObject.put("fieldtype", "varchar_select2");
                childObject.put("value", productionchildsearchno);
                childObject.put("logic", "and");
                childObject.put("ope", "in");
                array.put(childObject);
            }
            if (!"".equals(startTime)) {
                childObject = new JSONObject();
                childObject.put("fieldname", "dco.adjuststarttime");
                childObject.put("fieldtype", "datetime");
                childObject.put("value", startTime);
                childObject.put("logic", "and");
                childObject.put("ope", ">=");
                array.put(childObject);
            }
            if (!"".equals(endTime)) {
                childObject = new JSONObject();
                childObject.put("fieldname", "dco.adjuststarttime");
                childObject.put("fieldtype", "datetime");
                childObject.put("value", endTime);
                childObject.put("logic", "and");
                childObject.put("ope", "<=");
                array.put(childObject);
            }
            if (!"".equals(workCenterName)) {
                childObject = new JSONObject();
                childObject.put("fieldname", "wcg.shortname");
                childObject.put("fieldtype", "varchar");
                childObject.put("value", workCenterName);
                childObject.put("logic", "and");
                childObject.put("ope", "like");
                array.put(childObject);
            }
            if (!"".equals(produceName)) {
                childObject = new JSONObject();
                childObject.put("fieldname", "sp.shortname");
                childObject.put("fieldtype", "varchar");
                childObject.put("value", produceName);
                childObject.put("logic", "and");
                childObject.put("ope", "like");
                array.put(childObject);
            }
            object.put("sqlDatas", array);
            return object.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取派工单列表
     *
     * @param dispatchingstatus 派工单状态  0：未开工，2已开工，3完工
     * @param searchValue       查询关键字
     * @param sqlDatas          查询条件
     * @param length            每页取几条数据
     * @param start             从第几条开始
     * @param getComIdListener  回调
     */
    public static void getOrderList(String dispatchingstatus, String searchValue, String
            sqlDatas, String
                                            length, String start, String personcenterid, String comid, final RequestListener
                                            getComIdListener) {
        OkHttpUtils.post().url(RootUrl.ROOT_PATH + HttpUrl.getPachOrderList).addParams
                ("dispatchingstatus", dispatchingstatus)
                .addParams("searchValue", searchValue).addParams("sqlDatas", sqlDatas).addParams
                ("length", length).addParams("start", start).addParams("personcenterid",
                personcenterid).addParams("compid", comid).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                getComIdListener.requestFail();
            }

            @Override
            public void onResponse(String response, int id) {
                getComIdListener.requestSuccess(response);
            }
        });
    }

    /**
     * 用户登录
     *
     * @param url
     * @param userCode
     * @param comid
     * @param listener
     */
    public static void loginUseBarcode(String url, String userCode, String
            comid, final RequestListener listener) {
        OkHttpUtils.post().url(url).addParams("barcode", userCode).addParams("compid", comid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.requestFail();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response.contains(RequestError.LOGIN_TIMEOUT)) {
                            listener.loginTimeOut();
                        } else {
                            listener.requestSuccess(response);
                        }
                    }
                });

    }

    /**
     * 开工
     *
     * @param url
     * @param orderid
     * @param starttime
     * @param listener
     */
    public static void startWork(String url, String orderid, String
            starttime, final RequestListener listener) {
        OkHttpUtils.post().url(url).addParams("orderid", orderid).addParams("starttime", starttime)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                listener.requestFail();
            }

            @Override
            public void onResponse(String response, int id) {
                if (response.contains(RequestError.LOGIN_TIMEOUT)) {
                    listener.loginTimeOut();
                } else {
                    listener.requestSuccess(response);
                }
            }
        });
    }

    public static void finishWork(String url, String trackinfo, final RequestListener listener) {
        OkHttpUtils.post().url(url).addParams("trackinfo", trackinfo).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                listener.requestFail();
            }

            @Override
            public void onResponse(String response, int id) {
                if (response.contains(RequestError.LOGIN_TIMEOUT)) {
                    listener.loginTimeOut();
                } else {
                    listener.requestSuccess(response);
                }
            }
        });
    }


}
