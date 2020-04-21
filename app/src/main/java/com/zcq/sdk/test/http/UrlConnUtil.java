package com.zcq.sdk.test.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.zcq.sdk.test.util.LogUtil;

/**
 * Created by zcq on 2018/1/11.
 */

public class UrlConnUtil {
    private String response;

    public static String get(String url){
        HttpURLConnection urlConnection = null;
        try {
            URL u = new URL(url);
            urlConnection = (HttpURLConnection) u.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(10000);
            urlConnection.setDoOutput(true);
            int resCode = urlConnection.getResponseCode();
            if(resCode==200){
                InputStream is = urlConnection.getInputStream();
                String content = getStringFromInputString(is);
                return content;
            }else{
//                throw new NetworkErrorException("response status is "+resCode);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
        }
        return null;

    }

    public static String post(String url,String content){
        HttpURLConnection urlConn = null;
        try {
            URL u = new URL(url);
            urlConn = (HttpURLConnection) u.openConnection();
            urlConn.setRequestMethod("POST");
            urlConn.setReadTimeout(10000);
            urlConn.setConnectTimeout(10000);
            urlConn.setDoOutput(true);
            OutputStream os = urlConn.getOutputStream();
            os.write(content.getBytes());
            os.flush();
            os.close();
            int resCode = urlConn.getResponseCode();
            if(resCode==200){
                InputStream is = urlConn.getInputStream();
                String response = getStringFromInputString(is);
                return response;
            }else{
//                throw new NetworkErrorException("response status code is "+resCode);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(urlConn!=null){
                urlConn.disconnect();
            }
        }
        return null;
    }

    private static String getStringFromInputString(InputStream inputStream) throws IOException{
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = -1;
        while((len = inputStream.read(buffer))!=-1){
            baos.write(buffer,0,len);
            LogUtil.i("len:"+len);
        }
        inputStream.close();
        String response = baos.toString();
        baos.close();
        return response;
    }
}
