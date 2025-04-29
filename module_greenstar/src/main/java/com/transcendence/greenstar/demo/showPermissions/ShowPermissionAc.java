package com.transcendence.greenstar.demo.showPermissions;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.core.utils.log.LogUtils;
import com.transcendence.greenstar.R;
import com.transcendence.greenstar.demo.showPermissions.networkmanager.NetworkUtils;

import java.util.List;

/**
 * @author joephone
 * @date 2023/5/29
 * @desc
 */
public class ShowPermissionAc extends AppAc {

    TextView mTvGetNotification,mTvNetWork;
    boolean netWork;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo_showpermission_show;
    }

    @Override
    protected void initView() {
        applyPermission();

        netWork = NetworkUtils.isNetworkAvailable(ShowPermissionAc.this);
        if(netWork){
            mTvNetWork.setText("当前网络可用");
            mTvNetWork.setOnClickListener(v -> {
                NetworkUtils.setNetworkMethod(ShowPermissionAc.this);
            });
        } else {
            mTvNetWork.setText("当前网络不可用，申请网络");
            mTvNetWork.setOnClickListener(v -> {
                NetworkUtils.setNetworkMethod(ShowPermissionAc.this);
            });
        }
    }

    //申请权限
    private void applyPermission(){
        mTvGetNotification = findViewById(R.id.tv_getNotification);
        mTvNetWork = findViewById(R.id.tv_network);
        findViewById(R.id.tv_checkPermission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 点击检查 相机、打电话 权限
                 */
                PermissionUtil permissionUtil = new PermissionUtil(ShowPermissionAc.this);
                permissionUtil.requestPermissions(new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA,
                                Manifest.permission.RECORD_AUDIO,
                                Manifest.permission.BLUETOOTH,

                        },
                        new PermissionListener() {
                            @Override
                            public void onGranted() {
                                //所有权限都已经授权
                                showMsg("所有权限都已授权");
                            }

                            @Override
                            public void onDenied(List<String> denieds) {
                                //Toast第一个被拒绝的权限
//                                showMsg("拒绝了权限" + denieds.get(0));
                                for (int i = 0; i < denieds.size(); i++) {
                                    LogUtils.d("拒绝了" + denieds.get(i)+"权限");
                                }
                            }

                            @Override
                            public void onShouldShowRationale(List<String> denieds) {
                                //Toast第一个勾选不在提示的权限
//                                showMsg("这个权限" + deniedPermission.get(0)+"勾选了不在提示，要像用户解释为什么需要这权限");
                                for (int i = 0; i < denieds.size(); i++) {
                                    LogUtils.d(denieds.get(i)+"勾选了不在提示");
                                }
                            }
                        });



            }
        });


        findViewById(R.id.tv_checkNotification).setOnClickListener(v -> {
            boolean flagNotification = NotificationsUtils.isNotificationEnabled(ShowPermissionAc.this);
            LogUtils.d("是否有通知权能"+flagNotification);
            if(flagNotification){
                mTvGetNotification.setVisibility(View.VISIBLE);
            } else {
                mTvGetNotification.setVisibility(View.INVISIBLE);
            }

        });

        mTvGetNotification.setOnClickListener(v ->{
            getNotification();
        });
    }



    private void getNotification(){
        if (NotificationsUtils.isNotificationEnabled(this)){
            AlertDialog.Builder builder = new AlertDialog.Builder(ShowPermissionAc.this)
                    .setCancelable(true)
                    .setTitle("检测到通知权限未开启!")
                    .setMessage("如果不开启权限会收不到推送通知哦~")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setPositiveButton("去开启", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            Intent intent = new Intent();
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                                intent.putExtra("android.provider.extra.APP_PACKAGE", ShowPermissionAc.this.getPackageName());
                            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //5.0
                                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                                intent.putExtra("app_package", ShowPermissionAc.this.getPackageName());
                                intent.putExtra("app_uid", ShowPermissionAc.this.getApplicationInfo().uid);
                                startActivity(intent);
                            } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) { //4.4
                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.addCategory(Intent.CATEGORY_DEFAULT);
                                intent.setData(Uri.parse("package:" + ShowPermissionAc.this.getPackageName()));
                            } else if (Build.VERSION.SDK_INT >= 15) {
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                intent.setData(Uri.fromParts("package", ShowPermissionAc.this.getPackageName(), null));
                            }
                            startActivity(intent);
                        }
                    });
            builder.create().show();
        }
    }


}
