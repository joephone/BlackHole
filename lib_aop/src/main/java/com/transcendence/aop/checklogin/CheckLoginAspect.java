package com.transcendence.aop.checklogin;


import android.util.Log;

import com.transcendence.core.base.route.RoutePath;
import com.transcendence.core.base.route.RouteUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class CheckLoginAspect {

    @Pointcut("execution(" +//执行语句
            "@com.transcendence.aop.checklogin.CheckLogin" +//注解筛选
            " * " + //类路径,*为任意路径
            "*" +   //方法名,*为任意方法名
            "(..)" +//方法参数,'..'为任意个任意类型参数
            ")" +
            " && " +//并集
            "@annotation(checkLogin)"//注解筛选,这里主要用于下面方法的'NeedLogin'参数获取
    )
    public void pointcutCheckLogin(CheckLogin checkLogin) {

    }

    @Around("pointcutCheckLogin(checkLogin)")
    public Object aroundCheckLogin(ProceedingJoinPoint joinPoint, final CheckLogin checkLogin) throws Throwable {
        Log.i("wan", "登录校验");
//        if (!MMkvHelper.getInstance().isLogin()) {
//            RouteUtils.navigationActivity(RoutePath.User.LOGIN);
//            Log.i("TAG", "未登录");
//            return null;
//        } else {
//            return joinPoint.proceed();
//        }
        return joinPoint.proceed();
    }
}
