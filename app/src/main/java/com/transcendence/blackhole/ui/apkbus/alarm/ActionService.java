/*
 *	Copyright (c) 2011, Yulong Information Technologies
 *	All rights reserved.
 *  
 *  @Project: AlarmTest
 *  @author: Robot	
 */
package com.transcendence.blackhole.ui.apkbus.alarm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * @author Robot
 * @weibo http://weibo.com/feng88724
 * @date Nov 18, 2011	
 */
public class ActionService extends Service {
	private static int num = 0;
	/* (non-Javadoc)
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		Log.e("ActionService", "Service New Message !" + num++);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.e("ActionService", "----------");
		return super.onStartCommand(intent, flags, startId);
	}
}