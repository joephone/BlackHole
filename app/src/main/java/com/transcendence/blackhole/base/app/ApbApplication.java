package com.transcendence.blackhole.base.app;

import com.antfortune.freeline.FreelineCore;
import com.transcendence.blackhole.demo.LoginInterceptor.act.Interceptors;

/**
 * @author Joephone on 2019/5/6 11:49
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 */

public class ApbApplication extends ApApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        FreelineCore.init(this);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }
}
