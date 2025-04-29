package com.transcendence.core.permission;

import android.Manifest;
import android.app.Activity;

import androidx.annotation.NonNull;

import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;
import com.transcendence.core.utils.log.LogUtils;

import java.util.List;

/**
 * @author joephone
 * @date 2023/6/10
 * @desc
 */
public class PermissionHelper {

    private Activity mActivity;

    public PermissionHelper(Activity hostActivity) {
        this.mActivity = hostActivity;
    }

    public interface PermissionCallback {
        void onPermissionComplete(Boolean isGranted);
    }

    public void requestAllPermissions(String[] permissions, final PermissionCallback permissionCallback){
        XXPermissions.with(mActivity)
                // 申请单个权限
                // 申请多个权限
                .permission(permissions)
                // 设置权限请求拦截器（局部设置）
                //.interceptor(new PermissionInterceptor())
                // 设置不触发错误检测机制（局部设置）
                //.unchecked()
                .request(new OnPermissionCallback() {

                    @Override
                    public void onGranted(@NonNull List<String> permissions, boolean allGranted) {
                        LogUtils.d("onGranted");
                        permissionCallback.onPermissionComplete(allGranted);
                    }

                    @Override
                    public void onDenied(@NonNull List<String> permissions, boolean doNotAskAgain) {
                        LogUtils.d("onDenied doNotAskAgain--"+doNotAskAgain);
                        if (doNotAskAgain) {
//                                // 如果是被永久拒绝就跳转到应用权限系统设置页面
                            XXPermissions.startPermissionActivity(mActivity, permissions);
                        } else {
                            permissionCallback.onPermissionComplete(false);
                        }
                    }
                });
    }


    /**
     * 申请播放视频权限
     *  Manifest.permission.WRITE_EXTERNAL_STORAGE,
     *                     Manifest.permission.READ_EXTERNAL_STORAGE,
     *
     * @param permissionCallback 权限处理完毕的回调
     */
    public void requestStoragePermissions(final PermissionCallback permissionCallback) {
        String[] permissions = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
        };
        requestAllPermissions(permissions,permissionCallback);
    }

    /**
     * 申请相机权限
     *
     * @param permissionCallback 权限处理完毕的回调
     */
    public void requestCameraPermissions(final PermissionCallback permissionCallback) {
        String[] permissions = new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
        };
        requestAllPermissions(permissions,permissionCallback);
    }

    /**
     * 申请打电话权限
     *
     * @param permissionCallback 权限处理完毕的回调
     */
    public void requestDialCallPermissions(final PermissionCallback permissionCallback) {
        String[] permissions = new String[]{
                Manifest.permission.CALL_PHONE,
        };
        requestAllPermissions(permissions,permissionCallback);
    }

    /**
     * 申请打电话权限
     *
     * @param permissionCallback 权限处理完毕的回调
     */
    public void requestLocationPermissions(final PermissionCallback permissionCallback) {
        requestAllPermissions(PermissionPool.GROUP.LOCATION,permissionCallback);
    }



}
