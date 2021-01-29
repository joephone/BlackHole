package com.transcendence.wan.module.home.model;

import com.transcendence.wan.core.bean.WanBaseBean;

import java.util.List;

/**
 * @Author Joephone on 2020/5/6 15:57
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class BannerModel extends WanBaseBean {

    private List<BannerBean> data;

    public List<BannerBean> getData() {
        return data;
    }

    public void setData(List<BannerBean> data) {
        this.data = data;
    }
}
