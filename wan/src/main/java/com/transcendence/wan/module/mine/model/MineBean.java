package com.transcendence.wan.module.mine.model;

import com.transcendence.wan.base.bean.WanBaseBean;

/**
 * @Author Joephone on 2020/5/6 22:23
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class MineBean extends WanBaseBean {

    private int ivLeft;
    private String tvLeft;
    private String tvRight;

    public int getIvLeft() {
        return ivLeft;
    }

    public void setIvLeft(int ivLeft) {
        this.ivLeft = ivLeft;
    }

    public String getTvLeft() {
        return tvLeft;
    }

    public void setTvLeft(String tvLeft) {
        this.tvLeft = tvLeft;
    }

    public String getTvRight() {
        return tvRight;
    }

    public void setTvRight(String tvRight) {
        this.tvRight = tvRight;
    }
}
