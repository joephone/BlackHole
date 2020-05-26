package com.transcendence.core.permission;


import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * @author Joephone on 2020/5/25 16:37
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class CheckPermissionUtils {



    /**
     * 通用检查权限方法
     * @param context
     * @param permissions 权限数组
     * @param requestPermissionCode 请求权限时的activity 请求码
     * @param permissionName 权限中文名称
     * @param callback  权限回调
     */
    public static void checkPermission(final Activity context, final String[] permissions, final int requestPermissionCode, final String permissionName, final PermissionRequestCallback callback) {
        if (hasPermission(context,permissions)) {
            callback.hasPermission();
        } else {

            //没有权限，则先给Activity增加一个我们自定义的权限结果回调
//            context.addRequestPermissionsResult(new BaseActivity.PermissionCallBack() {
//                @Override
//                public void onRequestPermissionsResult(int requestCode, @NonNull String[]
//                        permissions, @NonNull int[] grantResults) {
//
//                    if (requestCode == requestPermissionCode && grantResults != null&&grantResults.length>0) {
//                        if (checkGrantResult(grantResults,permissions.length)) {
//
//                            callback.hasPermission();
//                        } else {
//
//                            callback.noPermission();
//
//                        }
//                    }
//                }
//            });

            if (shouldShowRequestPermissionRationale(context, permissions)) {//给用户一个解释
                // Logs.base.d("应该展示一个UI提示：ShowRequestPermissionRationale %s权限",permissionName);
//                Dialogs.showMustConfirmDialog(context, "权限申请", String.format("%s需要获得%s权限才能正常使用此功能,请允许！", appLabel, permissionName), new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        ActivityCompat.requestPermissions(context, permissions, requestPermissionCode);
//                    }
//                });

            }else{
                //请求权限
                ActivityCompat.requestPermissions(context, permissions, requestPermissionCode);
            }
        }
    }
    //是不是要给一个用户一个解释
    public static boolean shouldShowRequestPermissionRationale(Activity context, String[] permissions) {
        for (int i = 0; i < permissions.length; i++) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(context,permissions[i])){
                return true;
            }
        }
        return false;
    }

    public static boolean hasPermission(Activity context,String[] permissions) {
        for (int i = 0; i < permissions.length; i++) {
            if (ActivityCompat.checkSelfPermission(context, permissions[i])
                    != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }
    //检查授权结果
    public static boolean checkGrantResult(int[] grantResults,int permissionCount) {
        for (int i = 0; i < permissionCount; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public interface PermissionRequestCallback {
        void hasPermission();
        void noPermission();
    }


//    /**
//     * TODO 权限回调接口
//     */
//    public interface PermissionCallBack {
//        void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                        @NonNull int[] grantResults);
//    }
//
//    private List<PermissionCallBack> permissionCallBacks = null;
//
//    /**
//     * TODO 添加Activity权限申请回掉
//     */
//    public void addRequestPermissionsResult(PermissionCallBack callback) {
//        if (permissionCallBacks == null) {
//            permissionCallBacks = new ArrayList<>();
//        }
//
//        if (permissionCallBacks.size() >= 1) {
//            permissionCallBacks.clear();
//        }
//        permissionCallBacks.add(callback);
//    }
//
//    //系统权限申请回调，这里调用我定义的回调
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (permissionCallBacks == null) return;
//        for (PermissionCallBack callBack : permissionCallBacks) {
//            callBack.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        }
//    }


}
