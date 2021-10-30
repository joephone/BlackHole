package com.transcendence.heima.model;


import androidx.annotation.NonNull;

import com.transcendence.heima.base.BaseModel;
import com.transcendence.heima.base.ProApplication;
import com.transcendence.heima.bean.LoginBean;
import com.transcendence.heima.exception.ApiException;
import com.transcendence.heima.observer.CommonObserver;
import com.transcendence.heima.transformer.CommonTransformer;

import okhttp3.ResponseBody;

/**
 * 登录的Model, 主要做一些数据处理, 网路请求
 *
 * @author gc
 * @since 1.0
 */
public class LoginModel extends BaseModel {
    private boolean isLogin = false;

    public boolean login(@NonNull String username, @NonNull String pwd, @NonNull final DataListener
            listener) {
        if (listener == null) {
            throw new RuntimeException("InfoHint不能为空");
        }
        httpService.login(username, pwd)
                .compose(new CommonTransformer<LoginBean>())
                .subscribe(new CommonObserver<LoginBean>(ProApplication.getContext()) {

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull LoginBean loginBean) {
                        isLogin = true;
                        listener.successInfo(loginBean);
                    }

                    @Override
                    protected void onError(ApiException e) {
                        super.onError(e);
                        isLogin = false;
                        listener.failInfo(e.message);
                    }
                });
        return isLogin;
    }


    public void aisFetch(@NonNull String aisStr,@NonNull final DataListener listener){
        httpService.aisFetch(aisStr)
                .compose(new CommonTransformer<ResponseBody>())
                .subscribe(new CommonObserver<ResponseBody>(ProApplication.getContext()) {

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull ResponseBody loginBean) {
                        listener.successInfo(loginBean);
                    }

                    @Override
                    protected void onError(ApiException e) {
                        super.onError(e);
                        listener.failInfo(e.message);
                    }
                });
    }

    /**
     * 通过接口产生信息回调
     *
     * @param <T>
     */
    public interface DataListener<T> {
        void successInfo(T result);
        void failInfo(String result);
    }
}
