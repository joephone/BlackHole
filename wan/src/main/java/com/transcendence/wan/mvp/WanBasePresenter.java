package com.transcendence.wan.mvp;

import android.content.Context;

/**
 * 描述：
 *
 * @author Cuizhen
 * @date 2019/3/29
 */
public class WanBasePresenter<V extends WanBaseView> extends MvpPresenter<V> {


    @Override
    public void attach(V baseView) {
        super.attach(baseView);
    }

    @Override
    public void detach() {
        super.detach();
    }


}
