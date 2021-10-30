package com.transcendence.heima.mvp;

public interface IPresenter<V extends IView> {
    /**
     * 绑定
     * @param view
     */
    void attachView(V view);
    /**
     * 防止内存的泄漏, 清除Presenter与Activity之间的绑定
     */
    void detachView();
    /**
     * @return 获取View
     */
    V getIView();
}
