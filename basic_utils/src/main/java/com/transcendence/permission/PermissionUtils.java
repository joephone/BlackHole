package com.transcendence.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class PermissionUtils {
    private OnPermissionListener listener;

    public void onPermissionRequest(Activity activity,int permissionCode,String... permissionName){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//            L.logI("22以下");
//            onPermissionsGranted(permissionCode);
            return ;
        }

        for (int i = 0; i < permissionName.length; i++) {
            if(ContextCompat.checkSelfPermission(activity, permissionName[i])== PackageManager.PERMISSION_GRANTED){
                //有权限
                if(listener!=null){
                    listener.onPermissionsGranted(permissionCode);
                }
            }else{
                //没有权限,开始申请
                ActivityCompat.requestPermissions(activity,new String[]{permissionName[i]},permissionCode);
            }
        }
    }

    public void setListener(OnPermissionListener listener) {
        this.listener = listener;
    }

    public interface OnPermissionListener{
        /**
         * 有授权执行的方法(子类重写)
         */
        void onPermissionsGranted(int requestCode);

        /**
         * 没有授权执行的方法(子类重写)
         */
        void onPermissionsDenied(int requestCode);
    }


}
