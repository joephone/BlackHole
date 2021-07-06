package com.transcendence.blackhole.demo.permission.simple.permissionutils;


import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class SimplePermissionUtils {

    private static final String TAG = SimplePermissionUtils.class.getSimpleName();

    private SimplePermissionFragment fragment;

    public SimplePermissionUtils(@NonNull FragmentActivity activity) {
        fragment = getRxPermissionsFragment(activity);
    }

    private SimplePermissionFragment getRxPermissionsFragment(FragmentActivity activity) {
        SimplePermissionFragment fragment = (SimplePermissionFragment) activity.getSupportFragmentManager().findFragmentByTag(TAG);
        boolean isNewInstance = fragment == null;
        if (isNewInstance) {
            fragment = new SimplePermissionFragment();
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(fragment, TAG)
                    .commit();
            fragmentManager.executePendingTransactions();
        }

        return fragment;
    }

    /**
     * 外部使用 申请权限
     * @param permissions 申请授权的权限
     * @param listener 授权回调的监听
     */
    public void requestPermissions(String[] permissions, SimplePermissionListener listener) {
        fragment.setListener(listener);
        fragment.requestPermissions(permissions);

    }

}
