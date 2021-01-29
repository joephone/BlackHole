package com.transcendence.wan.module.mine.model;

import com.transcendence.wan.core.bean.WanBaseBean;

/**
 * @Author Joephone on 2020/4/25 15:00
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class MyCoinModel extends WanBaseBean {

    /**
     * data : {"coinCount":21,"level":1,"rank":8880,"userId":60527,"username":"1**71484007"}
     */

    private MyCoinBean data;

    public MyCoinBean getData() {
        return data;
    }

    public void setData(MyCoinBean data) {
        this.data = data;
    }


}
