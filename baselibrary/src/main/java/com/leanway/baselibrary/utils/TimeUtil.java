package com.leanway.baselibrary.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 岳希波
 * @time 2016/5/24 14:52
 */
public class TimeUtil {

    /**
     * 获取年月日时分秒
     *
     * @return
     */
    public static String getTimeWithYear() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(System.currentTimeMillis()));
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        return year + "-" + addZero(month) + "-" + addZero(day) + " " + addZero(hour) + ":" + addZero(minute) + ":" + addZero(second);
    }



    /**
     * 获取年月日时分秒换行
     *
     * @return
     */
    public static String getTimeWithYearline() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(System.currentTimeMillis()));
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        return year + "-" + addZero(month) + "-" + addZero(day) + "\n" + addZero(hour) + ":" + addZero(minute) + ":" + addZero(second);
    }



    /**
     * 获取年月日
     *
     * @return
     */
    public static String getTimeDay(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(System.currentTimeMillis()));
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        return year + "-" + addZero(month) + "-" + addZero(day);
    }



    /**
     * 获取时间
     *
     * @return
     */
    public static String getTime() {

        Calendar c = Calendar.getInstance();
        c.setTime(new Date(System.currentTimeMillis()));
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        return addZero(hour) + ":" + addZero(minute) + ":" + addZero(second);

    }



    /**
     * 比较两个日期的大小，返回1代表date1大于date2,-1代表date1小于date2,0代表两个日期相等
     * @param DATE1
     * @param DATE2
     * @return
     */
    public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }


    /**
     * 时间个位数时，前面加0
     * @param i
     * @return
     */
    public static String addZero(int i) {
        if (i < 10) {
            return "0" + i;
        } else {
            return i + "";
        }
    }






}
