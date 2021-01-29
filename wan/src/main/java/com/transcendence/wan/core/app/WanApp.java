package com.transcendence.wan.core.app;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.squareup.leakcanary.LeakCanary;
import com.transcendence.core.base.app.LibApplication;
import com.transcendence.config.ProjectInit;
import com.transcendence.global.API;
import com.transcendence.network.jett.retrofit.RetrofitCreator;

/**
 * @author Joephone on 2019/12/9 16:00
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class WanApp extends LibApplication {

    private static PersistentCookieJar mCookieJar = null;

    @Override
    public void onCreate() {
        super.onCreate();

        ProjectInit.init(this)
                .withApiHost(API.API_WAN_ANDROID)
                .configurator();
        //登录后会在cookie中返回账号密码，只要在客户端做cookie持久化存储即可自动登录验证。
        RetrofitCreator.setCookieJar(getCookieJar());

        setupLeakCanary();

    }

    protected void setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }



    public static PersistentCookieJar getCookieJar() {
        if (mCookieJar == null) {
            mCookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getAppContext()));
        }
        return mCookieJar;
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
