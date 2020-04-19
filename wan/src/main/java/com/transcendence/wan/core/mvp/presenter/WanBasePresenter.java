package com.transcendence.wan.core.mvp.presenter;

import android.content.Context;

import com.transcendence.wan.core.mvp.WanBaseView;

/**
 * 描述：
 *
 * @author Cuizhen
 * @date 2019/3/29
 */

public class WanBasePresenter<V extends WanBaseView> {

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

}
