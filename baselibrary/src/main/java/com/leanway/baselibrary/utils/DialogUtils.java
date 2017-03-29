package com.leanway.baselibrary.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

/**
 * @author Xibo_Yue
 * @time 2016/11/1 10:09
 */
public class DialogUtils {

    /**
     * 提示错误信息
     * @param errorMessage
     */
    public static void showDis(String errorMessage, Context context,String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(errorMessage);
        builder.setTitle(title);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    /***
     * 获取一个耗时等待对话框
     * @param context
     * @param message
     * @return
     */
    public static ProgressDialog getWaitDialog(Context context, String message) {
        ProgressDialog waitDialog = new ProgressDialog(context);
        if (!TextUtils.isEmpty(message)) {
            waitDialog.setMessage(message);
        }
        waitDialog.setCanceledOnTouchOutside(false);
        return waitDialog;
    }

    public static android.support.v7.app.AlertDialog.Builder getConfirmDialog(Context context, String message, DialogInterface.OnClickListener onOkClickListener, DialogInterface.OnClickListener onCancleClickListener) {
        android.support.v7.app.AlertDialog.Builder builder = getDialog(context);
        builder.setMessage(message);
        builder.setPositiveButton("确定", onOkClickListener);
        builder.setNegativeButton("取消", onCancleClickListener);
        return builder;
    }

    /***
     * 获取一个dialog
     * @param context
     * @return
     */
    public static android.support.v7.app.AlertDialog.Builder getDialog(Context context) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        return builder;
    }


}
