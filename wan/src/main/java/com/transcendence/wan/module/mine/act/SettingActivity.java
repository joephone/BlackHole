package com.transcendence.wan.module.mine.act;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.transcendence.wan.R;
import com.transcendence.wan.base.act.WanBaseActivity;
import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;

/**
 * @Author Joephone on 2020/3/14 17:11
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class SettingActivity extends WanBaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_wan_setting;
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


    public static void start(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void clearLoading() {

    }
}
