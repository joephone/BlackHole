package com.transcendence.core.permission;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtils {

    private final int mRequestCode = 100;//权限请求码
    public static boolean showSystemSetting = false;
    private PermissionUtils() {
    }

    private static PermissionUtils permissionsUtils;
    private IPermissionsResult mListener;

    public static PermissionUtils getInstance() {
        if (permissionsUtils == null) {
            permissionsUtils = new PermissionUtils();
        }
        return permissionsUtils;
    }





    public void checkPermissions(Activity activity,String [] permissions,IPermissionsResult listener){
        this.mListener = listener;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//            L.logI("22以下");
            listener.onGranted();
            return ;
        }



        //创建一个mPermissionList，逐个判断哪些权限未授予，未授予的权限存储到mPerrrmissionList中
        List<String> mPermissionList = new ArrayList<>();
        //逐个判断你要的权限是否已经通过
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(activity, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);//添加还未授予的权限
            }
        }

        //申请权限
        if (mPermissionList.size() > 0) {//有权限没有通过，需要申请
            ActivityCompat.requestPermissions(activity, permissions, mRequestCode);
        } else {
            //说明权限都已经通过，可以做你想做的事情去
            listener.onGranted();
            return;
        }

    }


    //请求权限后回调的方法
    //参数： requestCode  是我们自己定义的权限请求码
    //参数： permissions  是我们请求的权限名称数组
    //参数： grantResults 是我们在弹出页面后是否允许权限的标识数组，数组的长度对应的是权限名称数组的长度，数组的数据0表示允许权限，-1表示我们点击了禁止权限
    public void onRequestPermissionsResult(Activity context, int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        boolean hasPermissionDismiss = false;//有权限没有通过
        if (mRequestCode == requestCode) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == -1) {
                    hasPermissionDismiss = true;
                }
            }
            //如果有权限没有被允许
            if (hasPermissionDismiss) {
                if (showSystemSetting) {
                    showSystemPermissionsSettingDialog(context);//跳转到系统设置权限页面，或者直接关闭页面，不让他继续访问
                } else {
                    mListener.onDenied();
                }
            } else {
                //全部权限通过，可以进行下一步操作。。。
                mListener.onGranted();
            }
        }

    }


    /**
     * 不再提示权限时的展示对话框
     * String.format("%s需要获得%s权限才能正常使用此功能,请允许！", appLabel, permissionName);
     */
    AlertDialog mPermissionDialog;

    private void showSystemPermissionsSettingDialog(final Activity context) {
        final String mPackName = context.getPackageName();
        if (mPermissionDialog == null) {
            mPermissionDialog = new AlertDialog.Builder(context)
                    .setMessage("已禁用权限，请手动授予")
                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cancelPermissionDialog();

                            Uri packageURI = Uri.parse("package:" + mPackName);
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                            context.startActivity(intent);
                            context.finish();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //关闭页面或者做其他操作
                            cancelPermissionDialog();
                            //mContext.finish();
                            mListener.onDenied();
                        }
                    })
                    .create();
        }
        mPermissionDialog.show();
    }

    //关闭对话框
    private void cancelPermissionDialog() {
        if (mPermissionDialog != null) {
            mPermissionDialog.cancel();
            mPermissionDialog = null;
        }

    }

//    //检查授权结果
//    public static boolean checkGrantResult(int[] grantResults,int permissionCount) {
//        for (int i = 0; i < permissionCount; i++) {
//            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public static boolean hasPermission(Activity context,String[] permissions) {
//        for (int i = 0; i < permissions.length; i++) {
//            if (ActivityCompat.checkSelfPermission(context, permissions[i])
//                    != PackageManager.PERMISSION_GRANTED){
//                return false;
//            }
//        }
//        return true;
//    }

    public interface IPermissionsResult{
        void onGranted();

        void onDenied();
    }





}
