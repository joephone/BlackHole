package com.transcendence.news.ui.fragment;

import android.view.View;

import com.socks.library.KLog;
import com.transcendence.news.R;
import com.transcendence.news.base.act.NewsBaseFragment;
import com.transcendence.news.base.act.NewsBasePresenter;

/**
 * @author ChayChan
 * @description: 我的fragment
 * @date 2017/6/12  21:47
 */

public class MineFragment extends NewsBaseFragment {
    @Override
    protected NewsBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView(View rootView) {
        KLog.i("initView");
    }

    @Override
    public void initData() {
        KLog.i("initData");
    }

    @Override
    public void initListener() {
        KLog.i("initListener");
    }

    @Override
    public void loadData() {
        KLog.i("loadData");
    }
}
