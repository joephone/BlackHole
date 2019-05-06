package com.transcendence.blackhole.base.app;

import android.app.Application;
import android.content.Context;

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

    }

    public static LibApplication getInstance() {
        if (instance == null) {
            instance = new LibApplication();
        }
        return instance;
    }
}
