package com.transcendence.blackhole.demo.LoginInterceptor.act;

/**
 * @Author Joephone on 2020/3/16 1:24
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public interface Interceptor {

    //并传入一个拦截器的拦截方法,做一些拦截判断操作
    void intercept(Invocation invocation);

}
