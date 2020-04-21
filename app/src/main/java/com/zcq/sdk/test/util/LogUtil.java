package com.zcq.sdk.test.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Environment;
import android.util.Log;

public class LogUtil
{
	private static final String TAG = "MySdk";
    private static boolean IS_OUTPUT = false;
    private static boolean IS_DEBUG = true;
    
	public static void v(String msg)
	{
		v(TAG, msg);
	}
    
	public static void d(String msg)
	{
		d(TAG, msg);
	}
	
	public static void i(String msg)
	{
		i(TAG, msg);
	}
	
	public static void w(String msg)
	{
		w(TAG, msg);
	}

	public static void e(String msg)
	{
		e(TAG, msg);
	}

	public static void v(String tag, String msg)
	{
		if(IS_DEBUG)
		Log.v(tag, msg);
	}

	public static void d(String tag, String msg)
	{
		if(IS_DEBUG)
		Log.d(tag, msg);
	}	
	
	public static void i(String tag, String msg)
	{
		if(IS_DEBUG)
		Log.i(tag, msg);
	}
		
	public static void w(String tag, String msg)
	{
		if(IS_DEBUG)
		Log.w(tag, msg);
	}

	public static void e(String tag, String msg)
	{
		if(IS_DEBUG)
		Log.e(tag, msg);
	}

	
	public static void output(String msg)
	{
	    if (IS_DEBUG && IS_OUTPUT)
	    {
	        File file = Environment.getExternalStorageDirectory();
	        try
	        {
	            BufferedWriter fw = new BufferedWriter(new FileWriter(file.getAbsolutePath() + "/log_personalcenter.txt", true));
	            fw.write(msg + "\n");
	            fw.flush();
	            fw.close();
	        } catch (IOException e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
	}
	
	public static void setLogDebugEnabled(boolean isDebugEnabled) {
		if(isDebugEnabled){
			IS_DEBUG = true;
		}else{
			IS_DEBUG = false;
		}
	}
	
	public static void setLogOutputEnabled(boolean isOutputEnabled) {
		if(isOutputEnabled){
			IS_OUTPUT = true;
		}else{
			IS_OUTPUT = false;
		}
	}
}
