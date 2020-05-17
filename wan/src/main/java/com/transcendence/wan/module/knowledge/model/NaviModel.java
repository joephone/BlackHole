package com.transcendence.wan.module.knowledge.model;

import com.transcendence.wan.base.bean.WanBaseBean;

import java.util.List;

/**
 * @Author Joephone on 2020/5/9 5:04
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class NaviModel extends WanBaseBean {

    private List<NaviBean> data;

    public List<NaviBean> getData() {
        return data;
    }

    public void setData(List<NaviBean> data) {
        this.data = data;
    }

}
