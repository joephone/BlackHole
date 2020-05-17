package com.transcendence.wan.module.mine.act;

import android.support.annotation.Nullable;

import com.transcendence.wan.R;
import com.transcendence.wan.base.act.WanBaseActivity;
import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;

public class BounceActivity extends WanBaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Nullable
    @Override
    protected WanBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }
}
