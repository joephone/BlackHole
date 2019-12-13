package com.transcendence.wan.mvp;

import android.content.Context;

/**
 * @author Joephone on 2019/12/10 14:40
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public abstract class MvpPresenter<V extends MvpView>{

    protected Context context;
    private V baseView;

    public void attach(V baseView) {
        this.baseView = baseView;
        context = baseView.getContext();
    }

    public void detach() {
        baseView = null;
        context = null;
    }

}
