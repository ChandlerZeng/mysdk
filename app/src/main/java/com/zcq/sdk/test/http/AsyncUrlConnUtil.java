package com.zcq.sdk.test.http;

import android.os.Handler;

/**
 * Created by zcq on 2018/1/11.
 */

public class AsyncUrlConnUtil {
    public interface HttpCallback{
        void onSuccess(String response);
        void onError();
    }
    public static void asyncGet(final String url, final HttpCallback callback){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String response = UrlConnUtil.get(url);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(response!=null){
                            callback.onSuccess(response);
                        }else{
                            callback.onError();
                        }
                    }
                });

            }
        }).start();
    }

    public static void asyncPost(final String url, final String content, final HttpCallback callback){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String response = UrlConnUtil.post(url,content);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(response!=null){
                            callback.onSuccess(response);
                        }else{
                            callback.onError();
                        }
                    }
                });

            }
        }).start();
    }
}
