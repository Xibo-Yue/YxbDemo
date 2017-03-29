package com.leanway.baselibrary.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import java.io.File;
import java.util.List;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * @author 岳希波
 * @funciton 获取手机信息的工具类
 */
public class TDevice {

    private static InputMethodManager imm;
    /**
     * dp转px
     *
     * @param dp
     * @param context
     * @return
     */
    public static float dp2px(float dp, Application context) {
        return dp * getDisplayMetrics(context).density;
    }

    /**
     * px转dp
     *
     * @param px
     * @param context
     * @return
     */
    public static float px2dp(float px, Application context) {
        return px / getDisplayMetrics(context).density;
    }

    public static DisplayMetrics getDisplayMetrics(Application context) {
        return context.getResources().getDisplayMetrics();
    }

    public static float getScreenHeight(Application context) {
        return getDisplayMetrics(context).heightPixels;
    }

    public static float getScreenWidth(Application context) {
        return getDisplayMetrics(context).widthPixels;
    }

    public static int getStatusBarHeight(Activity context) {
        Rect rectangle = new Rect();
        Window window = context.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        return rectangle.top;
    }

    public static boolean hasInternet(Application context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isAvailable() && info.isConnected();
    }

    public static boolean isPortrait(Application context) {
        return context.getResources().getConfiguration()
                .orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    /**
     * 是否是平板电脑
     *
     * @return
     */
    public static boolean isTablet(Application context) {
        int s = context.getResources().getConfiguration().screenLayout;
        s &= Configuration.SCREENLAYOUT_SIZE_MASK;
        return s >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }


    /**
     * 隐藏软键盘
     *
     * @param view
     */
    public static void hideSoftKeyboard(View view) {
        if (view == null)
            return;
        View mFocusView = view;

        Context context = view.getContext();
        if (context != null && context instanceof Activity) {
            Activity activity = ((Activity) context);
            mFocusView = activity.getCurrentFocus();
        }
        if (mFocusView == null)
            return;
        mFocusView.clearFocus();
        InputMethodManager manager = (InputMethodManager) mFocusView.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(mFocusView.getWindowToken(), InputMethodManager
                .HIDE_NOT_ALWAYS);
    }

    /**
     * 软键盘弹出隐藏
     *
     * @param context
     */
    public static void hideOrShowSoft(Context context) {
        if (null==imm)
        imm  = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }
    public static void hideSoft(Context context,View view) {
        if (null==imm)
            imm  = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    /**
     * 显示软键盘
     *
     * @param view
     */
    public static void showSoftKeyboard(View view) {
        if (view == null)
            return;
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        if (!view.isFocused())
            view.requestFocus();

        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, 0);
    }

    /**
     * 是否有应用市场
     *
     * @param context
     * @return
     */
    public static boolean isHaveMarket(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_APP_MARKET);
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
        return infos.size() > 0;
    }


    /**
     * 获得自身版本code
     *
     * @return 版本号
     */
    public static int getVersionCode(Application context) {
        return getVersionCode(context.getPackageName(), context);
    }

    /**
     * 获取指定包名的版本code
     *
     * @param packageName
     * @param context
     * @return 版本号
     */
    public static int getVersionCode(String packageName, Application context) {
        try {
            return context
                    .getPackageManager()
                    .getPackageInfo(packageName, 0)
                    .versionCode;
        } catch (PackageManager.NameNotFoundException ex) {
            return 0;
        }
    }

    /**
     * 获得版本名称
     *
     * @return
     */
    public static String getVersionName(Application context) {
        try {
            return context
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0)
                    .versionName;
        } catch (PackageManager.NameNotFoundException ex) {
            return "undefined version name";
        }
    }

    /**
     * 安装apk
     *
     * @param context
     * @param file
     */
    public static void installAPK(Context context, File file) {
        if (file == null || !file.exists())
            return;
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 指定activity是否打开
     *
     * @param context
     * @param packageName
     * @param activityName
     * @return
     */
    public static boolean openAppActivity(Context context,
                                          String packageName,
                                          String activityName) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ComponentName cn = new ComponentName(packageName, activityName);
        intent.setComponent(cn);
        try {
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        }
    }

    /**
     * wifi是否打开
     *
     * @return
     */
    public static boolean isWifiOpen(Application context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null)
            return false;
        if (!info.isAvailable() || !info.isConnected())
            return false;
        if (info.getType() != ConnectivityManager.TYPE_WIFI)
            return false;
        return true;
    }

    /**
     * 复制内容到剪贴板
     *
     * @param string
     * @param context
     */
    public static void copyTextToBoard(String string, Application context) {
        if (TextUtils.isEmpty(string))
            return;
        ClipboardManager clip = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        clip.setText(string);
        ToastUtil.show(context, "复制成功");
    }

    /**
     * 调用系统安装了的应用分享
     *
     * @param context
     * @param title
     * @param url
     */
    public static void showSystemShareOption(Activity context,
                                             final String title, final String url) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享：" + title);
        intent.putExtra(Intent.EXTRA_TEXT, title + " " + url);
        context.startActivity(Intent.createChooser(intent, "选择分享"));
    }

}
