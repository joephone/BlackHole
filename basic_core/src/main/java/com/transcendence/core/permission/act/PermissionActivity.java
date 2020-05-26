package com.transcendence.core.permission.act;


/**
 * @author Joephone on 2020/5/25 20:52
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class PermissionActivity  {

//    /**
//     * TODO 权限回调接口
//     */
//    public interface PermissionCallBack {
//        void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                        @NonNull int[] grantResults);
//    }
//
//    private List<PermissionCallBack> permissionCallBacks = null;
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
//    //系统权限申请回调，这里调用我定义的回调
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (permissionCallBacks == null) return;
//        for (PermissionCallBack callBack : permissionCallBacks) {
//            callBack.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        }
//
//    }
}
