package com.transcendence.heima.presenter;


import com.transcendence.heima.base.BasePresenter;
import com.transcendence.heima.bean.LoginBean;
import com.transcendence.heima.model.LoginModel;
import com.transcendence.heima.mvp.IModel;
import com.transcendence.heima.ui.LoginActivity;
import com.transcendence.heima.contract.LoginContract;

import java.util.HashMap;

import okhttp3.ResponseBody;

/**
 * 登录的Presenter, 交互中间人, 处理View的业务逻辑, 它是沟通View和Model的桥梁
 *
 * @author gc
 * @since 1.0
 */
public class LoginPresenter extends BasePresenter<LoginActivity> implements
        LoginContract.LoginPresenter {

    @Override
    public void login(String name, String pwd) {
        if (!getIView().checkNull()) {
            ((LoginModel) getiModelMap().get("login")).login(name, pwd, new LoginModel
                    .DataListener<LoginBean>() {
                @Override
                public void successInfo(LoginBean result) {
                    getIView().loginSuccess(result);  //成功
                }

                @Override
                public void failInfo(String result) {
                    getIView().loginFail(result);  //失败
                }
            });
        }
    }

    @Override
    public void aisFetch(String ais) {
        ((LoginModel) getiModelMap().get("ais")).aisFetch(ais, new LoginModel
                .DataListener<ResponseBody>() {
            @Override
            public void successInfo(ResponseBody result) {
//                getIView().loginSuccess(result);  //成功
            }

            @Override
            public void failInfo(String result) {
                getIView().loginFail(result);  //失败
            }
        });
    }

    @Override
    public HashMap<String, IModel> getiModelMap() {
        return loadModelMap(new LoginModel());
    }

    @Override
    public HashMap<String, IModel> loadModelMap(IModel... models) {
        HashMap<String, IModel> map = new HashMap<>();
        map.put("login", models[0]);
        return map;
    }


}
