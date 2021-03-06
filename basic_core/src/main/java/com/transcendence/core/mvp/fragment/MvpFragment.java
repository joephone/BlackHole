package com.transcendence.core.mvp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.transcendence.core.mvp.presenter.BasePresenter;
import com.transcendence.core.mvp.view.BaseView;

/**
 * @author Joephone on 2019/12/10 16:20
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public abstract class MvpFragment<T extends BasePresenter> extends LazyFragment implements BaseView, View.OnClickListener {

    protected T presenter;

    /**
     * 获取布局资源文件
     *
     * @return int
     */
    @Override
    @LayoutRes
    protected abstract int getLayoutRes();

    /**
     * 初始化presenter
     *
     * @return P
     */
    @Nullable
    protected abstract T initPresenter();

    /**
     * 绑定事件
     */
    protected abstract void initView();

    /**
     * 加载数据
     */
    protected abstract void loadData();

    /**
     * 点击事件，可连续点击
     */
    protected boolean onClick1(final View v){
        return false;
    }

    /**
     * 点击事件，500毫秒第一次
     */
    protected void onClick2(final View v){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        attachPresenter();
        initialize();
    }

    @Override
    protected void onVisible(boolean isFirstVisible) {
        super.onVisible(isFirstVisible);
        attachPresenter();
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
    public void onDestroyView() {
        if (presenter != null) {
            presenter.detach();
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected void initialize() {
        initView();
        loadData();
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    public Fragment getFragment() {
        return this;
    }





}
