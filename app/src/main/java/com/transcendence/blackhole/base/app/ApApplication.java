package com.transcendence.blackhole.base.app;

import com.transcendence.blackhole.demo.LoginInterceptor.act.Interceptors;

/**
 * @author Joephone on 2019/5/6 11:49
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 */

public abstract class ApApplication extends LibApplication {


    private static Interceptors interceptors = new Interceptors();
    @Override
    public void onCreate() {
        super.onCreate();
        configInterceptor(interceptors);
    }

    public static final Interceptors getInterceptors() {
        return interceptors;
    }
    //子类实现的方法,添加自己的拦截器数组
    public abstract void configInterceptor(Interceptors interceptors);

}
