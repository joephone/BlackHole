package com.transcendence.wan.module.login.presenter;

import com.transcendence.blackhole.utils.GsonUtils;
import com.transcendence.blackhole.utils.L;
import com.transcendence.global.API;
import com.transcendence.network.jett.callback.IFailure;
import com.transcendence.network.jett.callback.ISuccess;
import com.transcendence.network.jett.retrofit.RetrofitClient;
import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;
import com.transcendence.wan.core.service.ParamUser;
import com.transcendence.wan.module.login.model.LoginModel;
import com.transcendence.wan.module.login.view.LoginView;
import com.transcendence.wan.utils.UserUtils;

import java.util.Map;

/**
 * @author CuiZhen
 * @date 2019/5/15
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */

public class LoginPresenter extends WanBasePresenter<LoginView> {

    public void login(String userName, String password){
        Map<String,Object> map = ParamUser.getInstance().login(userName, password);
        RetrofitClient.create()
                .url(API.WAN.LOGIN)
                .params(map)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        L.d("response"+response);
                        LoginModel loginModel = GsonUtils.json2Cls(response,LoginModel.class);
                        if(loginModel!=null && loginModel.getData()!=null){
                            UserUtils.getInstance().login(loginModel.getData());
                        }
                        if (isAttach()) {
                            getWanBaseView().loginSuccess(0, loginModel.getData());
                        }
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        L.d("onFailure");
                    }
                })
                .build()
                .post();

    }


}
