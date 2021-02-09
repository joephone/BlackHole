package com.transcendence.guard.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.transcendence.guard.app.GuardApp;

/**
 * @Author Joephone on 2021/2/9 0009 下午 2:59
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class BootCompleteReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        ((GuardApp)context.getApplicationContext()).correctSIM();
    }
}
