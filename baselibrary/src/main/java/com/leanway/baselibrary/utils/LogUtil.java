package com.leanway.baselibrary.utils;

import android.util.Log;

/**
 *岳希波
 *
 */
public class LogUtil {
	private static boolean is_debug=true;
	public static void i(String tag,String msg){
		if(is_debug){
			Log.i(tag, msg);
		}
	}
	public static void v(String tag,String msg){
		if(is_debug){
			Log.v(tag, msg);
		}
	}
	public static void d(String tag,String msg){
		if(is_debug){
			Log.d(tag, msg);
		}
	}

}
