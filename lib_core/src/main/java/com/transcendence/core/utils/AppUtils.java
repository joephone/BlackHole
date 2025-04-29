package com.transcendence.core.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;

import com.transcendence.core.base.app.CoreApp;
import com.transcendence.core.utils.log.LogUtils;

import java.util.Random;

/**
 * @author Joephone on 2019/5/28 10:44
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class AppUtils {

//    static AppUtils instance;
//
//    public static AppUtils getInstance() {
//        if (null == instance) {
//            instance = new AppUtils();
//        }
//        return instance;
//    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static int getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取 APP 包名
     * @return APP 包名
     */
    public static String getPackageName() {
        try {
            return CoreApp.getInstance().getPackageName();
        } catch (Exception e) {
            LogUtils.e("getPackageName"+e);
        }
        return null;
    }

    /**
     * 获取 APP 图标
     * @return {@link Drawable}
     */
    public static Drawable getAppIcon() {
        return getAppIcon(getPackageName());
    }

    /**
     * 获取 APP 图标
     * @param packageName 应用包名
     * @return {@link Drawable}
     */
    public static Drawable getAppIcon(final String packageName) {
        if (TextUtils.isEmpty(packageName)) return null;
        PackageManager packageManager = getPackageManager();
        if (packageManager == null) return null;
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    packageName, 0
            );
            if (packageInfo == null) return null;
            return packageInfo.applicationInfo.loadIcon(packageManager);
        } catch (Exception e) {
            LogUtils.e("getAppIcon"+e);
            return null;
        }
    }

    /**
     * 获取 APP 应用名
     * @return APP 应用名
     */
    public static String getAppName() {
        return getAppName(getPackageName());
    }

    /**
     * 获取 APP 应用名
     * @param packageName 应用包名
     * @return APP 应用名
     */
    public static String getAppName(final String packageName) {
        if (TextUtils.isEmpty(packageName)) return null;
        PackageManager packageManager = getPackageManager();
        if (packageManager == null) return null;
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    packageName, 0
            );
            if (packageInfo == null) return null;
            return packageInfo.applicationInfo.loadLabel(packageManager).toString();
        } catch (Exception e) {
            LogUtils.e("getAppName"+e);
            return null;
        }
    }

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

    /**
     * 获取 APP Signature
     * @return {@link Signature} 数组
     */
    public static Signature[] getAppSignature() {
        return getAppSignature(getPackageName());
    }

    /**
     * 获取 APP Signature
     * @param packageName 应用包名
     * @return {@link Signature} 数组
     */
    public static Signature[] getAppSignature(final String packageName) {
        if (TextUtils.isEmpty(packageName)) return null;
        try {
            PackageInfo packageInfo = getPackageInfo(
                    packageName, PackageManager.GET_SIGNATURES
            );
            return packageInfo == null ? null : packageInfo.signatures;
        } catch (Exception e) {
            LogUtils.e( "getAppSignature"+e);
            return null;
        }
    }

    /**
     * 获取 PackageInfo
     * @param flags package flags
     * @return {@link ApplicationInfo}
     */
    public static PackageInfo getPackageInfo(final int flags) {
        return getPackageInfo(getPackageName(), flags);
    }

    /**
     * 获取 PackageInfo
     * @param packageName 应用包名
     * @param flags       package flags
     * @return {@link ApplicationInfo}
     */
    public static PackageInfo getPackageInfo(
            final String packageName,
            final int flags
    ) {
        try {
            return CoreApp.getInstance().getPackageManager()
                    .getPackageInfo(packageName, flags);
        } catch (Exception e) {
//            LogUtils.e( "getPackageInfo %s", packageName);
        }
        return null;
    }

    /**
     * 获取 ApplicationInfo
     * @return {@link ApplicationInfo}
     */
    public static ApplicationInfo getApplicationInfo() {
        try {
            return CoreApp.getInstance().getApplicationInfo();
        } catch (Exception e) {
            LogUtils.e("getApplicationInfo"+e);
        }
        return null;
    }

    /**
     * 获取 ApplicationInfo
     * @param packageName 应用包名
     * @param flags       application flags
     * @return {@link ApplicationInfo}
     */
    public static ApplicationInfo getApplicationInfo(
            final String packageName,
            final int flags
    ) {
        try {
            return CoreApp.getInstance().getPackageManager()
                    .getApplicationInfo(packageName, flags);
        } catch (Exception e) {
//            LogUtils.e( e, "getApplicationInfo %s", packageName);
        }
        return null;
    }

    /**
     * 如果软键盘已显示则隐藏软键盘
     *
     * @param activity
     */
    public static void hideInputMethod(Activity activity) {
        if (null == activity) {
            return;
        }
        if (null != activity.getCurrentFocus() && null != activity.getCurrentFocus().getWindowToken()) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    /**
     *  追踪事件用时
     */
    public void trace(String tvName){
        long begin = System.currentTimeMillis();
        //模拟网络请求耗时 0-2秒
        SystemClock.sleep(new Random().nextInt(2000));
        long duration = System.currentTimeMillis() - begin;
        LogUtils.d(tvName + "-检查性能耗时:"+duration);
    }


    /**
     * 从当前上下文获取Activity
     */
//    @Nullable
    public Activity getActivity(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            Context baseContext = ((ContextWrapper) context).getBaseContext();
            if (baseContext instanceof Activity) {
                return (Activity) baseContext;
            }
        }
        return null;
    }


}
