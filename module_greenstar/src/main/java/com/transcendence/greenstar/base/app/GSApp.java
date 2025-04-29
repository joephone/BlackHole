package com.transcendence.greenstar.base.app;

import android.annotation.SuppressLint;
import android.content.Context;

import com.transcendence.core.base.app.CoreApp;

/**
 * @author joephone
 * @date 2023/2/4
 * @desc
 */
public class GSApp extends CoreApp {


    @SuppressLint("StaticFieldLeak")
    public static Context context;

    //数据库
//    public static AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //初始化网络框架
//        NetworkApi.init(new NetworkRequiredInfo(this));
//        //创建本地数据库
//        db = AppDatabase.getInstance(this);
        //腾讯WebView初始化
        initX5WebView();
    }

    private void initX5WebView() {
//        HashMap map = new HashMap(2);
//        map.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, true);
//        map.put(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE, true);
//        QbSdk.initTbsSettings(map);
//        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
//        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
//            @Override
//            public void onViewInitFinished(boolean arg0) {
//                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
//                Log.d("app", " onViewInitFinished is " + arg0);
//            }
//
//            @Override
//            public void onCoreInitFinished() {
//            }
//        };
//        //x5内核初始化接口
//        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    public static Context getContext() {
        return context;
    }

//    public static AppDatabase getDb(){
//        return db;
//    }

    public static ActivityManager getActivityManager() {
        return ActivityManager.getInstance();
    }

}
