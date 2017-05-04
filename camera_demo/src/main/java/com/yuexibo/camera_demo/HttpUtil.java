package com.yuexibo.camera_demo;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class HttpUtil {
	private final static int CONNECT_TIMEOUT = 10000;
    private final static String DEFAULT_ENCODING = "UTF-8";
    
    public static String postData(String urlStr, String data){
        return postData(urlStr, data, "text/xml,charset=utf-8");
    }
	public static String postData(String urlStr, String data, String contentType){
		BufferedReader reader = null;
        try {
            URL url = new URL(urlStr);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(CONNECT_TIMEOUT);
            if(contentType != null){
                conn.setRequestProperty("content-type", contentType);
            }
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), DEFAULT_ENCODING);
            if(data == null){
                data = "";
            }
            writer.write(data); 
            writer.flush();
            writer.close();  

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), DEFAULT_ENCODING));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
        	System.out.println(e);
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
            }
        }
        return null;
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String doPost(String urlString,String imagUrl)
            throws IOException {
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        //connection.setRequestProperty("content-type","multipart/form-data;boundary=1321ZnGpDtePMx0KrHh_G0X99Yef9r8JZsRJSXC");
        //try里面拿到输出流，输出端就是服务器端
        try (BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream())) {
            InputStream is = new FileInputStream(imagUrl);
            BufferedInputStream bis = new BufferedInputStream(is);

            byte[] buf= new byte[1024];
            int length = 0;
            length = bis.read(buf);
            while(length!=-1) {
                bos.write(buf, 0, length);
                length = bis.read(buf);
            }
            bis.close();
            is.close();
            bos.close();
        }

        //下面是服务器端如果有返回数据的话，做接收用的
        StringBuilder response = new StringBuilder();
        try (Scanner in = new Scanner(connection.getInputStream())) {
            while (in.hasNextLine()) {
                response.append(in.nextLine());
                response.append("\n");
            }
        } catch (IOException e) {
            if (!(connection instanceof HttpURLConnection))
                throw e;
            InputStream err = ((HttpURLConnection) connection).getErrorStream();
            if (err == null)
                throw e;
            Scanner in = new Scanner(err);
            response.append(in.nextLine());
            response.append("\n");
            in.close();
        }

        return response.toString();
    }

}

