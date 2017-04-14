package com.leanway.baselibrary.base;

import android.app.Application;
import android.content.Context;

/**
 * @author Xibo_Yue
 * @time 2017/2/13 17:53
 */
public class BaseLibraryManage{
    public static Context library_context;
    public static void baseLibraryInit(Application context){
        library_context = context;
    }
}
