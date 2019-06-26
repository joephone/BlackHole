package com.transcendence.blackhole.base.config;

import android.app.Application;

import com.hjq.image.ImageLoader;
import com.hjq.toast.ToastUtils;
import com.hjq.toast.style.ToastBlackStyle;

/**
 * @author Joephone on 2019/6/25 10:10
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public enum AppConfig {

    //对象
    INSTANCE;

    public void initConfig(Application application){

        // 初始化吐司工具类
        ToastUtils.init(application, new ToastBlackStyle());

        // 初始化图片加载器
        ImageLoader.init(application);
    }
}
