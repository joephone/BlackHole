/*
 *	Copyright (c) 2011, Yulong Information Technologies
 *	All rights reserved.
 *  
 *  @Project: AlarmTest
 *  @author: Robot	
 */
package com.transcendence.blackhole.activity.apkbus.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author Robot
 * @weibo http://weibo.com/feng88724
 * @date Nov 18, 2011	
 */
public class ActionBroadCast extends BroadcastReceiver {
	
	private static int num = 0;
	/* (non-Javadoc)
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.e("ActionBroadCast", "New Message !" + num++);
	}

}
