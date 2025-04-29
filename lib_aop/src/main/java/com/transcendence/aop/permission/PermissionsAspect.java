package com.transcendence.aop.permission;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.hjq.permissions.XXPermissions;
import com.transcendence.core.base.manager.ActivityManager;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

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
    @Pointcut("execution(@com.transcendence.aop.permission.Permissions * *(..))")
    public void method() {}

    /**
     * 在连接点进行方法替换
     */
    @Around("method() && @annotation(permissions)")
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint, Permissions permissions) {
        Activity activity = null;

        // 方法参数值集合
        Object[] parameterValues = joinPoint.getArgs();
        for (Object arg : parameterValues) {
            if (!(arg instanceof Activity)) {
                continue;
            }
            activity = (Activity) arg;
            break;
        }

        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            activity = ActivityManager.getInstance().getTopActivity();
        }

        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            Log.e("wan","The activity has been destroyed and permission requests cannot be made");
            return;
        }

        requestPermissions(joinPoint, activity, permissions.value());
    }

    private void requestPermissions(ProceedingJoinPoint joinPoint, Activity activity, String[] permissions) {
        Log.i("wan","requestPermissions 拦截");
        if(activity==null){
            Log.i("wan","activity==null");
        }else {
            Log.i("wan","activity!=null");
        }
        XXPermissions.with(activity)
                .permission(permissions)
                .request(new PermissionCallback() {

                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        if (all) {
                            try {
                                // 获得权限，执行原方法
                                joinPoint.proceed();
                            } catch (Throwable e) {
//                                CrashReport.postCatchedException(e);
                            }
                        }
                    }
                });
    }
}