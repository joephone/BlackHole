package com.transcendence.wan.module.wxpublic.model;

import com.transcendence.wan.base.bean.WanBaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Joephone on 2020/4/18 16:02
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class WxChapterModel extends WanBaseBean implements Serializable {

    private List<WxChapterBean> data;

    public List<WxChapterBean> getData() {
        return data;
    }

    public void setData(List<WxChapterBean> data) {
        this.data = data;
    }


}
