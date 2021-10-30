package com.transcendence.heima.base;

import com.transcendence.heima.exception.ApiException;

import io.reactivex.Observer;

/**
 * 观察者基类
 *
 * @author gc
 * @since 1.0
 */
public abstract class BaseObserver<T> implements Observer<T> {
    @Override
    public void onError(Throwable e) {
        ApiException apiException = (ApiException) e;
        onError(apiException);
    }

    /**
     * @param e 错误的一个回调
     */
    protected abstract void onError(ApiException e);
}
