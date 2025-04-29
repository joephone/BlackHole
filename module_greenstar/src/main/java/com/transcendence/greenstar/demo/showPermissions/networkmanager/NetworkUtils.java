package com.transcendence.greenstar.demo.showPermissions.networkmanager;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;

import androidx.appcompat.app.AlertDialog;

public class NetworkUtils {
    /**
     * 判断当前网络状态是否可用
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager connMgr = (ConnectivityManager)context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connMgr == null){
            return false;
        }
        NetworkInfo[] info = connMgr.getAllNetworkInfo();
        for(NetworkInfo info0 : info){
            if(info0.getState() == NetworkInfo.State.CONNECTED){
                return true;
            }
        }
        return false;
    }

//    public static NetType getNetType(Context context){
//        ConnectivityManager connMgr = (ConnectivityManager)context
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//        if(connMgr == null){
//            return NetType.NONE;
//        }
//        //获取当前激活的网络连接状态
//        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//        if(networkInfo == null){
//            return NetType.NONE;
//        }
//
//        int nType = networkInfo.getType();
//        if(nType == ConnectivityManager.TYPE_MOBILE){
//            return NetType.MOBILE;
//        }else if(nType == ConnectivityManager.TYPE_WIFI){
//            return NetType.WIFI;
//        }
//        return NetType.NONE;
//    }

    /*
     * 判断网络连接是否已开
     * 2012-08-20
     *true 已打开  false 未打开
     * */
    public static boolean isConn(Context context){
        boolean bisConnFlag=false;
        ConnectivityManager conManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = conManager.getActiveNetworkInfo();
        if(network!=null){
            bisConnFlag=conManager.getActiveNetworkInfo().isAvailable();
        }
        return bisConnFlag;
    }

    /**
     * 打开网络设置页面
     */
    public static void setNetworkMethod(Activity context){
        //提示对话框
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("网络设置提示").setMessage("网络连接不可用,是否进行设置?").setPositiveButton("设置", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Intent intent=null;
                //判断手机系统的版本  即API大于10 就是3.0或以上版本
                if(android.os.Build.VERSION.SDK_INT>10){
//                    intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                    intent =  new Intent(Settings.ACTION_WIFI_SETTINGS);
                }else{
                    intent = new Intent();
                    ComponentName component = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
                    intent.setComponent(component);
                    intent.setAction("android.intent.action.VIEW");
                }
                context.startActivity(intent);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        }).show();
    }
}
