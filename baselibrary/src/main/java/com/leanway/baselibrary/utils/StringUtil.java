package com.leanway.baselibrary.utils;

import android.support.annotation.Nullable;

import java.math.BigDecimal;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 岳希波
 * @time 2016/6/17 16:54
 */
public class StringUtil {


    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(@Nullable CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }


    /**
     * 判断字符串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断字符串是否为科学计数法
     * @param num
     * @return
     */
    public static boolean isScienceNum(String num){
        Pattern pattern = Pattern.compile("^((-?\\d+.?\\d*)[Ee]{1}(-?\\d+))$");
        Matcher isScNum = pattern.matcher(num);
        if (!isScNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 将字符串转换成Double型数据。如果转换失败，则返回默认值
     *
     * @param value 需要转换的字符串内容
     * @param defaultValue 默认Double值
     * @return Double值
     * @date 2015-11-16
     */
    public static double tryParseDouble(String value, double defaultValue) {
        try {
            double result = Double.parseDouble(value);
            return result;
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    /**
     * 将字符串转换成Integer型数据。如果转换失败，则返回默认值
     *
     * @param value 需要转换的字符串内容
     * @param defaultValue 默认Integer值
     * @return Integer数据
     * @date 2015-11-16
     */
    public static int tryParseInteger(String value, int defaultValue) {
        try {
            int result = Integer.parseInt(value);
            return result;
        } catch (Exception ex) {
            return defaultValue;
        }
    }


    /**
     * 显示double类型的数据
     * @param d
     * @return
     */
    public static String showDouble(double d){
        double c=d-(int)d;
        String res = "";
        if(c==0){
            res =(int)d + "";
        }else{
            res = d + "";
            if(isScienceNum(res)){
                BigDecimal bd = new BigDecimal(res);
                res = bd.toPlainString();
            }
        }
        return res;
    }


    /**
     * 比较两个double类型大小
     * @param a
     * @param b
     * @return
     */
    public static int compareDouble(double a,double b){


        BigDecimal data1 = new BigDecimal(a);
        BigDecimal data2 = new BigDecimal(b);
        int i = data1.compareTo(data2);
        if(i<0){
            return -1;
        }else if(i==0){
            return 0;
        }else{
            return 1;
        }

    }

    /**
     * 显示double类型的数据，当参数为String类型时
     * @param r
     * @return
     */
    public static String showDoubleString(String r){
        if("null".equals(r)||r==null){
            return 0+"";
        }else {
            double d = Double.parseDouble(r);
            return showDouble(d);
        }
    }


    public static boolean isEmpty(String str){
        if (str == null) {
            return true;
        } else if (str.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 字符串为 null时，设置不显示
     * @param str
     * @return
     */
    public static String showString(String str){
        if("null".equals(str)){
            return "";
        }else{
            return str;
        }
    }



    /**
     * 获取随机字符串
     * @param length
     * @return
     */
    public static String getRandomString(int length){
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }




}
