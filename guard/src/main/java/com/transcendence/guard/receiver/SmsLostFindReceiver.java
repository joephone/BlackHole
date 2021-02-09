package com.transcendence.guard.receiver;

import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsMessage;
import android.text.TextUtils;

import com.transcendence.core.utils.L;

/**
 * @Author Joephone on 2021/2/9 0009 下午 4:27
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class SmsLostFindReceiver extends BroadcastReceiver {

    private SharedPreferences sp;

    @Override
    public void onReceive(Context context, Intent intent) {
        sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        boolean protecting = sp.getBoolean("protecting",true);
        if(protecting){
            DevicePolicyManager manager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
            Object[] objs = (Object[]) intent.getExtras().get("pdus");
            for (Object obj : objs) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[])obj);
                String sender = smsMessage.getOriginatingAddress();
                String body = smsMessage.getMessageBody();
                String safePhone = sp.getString("safephone",null);

                if(!TextUtils.isEmpty(safePhone) && sender.equals(safePhone)){
                    if("#*location*#".equals(body)){
                        L.d("");
//                        Intent service = new Intent(context,GPS)
                    }
                }
            }
        }
    }
}
