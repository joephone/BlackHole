package com.transcendence.wan.core.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;
import com.transcendence.wan.core.mvp.view.WanMvpView;

import per.goweii.swipeback.SwipeBackActivity;

/**
 * Created by Administrator on 2020/3/2.
 */

public abstract class WanMvpActivity<T extends WanBasePresenter> extends SwipeBackActivity implements WanMvpView {


    protected T presenter;

    /**
     * 获取布局资源文件
     */
    protected abstract int getLayoutId();

    /**
     * 初始化presenter
     */
    @Nullable
    protected abstract T initPresenter();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 加载数据
     */
    protected abstract void loadData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initWindow();
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
        }

        attachPresenter();
        initialize();
    }

    protected void initialize() {
        initView();
        loadData();
    }


    private void attachPresenter() {
        if (presenter == null) {
            presenter = initPresenter();
        }
        if (presenter != null) {
            presenter.attach(this);
        }
    }


    @Override
    public void onDestroy() {
        if (presenter != null) {
            presenter.detach();
        }
        super.onDestroy();
    }
}
