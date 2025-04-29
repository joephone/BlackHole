package com.transcendence.freeland.ble.test2;


import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

/**
 * 网络检测
 * Created by GXS on 2016/5/13.
 */
public class CheckNetWork {

    private AlertDialog.Builder builder;

    /**
     * 检测网络连接
     *
     * @param con
     * @return
     */
    public boolean isNetworkAvailable(Context con) {
        ConnectivityManager cm = (ConnectivityManager) con
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null)
            return false;
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        if (netinfo == null) {
            return false;
        }
        if (netinfo.isConnected()) {
            return true;
        }
        return false;
    }

    /**
     * 提示设置网络连接对话框
     *
     * @param context
     */
    public void showNetDialog(final Context context) {
        builder = new AlertDialog.Builder(context)
                .setTitle("世界上最遥远的距离就是没网")
                .setMessage("检查")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { //ToDo: 你想做的事情
                        Intent intent = null;
                        try {
                            @SuppressWarnings("deprecation")
                            String sdkVersion = android.os.Build.VERSION.SDK;
                            if (Integer.valueOf(sdkVersion) > 10) {
                                intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                            } else {
                                intent = new Intent();
                                ComponentName comp = new  ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
                                intent.setComponent(comp);
                                intent.setAction("android.intent.action.VIEW");
                            }
                            context.startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        dialogInterface.dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { //ToDo: 你想做的事情

                        dialogInterface.dismiss();
                    }
                });
        builder.create().show();
    }
}
