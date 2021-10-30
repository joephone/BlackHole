package com.transcendence.heima.base;

import android.app.Application;
import android.content.Context;

import com.transcendence.core.base.app.LibApplication;

/**
 * 应用, 主要用来做一下初始化的操作
 *
 * @author gc
 * @since 1.0
 */
public class ProApplication extends LibApplication {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    /**
     * @return 全局的上下文
     */
    public static Context getContext() {
        return mContext;
    }
}
