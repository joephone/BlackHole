package com.transcendence.blackhole.demo.LoginInterceptor.act;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Joephone on 2020/3/16 1:46
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

@Inherited //可继承
@Retention(RetentionPolicy.RUNTIME)//保留的时间
@Target({ElementType.TYPE, ElementType.METHOD})//作用范围，类，接口方法等
public @interface Before {
    Class<? extends Interceptor>[] value();
}

