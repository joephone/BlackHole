package com.transcendence.blackhole.activity.hardware.act;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.util.Log;

import com.transcendence.blackhole.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class IsTwoSdCardActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StorageManager storageManager = (StorageManager) getSystemService(Context.STORAGE_SERVICE);
		try {
			Class<?>[] paramClasses = {};
			Method getVolumePathsMethod = StorageManager.class.getMethod("getVolumePaths", paramClasses);
			getVolumePathsMethod.setAccessible(true);
			Object[] params = {};
			Object invoke = getVolumePathsMethod.invoke(storageManager, params);
			for (int i = 0; i < ((String[])invoke).length; i++) {
				StatFs stat = getStatFs(((String[])invoke)[i]);
				Log.i("", ((String[])invoke)[i] + " 剩余空间:"  + calculateSizeInMB(stat) );
			}
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		
	}

	
	private StatFs getStatFs(String path) {
		try {
			return new StatFs(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	private float calculateSizeInMB(StatFs stat) {
		if (stat != null) {
			return stat.getAvailableBlocks()
					* (stat.getBlockSize() / (1024f * 1024f));
		}
		return 0.0f;
	}
	
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

}
