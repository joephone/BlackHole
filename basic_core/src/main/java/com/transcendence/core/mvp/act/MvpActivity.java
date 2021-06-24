package com.transcendence.core.mvp.act;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.transcendence.core.mvp.presenter.BasePresenter;
import com.transcendence.core.mvp.view.MvpView;

import per.goweii.swipeback.SwipeBackActivity;

/**
 * Created by Administrator on 2020/3/2.
 */

public abstract class MvpActivity<T extends BasePresenter> extends SwipeBackActivity implements MvpView {


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
     * 绑定数据
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
