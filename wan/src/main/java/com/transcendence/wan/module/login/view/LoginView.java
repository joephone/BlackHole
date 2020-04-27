package com.transcendence.wan.module.login.view;

import com.transcendence.wan.core.mvp.view.WanBaseView;
import com.transcendence.wan.module.login.model.LoginBean;

/**
 * @author CuiZhen
 * @date 2019/5/15
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */

public interface LoginView extends WanBaseView {

    void loginSuccess(int code, LoginBean data);
    void loginFailed(int code, String msg);

}
