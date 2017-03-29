package com.leanway.baselibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtil {
    private static SharedPreferences mSp;
    public static final String PROCESS_TRA = "process_tra";
    public static final String PRODUCT_TRA = "product_tra";
    public static final String GET_MATERIAL = "get_material";
    public static final String STORE_MANAGE = "store_manage";
    public static final String RETURN_MANAGE = "return_manage";
    public static final String SALE_MANAGE = "sale_manage";
    public static final String CONFIG_SP = "config_sp";// config_sp.xml 文件  存放位置 ：/data/data/《包名》/shared_prefes
    public static final String URL = "url";
    public static final String PORT = "port";
    public static final String COMID = "com_id";
    public static final String USERNAME = "username";
    public static final String USERPASS = "userpass";
    public static final String REMENMBER = "remember_count";
    public static final String APK_UPDATE = "apk_update";//软件自动更新

    private static SharedPreferences getPreferencs(Context context) {
        if (mSp == null) {
            mSp = context.getSharedPreferences(CONFIG_SP, Context.MODE_PRIVATE);
        }
        return mSp;
    }

    // 保存布尔数据
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = getPreferencs(context);
        sp.edit().putBoolean(key, value).commit();
    }

    // 取布尔数据 ,返回的是false 默认值
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = getPreferencs(context);
        return sp.getBoolean(key, false);
    }

    // 取布尔数据 ,默认返回的是false
    public static boolean getBoolean(Context context, String key, boolean defvalue) {
        SharedPreferences sp = getPreferencs(context);
        return sp.getBoolean(key, defvalue);
    }

    // 保存字符串
    public static void putString(Context context, String key, String value) {
        SharedPreferences sp = getPreferencs(context);
        sp.edit().putString(key, value).commit();
    }

    // 取字符串数据 ,默认返回的是 null
    public static String getString(Context context, String key) {
        SharedPreferences sp = getPreferencs(context);
        return sp.getString(key, null);
    }

    // 取字符串数据 ,返回的是传递过来的值
    public static String getString(Context context, String key, String defvalue) {
        SharedPreferences sp = getPreferencs(context);
        return sp.getString(key, defvalue);
    }
}