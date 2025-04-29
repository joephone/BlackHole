package com.transcendence.freeland.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author joephone
 * @date 2023/6/6
 * @desc
 */
public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
//        if (activeNetworkInfo == null) {
//            Toast.makeText(MyActivity.instance, "当前无网络连接！", 1).show();
//        }
//        if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
//            Toast.makeText(MyActivity.instance, "wifi连接成功！", 1).show();
//            return;
//        }
//        if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {//当前的connect不是wifi，并且是移动数据
//            Toast.makeText(MyActivity.instance, "移动数据流量连接！", 1).show();
//            return;
//        }
    }
}
