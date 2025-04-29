package com.transcendence.freeland.main.app;

import android.app.ActivityManager;

import com.transcendence.core.base.app.CoreApp;
import com.transcendence.core.utils.log.LogUtils;
import com.transcendence.freeland.crash.CrashHandler;

/**
 * @author joephone
 * @date 2023/1/20
 * @desc
 */
public class MainApp extends CoreApp {

    @Override
    public void onCreate() {
        super.onCreate();

//        CrashHandler.register(this);

        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        int memoryClass = activityManager.getMemoryClass();
        LogUtils.d("获取当前应用的内存限制（以MB为单位）： Memory Class: " + memoryClass + " MB");

        int largeMemoryClass = activityManager.getLargeMemoryClass();
        LogUtils.d( "对于大内存应用，可以使用getLargeMemoryClass()方法来获取更大的内存限制： Large Memory Class: " + largeMemoryClass + " MB");
    }
}
