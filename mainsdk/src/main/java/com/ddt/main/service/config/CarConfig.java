package com.ddt.main.service.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.PowerManager;
import android.view.WindowManager;

import com.ddt.main.service.util.Logger;

public class CarConfig {
	
	private volatile static CarConfig mInstance;
	private static final String CONFIG_FILE_NAME = "config.properties";
	private static final String CONFIG_FILE_PATH = "sdcard/yfdata/" + CONFIG_FILE_NAME;
	private static final String CONFIG_SYSTEM_FILE_PATH = "config/" + CONFIG_FILE_NAME;
	private Map<String,String> mUpdateConfigs = new HashMap<String,String>();
	private Map<String,String> mInitConfigs = new HashMap<String,String>();
	
	private Object sLock = new Object();
	private ConfigRunnable mConfigRunnable = new ConfigRunnable();
	private Context mContext;
	private String mSourceFilePath;
	private AlertDialog mConfigDialog;
	
	private CarConfig(Context context) {
		mContext = context.getApplicationContext();
		initConfigs();
		resetUpdateConfigsData();
	}
	
	private AlertDialog initDialog(){
		return new AlertDialog.Builder(mContext)
		.setMessage("update config file?")
		.setTitle("update tips")
		.setPositiveButton("confirm", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				copyConfig();
			}
		}).setNegativeButton("cancel", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				cancel();
			}
		}).create();
	}
	
	private void cancel(){
		mConfigDialog.dismiss();
	}
	
	public static CarConfig getInstance(Context context){
		if(mInstance == null){
			synchronized (CarConfig.class) {
				if(mInstance == null){
					mInstance = new CarConfig(context);
				}
			}
		}
		return mInstance;
	}
	
	public String getValue(String key){
		if(!mUpdateConfigs.isEmpty() && mUpdateConfigs.containsKey(key)){
			return mUpdateConfigs.get(key).trim();
		}else if(!mInitConfigs.isEmpty() && mInitConfigs.containsKey(key)){
			return mInitConfigs.get(key).trim();
		}
		return null;
	}
	
	public int[] getIntArrayValue(String key,String spit){
		String value = getValue(key);
		if(value == null)return null;
		String[] values = value.split(spit);
		int[] retValues = new int[values.length];
		for (int i = 0; i < values.length; i++) {
			try{
				retValues[i] = Integer.parseInt(values[i]);
			}catch(Exception e){
				Logger.e("get config value error,error info:" + e.getLocalizedMessage());
			}
		}
		return retValues;
	}
	
	public String[] getArrayValue(String key,String spit){
		String value = getValue(key);
		if(value == null)return null;
		return value.split(spit);
	}
	public boolean containsKey(String key){
		return mUpdateConfigs.containsKey(key) || mInitConfigs.containsKey(key);
	}

	public void remountUsb(String path) {
		File file = new File(path + "/" + CONFIG_FILE_NAME);
		if(file != null && file.exists()){
			mSourceFilePath = file.getPath();

			if(mConfigDialog == null){
				mConfigDialog = initDialog();
				mConfigDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
			}
			
			if(!mConfigDialog.isShowing()){
				mConfigDialog.show();
			}
		}
	}
	
	public void resetUpdateConfigsData(){
		mConfigRunnable.isCopyFile = false;
		new Thread(mConfigRunnable).start();
	}
	
	private void copyConfig(){
		cancel();
		mConfigRunnable.isCopyFile = true;
		mConfigRunnable.configPath = mSourceFilePath;
		new Thread(mConfigRunnable).start();
	}
	
	class ConfigRunnable implements Runnable{
		
		public boolean isCopyFile = false;
		public String configPath;
		
		@Override
		public void run() {
			synchronized (sLock) {
				
				File configFile = new File(CONFIG_FILE_PATH);
				Logger.d("config run , is copy file:"+isCopyFile + ",source config path:"+configPath);
				
				if(isCopyFile && configPath != null && configPath.trim().length() > 0){
					try {
						
						if(configFile != null && configFile.exists()){
							configFile.delete();
						}
						configFile.createNewFile();
						
						FileOutputStream outputStream = new FileOutputStream(configFile);
						BufferedReader reader = new BufferedReader(new FileReader(new File(configPath)));
						String data = null;
						while ((data = reader.readLine()) != null) {
							outputStream.write(data.getBytes());
							outputStream.write("\t\n".getBytes());
						}
						outputStream.flush();
						outputStream.close();
						reader.close();
						
						mUpdateConfigs.clear();
						((PowerManager) mContext.getSystemService(Context.POWER_SERVICE)).reboot(""); 
						return;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				mUpdateConfigs.clear();
				readConfigs(configFile,mUpdateConfigs);
				
			}
		}
	}
	
	private void initConfigs() {
		File initConfigFile = new File(CONFIG_SYSTEM_FILE_PATH);
		if(initConfigFile != null && initConfigFile.exists() && mInitConfigs.size() == 0){
			readConfigs(initConfigFile, mInitConfigs);
		}else{
			Logger.i("not init config!");
		}
		
		File configFile = new File(CONFIG_FILE_PATH);
		if(configFile != null && configFile.exists()){
			readConfigs(configFile,mUpdateConfigs);
		}
	}
	
	private void readConfigs(File configFile,Map<String,String> configMap) {
		if(configFile == null || !configFile.exists())return;
		
		Properties config = new Properties();
		try {
			config.load(new FileInputStream(configFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(!config.isEmpty()){
			Iterator<Entry<Object, Object>> iterator = config.entrySet().iterator();
			while(iterator.hasNext()){
				Entry<Object, Object> entry = iterator.next();
				String key = (String)entry.getKey();
				String value = (String)entry.getValue();
				configMap.put(key,value);
				Logger.d("CarConfig prop :"  + key + "--->" + value);
			}
		}
	}
}
