package com.transcendence.wan.module.knowledge.model;

import com.transcendence.wan.core.bean.WanBaseBean;

import java.util.List;

/**
 * @Author Joephone on 2020/5/9 5:13
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class TreeModel extends WanBaseBean {


    private List<TreeBean> data;

    public List<TreeBean> getData() {
        return data;
    }

    public void setData(List<TreeBean> data) {
        this.data = data;
    }


}
