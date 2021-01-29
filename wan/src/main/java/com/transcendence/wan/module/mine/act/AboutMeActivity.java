package com.transcendence.wan.module.mine.act;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseActivity;
import com.transcendence.wan.core.mvp.WanTitleBarActivity;
import com.transcendence.wan.module.mine.presenter.AboutMePresenter;
import com.transcendence.wan.module.mine.view.AboutMeView;

/**
 * @Author Joephone on 2020/4/25 15:28
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class AboutMeActivity extends WanTitleBarActivity<AboutMePresenter> implements AboutMeView {

    public static void start(Context context) {
        Intent intent = new Intent(context, AboutMeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_about_me;
    }

    @Nullable
    @Override
    protected AboutMePresenter initPresenter() {
        return new AboutMePresenter();
    }

    @Override
    protected void initView() {
        setTitle("欣赏完请他抽支华子呗");
    }

    @Override
    protected void loadData() {

    }
}
