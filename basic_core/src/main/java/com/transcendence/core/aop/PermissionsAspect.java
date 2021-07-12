package com.transcendence.core.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.transcendence.core.base.app.LibApplication;
import com.transcendence.core.permission.PermissionCallback;
import com.transcendence.permissions.Permissions;

import java.util.List;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/12/06
 *    desc   : 权限申请切面
 */
@Aspect
public class PermissionsAspect {

    /**
     * 方法切入点
     */
    @Pointcut("execution(@com.transcendence.core.aop.PermissionAop * *(..))")
    public void method() {}

    /**
     * 在连接点进行方法替换
     */
    @Before("method() && @annotation(permissions)")
    public void aroundJoinPoint(final ProceedingJoinPoint joinPoint, PermissionAop permissionAop) {
//        Activity activity = ActivityManager.getInstance().getTopActivity();
//        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
//            return;
//        }

        Permissions.with(LibApplication.getAppContext())
                .permission(permissionAop.value())
                .request(new PermissionCallback() {

                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        if (all) {
                            try {
                                // 获得权限，执行原方法
                                joinPoint.proceed();
                            } catch (Throwable e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }
}