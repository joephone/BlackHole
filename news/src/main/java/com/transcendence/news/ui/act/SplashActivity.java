package com.transcendence.news.ui.act;

import android.content.Intent;

import com.transcendence.news.R;
import com.transcendence.news.base.act.NewsBaseActivity;
import com.transcendence.news.base.act.NewsBasePresenter;
import com.transcendence.news.base.act.NewsMainActivity;
import com.transcendence.news.ui.uikit.statusbar.Eyes;
import com.transcendence.news.utils.UIUtils;

/**
 * @author ChayChan
 * @description: 闪屏页
 * @date 2017/7/16  17:17
 */

public class SplashActivity extends NewsBaseActivity{


    @Override
    protected NewsBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_news_splash;
    }

    @Override
    public boolean enableSlideClose() {
        return false;
    }

    @Override
    public void initView() {
        Eyes.translucentStatusBar(this,false);//
        UIUtils.postTaskDelay(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,NewsMainActivity.class));
                finish();
            }
        },2000);
    }
}
