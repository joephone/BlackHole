package com.transcendence.wan.module.login.model;

import com.transcendence.wan.base.bean.WanBaseBean;

/**
 * @author CuiZhen
 * @date 2019/5/8
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class LoginModel extends WanBaseBean {

    /**
     * data : {"admin":false,"chapterTops":[],"collectIds":[],"email":"","icon":"","id":60527,"nickname":"15171484007","password":"","publicName":"15171484007","token":"","type":0,"username":"15171484007"}
     */

    private LoginBean data;

    public LoginBean getData() {
        return data;
    }

    public void setData(LoginBean data) {
        this.data = data;
    }


}
