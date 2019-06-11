/*
 *	Copyright (c) 2011, Yulong Information Technologies
 *	All rights reserved.
 *  
 *  @Project: AlarmTest
 *  @author: Robot	
 */
package com.transcendence.blackhole.ui.apkbus.alarm;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

/**
 * @author Robot
 * @weibo http://weibo.com/feng88724
 * @date Nov 18, 2011	
 */
public class ActionActivity extends Activity {
	private static int num = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.e("ActionActivity", "Activity New Message !" + num++);
		Button button = new Button(this);
		button.setText("我是由AlarmManager启动的！");
		setContentView(button);
	}
	
}	
