package com.transcendence.heima.observer;

import android.content.Context;

import com.transcendence.core.global.Global;
import com.transcendence.heima.base.BaseObserver;
import com.transcendence.heima.exception.ApiException;
import com.transcendence.heima.utils.LogUtils;
import com.transcendence.heima.utils.NetworkUtil;

import io.reactivex.disposables.Disposable;

/**
 * 封装Observer
 *
 * @author gc
 * @since 1.0
 */
public abstract class CommonObserver<T> extends BaseObserver<T> {
    private static final String TAG = Global.TAG;
    private Context context;
    // Disposable 相当于RxJava1.x中的 Subscription，用于解除订阅 disposable.dispose();
    protected Disposable disposable;

    public CommonObserver(Context context) {
        this.context = context;
    }

    @Override
    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
        disposable = d;
        if (!NetworkUtil.isNetworkAvailable(context)) {
            LogUtils.e(TAG, "网络不可用");
        } else {
            LogUtils.e(TAG, "网络可用");
        }
    }

    @Override
    protected void onError(ApiException e) {
        LogUtils.e(TAG, "HTTP错误 --> " + "code:" + e.code + ", message:" + e.message);
    }

    @Override
    public void onComplete() {
        LogUtils.e(TAG, "成功了");
    }

}
