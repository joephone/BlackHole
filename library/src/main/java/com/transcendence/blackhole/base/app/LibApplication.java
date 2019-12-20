package com.transcendence.blackhole.base.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.transcendence.blackhole.arouter.ARouterUtils;
import com.transcendence.blackhole.base.config.AppInit;
import com.transcendence.blackhole.base.config.ProjectInit;
import com.transcendence.blackhole.global.Global;
import com.transcendence.blackhole.utils.L;

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


        ProjectInit.init(this)
                .withApiHost(Global.BASE_URL.WAN_API_HOST)
                .configurator();

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

}
