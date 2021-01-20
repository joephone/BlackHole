package com.transcendence.blackhole.base.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import com.transcendence.blackhole.arouter.ARouterUtils;
import com.transcendence.blackhole.base.config.AppInit;
import com.transcendence.blackhole.utils.L;

/**
 * @author Joephone on 2019/5/6 11:42
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc 全局
 */
public class LibApplication extends Application {

    //以下属性应用于整个应用程序，合理利用资源，减少资源浪费
    private static Context applicationContext;   //上下文
    private static LibApplication instance;
    private static Thread mMainThread;//主线程
    private static long mMainThreadId;//主线程id
    private static Looper mMainLooper;//循环队列
    private static Handler mHandler;//主线程Handler


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        //对全局属性赋值
        instance = this;
        applicationContext = getApplicationContext();
        AppInit.INSTANCE.initConfig(this);
    }



    public static LibApplication getInstance() {
        if (instance == null) {
            instance = new LibApplication();
        }
        return instance;
    }


    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        L.d("LibApplication onTerminate");
        super.onTerminate();
        ARouterUtils.destroy();
    }


    /**
     * 重启当前应用
     */
    public static void reStart() {
        Intent intent = applicationContext.getPackageManager().getLaunchIntentForPackage(applicationContext.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        applicationContext.startActivity(intent);
    }


    public static Context getAppContext() {
        return applicationContext;
    }

    public static void setContext(Context mContext) {
        LibApplication.applicationContext = mContext;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static void setMainThread(Thread mMainThread) {
        LibApplication.mMainThread = mMainThread;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public static void setMainThreadId(long mMainThreadId) {
        LibApplication.mMainThreadId = mMainThreadId;
    }

    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    public static void setMainThreadLooper(Looper mMainLooper) {
        LibApplication.mMainLooper = mMainLooper;
    }

    public static Handler getMainHandler() {
        return mHandler;
    }

    public static void setMainHandler(Handler mHandler) {
        LibApplication.mHandler = mHandler;
    }

}
