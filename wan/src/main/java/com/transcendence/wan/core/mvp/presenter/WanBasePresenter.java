package com.transcendence.wan.core.mvp.presenter;

import com.transcendence.wan.core.mvp.view.BaseView;
import com.transcendence.wan.mvp.MvpPresenter;

import io.reactivex.disposables.Disposable;
import per.goweii.rxhttp.core.RxLife;

/**
 * 描述：
 *
 * @author Cuizhen
 * @date 2019/3/29
 */

public class WanBasePresenter<V extends BaseView> extends MvpPresenter<V> {

    private RxLife rxLife;

    @Override
    public void attach(V baseView) {
        super.attach(baseView);
        rxLife = RxLife.create();
    }

    @Override
    public void detach() {
        super.detach();
        rxLife.destroy();
        rxLife = null;
    }

    public RxLife getRxLife() {
        return rxLife;
    }

    public void addToRxLife(Disposable disposable) {
        if (rxLife != null) {
            rxLife.add(disposable);
        }
    }
}
