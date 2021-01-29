package com.transcendence.core.base.config;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hjq.image.ImageLoader;
import com.hjq.toast.ToastUtils;
import com.hjq.toast.style.ToastBlackStyle;

/**
 * @author Joephone on 2019/6/25 10:10
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc 链式调度 中转  给用户的接口
 * @Edition 1.0
 * @EditionHistory
 */
public enum AppInit {

    //对象
    INSTANCE;

    public void initConfig(Application application){

        // 初始化吐司工具类
        ToastUtils.init(application, new ToastBlackStyle());

        // 初始化图片加载器
        ImageLoader.init(application);

        //
        initARouter(application);

    }

    private void initARouter(Application application) {

        //打印日志
        ARouter.openLog();
        //开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.openDebug();

        ARouter.init(application);
    }





}
