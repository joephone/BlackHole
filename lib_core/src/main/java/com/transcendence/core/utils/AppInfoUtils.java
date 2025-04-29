package com.transcendence.core.utils;

import android.content.Context;
import android.content.pm.PackageManager;

import com.transcendence.core.base.app.CoreApp;
import com.transcendence.core.utils.log.LogUtils;

/**
 * @author joephone
 * @date 2023/4/27
 * @desc
 */
public class AppInfoUtils {

    /**
     * 获取 PackageManager
     * @return {@link PackageManager}
     */
    public static PackageManager getPackageManager() {
        return getPackageManager(CoreApp.getInstance());
    }

    /**
     * 获取 PackageManager
     * @param context Context
     * @return {@link PackageManager}
     */
    public static PackageManager getPackageManager(final Context context) {
        if (context == null) return null;
        try {
            return context.getPackageManager();
        } catch (Exception e) {
            LogUtils.e("getPackageManager"+e);
        }
        return null;
    }

}
