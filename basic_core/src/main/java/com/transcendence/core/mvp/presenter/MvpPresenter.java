package com.transcendence.core.mvp.presenter;

import android.content.Context;

import com.transcendence.core.mvp.view.MvpView;

/**
 * @Author Joephone on 2020/4/24 0:21
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public abstract class MvpPresenter<V extends MvpView> {

    protected Context context;
    private V wanBaseView;



    public void attach(V baseView) {
        this.wanBaseView = baseView;
        context = baseView.getContext();
    }

    public boolean isAttach() {
        return wanBaseView != null;
    }

    public void detach() {
        wanBaseView = null;
        context = null;
    }

    public V getWanBaseView() {
        return wanBaseView;
    }

    public void showLoadingDialog() {
        if (wanBaseView != null) {
            wanBaseView.showLoadingDialog();
        }
    }

    public void dismissLoadingDialog() {
        if (wanBaseView != null) {
            wanBaseView.dismissLoadingDialog();
        }
    }


}
