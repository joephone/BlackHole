package com.transcendence.blackhole.demo.LoginInterceptor.act;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Joephone on 2020/3/16 1:37
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc 每一个应用的拦截器数组
 * @Edition 1.0
 * @EditionHistory
 */

final public class Interceptors {

    private final List<Interceptor> globalActionInterceptor = new ArrayList<>();
    //添加一个初始化全局拦截器（例如定位，登录等，可以初始化全局)
    public Interceptors add(Interceptor globalInterceptor) {
        if (globalInterceptor != null)
            this.globalActionInterceptor.add(globalInterceptor);
        return this;
    }
    //获取当前应用所有的拦截器
    public Interceptor[] getInterceptorArray() {
        Interceptor[] result = globalActionInterceptor.toArray(new Interceptor[globalActionInterceptor.size()]);
        return result == null ? new Interceptor[0] : result;
    }
}
