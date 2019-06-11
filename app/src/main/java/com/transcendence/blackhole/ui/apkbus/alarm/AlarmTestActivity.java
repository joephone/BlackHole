package com.transcendence.blackhole.ui.apkbus.alarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.transcendence.blackhole.R;

public class AlarmTestActivity extends Activity {
	/** Called when the ui is first created. */
	AlarmManager am;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_apkbus_alarm);

		am = (AlarmManager) getSystemService(ALARM_SERVICE);

		Button btn1 = (Button) findViewById(R.id.id_btn1);
		Button btn2 = (Button) findViewById(R.id.id_btn2);
		Button btn3 = (Button) findViewById(R.id.id_btn3);

		btn1.setOnClickListener(onclick);
		btn2.setOnClickListener(onclick);
		btn3.setOnClickListener(onclick);
	}

	OnClickListener onclick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			long now = System.currentTimeMillis();
			PendingIntent pi = null;
			switch (v.getId()) {
			case R.id.id_btn1:
				pi = PendingIntent.getBroadcast(AlarmTestActivity.this, 0,
						new Intent(AlarmTestActivity.this,
								ActionBroadCast.class),
						Intent.FLAG_ACTIVITY_NEW_TASK);
				
				break;
			case R.id.id_btn2:
				pi = PendingIntent
						.getService(AlarmTestActivity.this, 0, new Intent(
								AlarmTestActivity.this, ActionService.class),
								Intent.FLAG_ACTIVITY_NEW_TASK);
				break;
			case R.id.id_btn3:
				pi = PendingIntent
						.getActivity(AlarmTestActivity.this, 0, new Intent(
								AlarmTestActivity.this, ActionActivity.class),
								Intent.FLAG_ACTIVITY_NEW_TASK);
				break;
			default:
				break;
			}
			//每3秒发送一次
			am.setInexactRepeating(AlarmManager.RTC_WAKEUP, now, 3000, pi);
			//取消闹铃
			am.cancel(pi);
			
			//只发送一次
			//am.set(AlarmManager.RTC_WAKEUP, now, pi);
		}
	};

}