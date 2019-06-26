package com.transcendence.blackhole.ui.mvp.presenter;

import android.text.TextUtils;

import com.transcendence.blackhole.base.mvp.BaseView;
import com.transcendence.blackhole.ui.mvp.bean.User;

/**
 * @author Joephone on 2019/6/25 16:07
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class LoginPresenterImpl implements LoginPresenter.Presenter{

    private LoginPresenter.View logView;

    @Override
    public void attachView(BaseView v) {
        //绑定的时候把view置进来
        this.logView = (LoginPresenter.View) v;
    }

    @Override
    public void detachView() {
        //解绑时置空即可
        this.logView = null;
    }

    @Override
    public void login(User user) {
        if(!TextUtils.isEmpty(user.getName()) && !TextUtils.isEmpty(user.getPwd())){
            if("111".equals(user.getName()) && "222".equals(user.getPwd())){
                logView.loginSuccess("登录成功");
            }else {
                logView.loginFailed("登录失败");
            }
        }else {
            logView.showToast("用户名或密码不能为空");
        }

    }





}
