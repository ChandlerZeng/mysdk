package com.ddt.main.service.sdk;

import java.util.HashMap;
import java.util.Map;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import com.ddt.main.service.sdk.CarStatus.Status;

public class DataParams extends ContentObserver{
	
	private static final String TAG = DataParams.class.getSimpleName();
	
	protected static String AUTHORITY = "com.ddt.car.status.sets";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_VALUE = "value";
	
	protected Uri mUri;
	private OnDataChangeListener mDataChangeListener;
	private ContentResolver mContentResolver;
	private Map<String,String> mDatas = new HashMap<String,String>();
	
	public DataParams(Context context,Status data) {
		super(new Handler());
		mContentResolver = context.getContentResolver();
		mUri = Uri.parse("content://" + AUTHORITY + "/" + data.getTable());
		int size = data.getKeys().length;
		for (int i = 0; i < size; i++) {
			mDatas.put(data.getKeys()[i], "0");
		}
		init();
		mContentResolver.registerContentObserver(mUri, true, this);
	}
	
	private void init() {
		Cursor cursor = mContentResolver.query(mUri,new String[] {COLUMN_NAME, COLUMN_VALUE}, null,null, null);
		if (cursor != null) {
			while(cursor.moveToNext()){
				String key   = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
				String value = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE));
				if(mDatas != null && mDatas.keySet().contains(key)){
					mDatas.put(key, value);
				}
			}
			cursor.close();
		}
	}
	
	public void setParams(String[] keys,String[] values){
		if(keys.length != 0 && keys.length == values.length){
			for (int i = 0; i < values.length; i++) {
				setParam(keys[i], values[i]);
			}
		}else{
			Log.w(TAG, "the keys.length = 0 or keys.length ≠ values.length");
		}
	}
	
	/**
	 * 修改值
	 * @param key {@link CarStatus}
	 * @param value
	 */
	public void setParam(String key,String value){
		if(mDatas != null && mDatas.containsKey(key)){
			mDatas.put(key, value);
			updateContentProvider(key, value);
		}
	}
	
	/**
	 * 修改值
	 * @param key {@link CarStatus}
	 * @param value
	 */
	public void setParam(String key,int value){
		setParam(key, value + "");
	}
	
	/**
	 * 获取值
	 * @param key {@link CarStatus}
	 * @return
	 */
	public String getParam(String key){
		if(mDatas != null && mDatas.containsKey(key)){
			return mDatas.get(key);
		}
		return null;
	}
	
	/**
	 * 获取值
	 * @param key {@link CarStatus}
	 * @return
	 */
	public int getParamInt(String key,int def){
		String value = getParam(key);
		return value == null?def:Integer.parseInt(value);
	}
	
	public boolean isOn(String key){
		return getParamInt(key, CarStatus.STATE_OFF) == CarStatus.STATE_ON;
	}
	
	public boolean isDef(String key){
		return getParamInt(key, CarStatus.STATE_DEF) == CarStatus.STATE_DEF;
	}
	
	/**
	 * 更新状态值
	 * @param name
	 * @param value
	 */
	private int updateContentProvider(String name, String value) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_VALUE, value);
		String where = COLUMN_NAME + " = ?";
		String[] arg = new String[] { name };
		int result = -1;
		try{
			result = mContentResolver.update(mUri, values, where, arg);
		}catch(Exception e){
			Log.e(TAG, "error info :" + e.getLocalizedMessage());
		}
		return result;
	}
	
	/**
	 * 数据改变监听 
	 * @param listener {@link OnDataChangeListener}
	 */
	public void registerDataChangeListener(OnDataChangeListener listener){
		mDataChangeListener = listener;
	}
	
	/**
	 * 数据改变监听 
	 */
	public void unregisterDataChangeListener(){
		mDataChangeListener = null;
	}
	
	/**
	 * 释放数据
	 */
	public void release(){
		try{
			mContentResolver.unregisterContentObserver(this);
		}catch(Exception e){
			Log.e(TAG, "unregisterContentObserver error,msg -> " + e.getLocalizedMessage());
		}
		mDataChangeListener = null;
		mDatas.clear();
		mDatas = null;
	}
	
	@Override
	public void onChange(boolean selfChange, Uri uri) {
		super.onChange(selfChange, uri);
		if(mUri.equals(uri)){
			Cursor cursor = mContentResolver.query(mUri,new String[] {COLUMN_NAME, COLUMN_VALUE}, null,null, null);
			if (cursor != null) {
				while(cursor.moveToNext()){
					String key   = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
					String value = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE));
					if(mDatas != null && mDatas.keySet().contains(key)){
						String dValue = mDatas.get(key);
						if((dValue != null && !dValue.equals(value))
								|| (value != null && !value.equals(dValue))){
							mDatas.put(key, value);
							if(mDataChangeListener != null){
								mDataChangeListener.onChange(key, value);
							}
						}
					}
				}
				cursor.close();
			}
		}
	}
	
	/**
	 * 数据改变监听
	 * @author lishanfeng
	 */
	public interface OnDataChangeListener{
		void onChange(String key, String value);
	}
	
}
