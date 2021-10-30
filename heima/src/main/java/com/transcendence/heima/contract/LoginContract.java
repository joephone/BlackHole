package com.transcendence.heima.contract;


import com.transcendence.heima.bean.LoginBean;

/**
 * 契约类, 定义登录用到的一些接口方法
 *
 * @author gc
 * @since 1.0
 */
public class LoginContract {

    public interface LoginView {
        String getUserName();
        String getPwd();
        void loginSuccess(LoginBean loginBean); // 登录成功，展示数据
        void loginFail(String failMsg);
    }

    public interface LoginPresenter {
        void login(String name, String pwd); // 业务逻辑

        void aisFetch(String ais);
    }
}
