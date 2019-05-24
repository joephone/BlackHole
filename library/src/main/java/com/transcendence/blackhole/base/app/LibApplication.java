package com.transcendence.blackhole.base.app;

import android.app.Application;
import android.content.Context;

import com.hjq.toast.ToastUtils;
import com.hjq.toast.style.ToastBlackStyle;

/**
 * @author Joephone on 2019/5/6 11:42
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc 全局
 */

public class LibApplication extends Application {

    private static Context applicationContext;
    private static LibApplication instance;

    public static Context getAppContext() {
        return applicationContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        applicationContext = getApplicationContext();


        // 初始化吐司工具类
        ToastUtils.init(this, new ToastBlackStyle());
    }

    public static LibApplication getInstance() {
        if (instance == null) {
            instance = new LibApplication();
        }
        return instance;
    }
}
