package com.transcendence.wan.utils;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.transcendence.blackhole.utils.GsonUtils;
import com.transcendence.blackhole.utils.SPUtils;
import com.transcendence.wan.module.login.act.LoginActivity;
import com.transcendence.wan.module.login.model.LoginBean;

/**
 * @author CuiZhen
 * @date 2019/5/15
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */

public class UserUtils {

    private static final String KEY_LOGIN_BEAN = "KEY_LOGIN_BEAN";

    private LoginBean mLoginBean = null;

    private static class Holder {
        private static final UserUtils INSTANCE = new UserUtils();
    }

    public static UserUtils getInstance() {
        return Holder.INSTANCE;
    }

    public LoginBean getLoginBean() {
        if (mLoginBean == null) {
            String json = SPUtils.getInstance().get(KEY_LOGIN_BEAN, "");
            if (!TextUtils.isEmpty(json)) {
                try {
                    mLoginBean = new Gson().fromJson(json, LoginBean.class);
                } catch (Exception ignore) {

                }
            }
        }
        return mLoginBean;
    }



    public void login(LoginBean loginBean){
        mLoginBean = loginBean;
        String json = GsonUtils.cls2Json(loginBean); //GsonUtils.getInstance().cls2Json(LoginBean.class);
        SPUtils.getInstance().save(KEY_LOGIN_BEAN,json);
    }

    public void logout(){
        mLoginBean = null;
        SPUtils.getInstance().clear();
    }

    public boolean isLogin() {
        LoginBean loginBean = getLoginBean();
        if (loginBean == null) {
            return false;
        }
        if (loginBean.getId() > 0) {
            return true;
        }
        return false;
    }

    public int getUserId(){
        LoginBean loginBean = getLoginBean();
        if(loginBean ==null){
            return 0;
        }
        return loginBean.getId();
    }

    public boolean toDoIfLogin(Context context) {
        if (isLogin()) {
            return true;
        } else {
            LoginActivity.start(context);
            return false;
        }
    }
}
