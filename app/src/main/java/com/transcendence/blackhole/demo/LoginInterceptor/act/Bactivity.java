package com.transcendence.blackhole.demo.LoginInterceptor.act;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.transcendence.blackhole.base.app.ApApplication;

/**
 * @Author Joephone on 2020/3/16 1:39
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class Bactivity extends AppCompatActivity implements IVew {
    ApApplication application;
    //子类实现禁止onCreate方法
    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (ApApplication) getApplication();
        onInitView(savedInstanceState);
    }

    //禁止子类的onResume方法 并配置拦截器
    @Override
    protected final void onResume() {
        super.onResume();
        Interceptor[] globalInters = InterceptorBuilder.NULL_INTERS;
        Interceptors interceptors = application.getInterceptors();//获取拦截器集合
        if (interceptors != null) {
            globalInters = interceptors.getInterceptorArray();
        }
        Interceptor[] finalInters = InterceptorBuilder.build(globalInters, getClass());


        new Invocation(this, finalInters).invoke();
    }

    //子类获取对象
    @Override
    public Activity getViewActivity() {
        return this;
    }
    //设置主题
    @Override
    public void setWindowTheme() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    @Override
    public void onInitView(Bundle savedInstanceState) {

    }
    @Override
    public void resume() {

    }


}
