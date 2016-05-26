package com.itheima.redbaby.application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.itheima.redbaby.service.ECServiceManager;
import com.itheima.redbaby.service.IECManager;
import com.itheima.redbaby.util.Logger;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Environment;
import android.os.IBinder;


public class ECApplication extends Application {

	/** 缓存路径 */
	private static String cacheDir;
 	
	private IECManager ecManager;
	private List<Activity> records = new ArrayList<Activity>();
	private static final String TAG = "ECApplication";
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		bindService(new Intent(this, ECServiceManager.class), new ECServiceConnection(), Context.BIND_AUTO_CREATE);
		initCacheDirPath();
	}

	public static String getCacheDirPath() {
		return cacheDir;
	}
	
	
	private void initCacheDirPath() {
		File f;
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			f = new File(Environment.getExternalStorageDirectory() + "/.redbaby/");
			if (!f.exists()) {
				f.mkdir();
			}
 		} else {
			f = getApplicationContext().getCacheDir();
		}
		cacheDir = f.getAbsolutePath();
	}
	
	public IECManager getEcManager() {
		return ecManager;
	}

	private class ECServiceConnection  implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Logger.d(TAG, "onServiceConnected");
			ecManager = (IECManager) service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
	}
	
	public void addActvity(Activity activity) {
		records.add(activity);
		Logger.d(TAG, "Current Acitvity Size :" + getCurrentActivitySize());
	}
	
	public void removeActvity(Activity activity) {
		records.remove(activity);
		Logger.d(TAG, "Current Acitvity Size :" + getCurrentActivitySize());
	}
	
	public void exit() {
		for (Activity activity : records) {
			activity.finish();
		}
 	}
	
	public int getCurrentActivitySize() {
		return records.size();
 	}
}
