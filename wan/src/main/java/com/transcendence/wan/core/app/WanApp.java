package com.transcendence.wan.core.app;

import androidx.core.content.ContextCompat;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.squareup.leakcanary.LeakCanary;
import com.transcendence.core.base.app.LibApplication;
import com.transcendence.config.ProjectInit;
import com.transcendence.global.API;
import com.transcendence.network.jett.retrofit.RetrofitCreator;
import com.transcendence.ui.recyclerview.hjq.layout.SmartBallPulseFooter;
import com.transcendence.wan.R;

/**
 * @author Joephone on 2019/12/9 16:00
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class WanApp extends LibApplication {

    private static PersistentCookieJar mCookieJar = null;

    @Override
    public void onCreate() {
        super.onCreate();

        ProjectInit.init(this)
                .withApiHost(API.API_WAN_ANDROID)
                .configurator();
        //登录后会在cookie中返回账号密码，只要在客户端做cookie持久化存储即可自动登录验证。
        RetrofitCreator.setCookieJar(getCookieJar());

        setupLeakCanary();

        // 设置全局的 Header 构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) ->
                new MaterialHeader(context).setColorSchemeColors(ContextCompat.getColor(context, R.color.srflone)));
        // 设置全局的 Footer 构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> new SmartBallPulseFooter(context));
        // 设置全局初始化器
        SmartRefreshLayout.setDefaultRefreshInitializer((context, layout) -> {
            // 刷新头部是否跟随内容偏移
            layout.setEnableHeaderTranslationContent(true)
                    // 刷新尾部是否跟随内容偏移
                    .setEnableFooterTranslationContent(true)
                    // 加载更多是否跟随内容偏移
                    .setEnableFooterFollowWhenNoMoreData(true)
                    // 内容不满一页时是否可以上拉加载更多
                    .setEnableLoadMoreWhenContentNotFull(false)
                    // 仿苹果越界效果开关
                    .setEnableOverScrollDrag(false);
        });

    }

    protected void setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }



    public static PersistentCookieJar getCookieJar() {
        if (mCookieJar == null) {
            mCookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getAppContext()));
        }
        return mCookieJar;
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }



}
