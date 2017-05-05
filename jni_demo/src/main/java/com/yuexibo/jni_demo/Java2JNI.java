package com.yuexibo.jni_demo;

/**
 * @author Xibo_Yue
 * @time 2017/5/5 17:47
 */

public class Java2JNI {
    static {
        System.loadLibrary("Java2C");
    }
    public native String java2C();
}
