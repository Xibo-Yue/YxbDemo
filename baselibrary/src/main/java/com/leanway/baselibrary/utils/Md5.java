package com.leanway.baselibrary.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 字段MD5加密
 */
public class Md5 {

    public static void main(String[] args) {
        String a = "123";
        System.out.println(getMd5(a));
    }

    /**
     * 获取文本字段的MD5值
     * @param txt
     * @return
     */
    public static String getMd5(String txt) {
        String rs = "";
        String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] b = messageDigest.digest(txt.getBytes());
            StringBuffer resultSb = new StringBuffer();
            for (int i = 0; i < b.length; i++) {
                int n = b[i];
                if (n < 0)
                    n = 256 + n;
                int d1 = n / 16;
                int d2 = n % 16;
                resultSb.append(hexDigits[d1] + hexDigits[d2]);
            }
            rs = resultSb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
