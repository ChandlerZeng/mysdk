package com.zcq.sdk.test.activity;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import com.zcq.sdk.test.http.OkHttpUtil;

import java.io.IOException;


/**
 * @author 1
 */
public class MyApplication extends Application
{
    private static MyApplication mInstance;
    private static Handler mHandler;
    public static Context CONTEXT;
    
    @Override
    public void onCreate()
    {
        super.onCreate();
        mInstance = this;
        CONTEXT = this;
    }

    public Handler getHandler(){
        return mHandler;
    }

    public static MyApplication getInstance()
    {
        mHandler = new Handler();
        return mInstance;
    }
    
    public boolean isNetConnected()
    {
        ConnectivityManager manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] infos = manager.getAllNetworkInfo();
        for (NetworkInfo info : infos) {
            if (info.isConnected()) {
                return true;
            }
        }
        return false;
    }
}
