package com.transcendence.core.base.app;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mmkv.MMKV;
import com.transcendence.core.BuildConfig;
import com.transcendence.core.global.Global;

/**
 * @author joephone
 * @date 2023/1/23
 * @desc
 */
public class CoreApp extends Application {
    private static CoreApp instance;
    private static Context applicationContext;   //上下文

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        applicationContext = getApplicationContext();
        //mmkv初始化
        MMKV.initialize(this);
        //路由初始化
        initARouter();
    }

    private void initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
        // Bugly 异常捕捉
        CrashReport.initCrashReport(this, Global.BUGLY_ID, BuildConfig.DEBUG);
    }

    /**
     * 获得当前app运行的Application
     */
    public static CoreApp getInstance() {
        if (instance == null) {
            throw new NullPointerException(
                    "please inherit BaseApplication or call setApplication.");
        }
        return instance;
    }

    public static Context getAppContext() {
        return applicationContext;
    }
}
