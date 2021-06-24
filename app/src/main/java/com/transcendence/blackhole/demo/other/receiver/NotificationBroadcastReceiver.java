package com.transcendence.blackhole.demo.other.receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.transcendence.blackhole.index.IndexActivity;
import com.transcendence.core.utils.AppUtils;
import com.transcendence.core.utils.L;

/**
 * @Author Joephone on 2021/4/12 0012 下午 3:33
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class NotificationBroadcastReceiver extends BroadcastReceiver {

    public static final String TYPE = "type"; //这个type是为了Notification更新信息的，这个不明白的朋友可以去搜搜，很多

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        int type = intent.getIntExtra(TYPE, -1);

        if (type != -1) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancel(type);
        }

        if (action.equals("notification_clicked")) {
            //处理点击事件
            L.logE("clicked");

//            Notification.Builder builder = new Notification.Builder(context);
//
//            Intent i = new Intent();
//            ComponentName c = new ComponentName("com.xyyl.doctor.xinyuyiliao","com.xyyl.doctor.xinyuyiliao.activity.HomeAct");
//            i.setComponent(c);
//            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
//            builder.setContentIntent(pendingIntent);

            //判断app进程是否存活
            if(AppUtils.isAppAlive(context, "com.transcendence.blackhole")){
                L.logE( "the app process is alive");
                Intent mainIntent = new Intent(context, IndexActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(mainIntent);
            }else {
                L.logE( "the app process is not alive");
                Intent launchIntent = context.getPackageManager().
                        getLaunchIntentForPackage("com.transcendence.blackhole");
                launchIntent.setFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                Bundle args = new Bundle();
                context.startActivity(launchIntent);
            }

        }

        if (action.equals("notification_cancelled")) {
            //处理滑动清除和点击删除事件
            L.d("cancelled");
        }
    }
}
