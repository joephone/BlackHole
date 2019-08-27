package com.transcendence.blackhole.demo.mvp.model;

import com.transcendence.blackhole.demo.mvp.bean.Beauty;

import java.util.List;

/**
 * @author Joephone on 2019/7/9 16:22
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public interface IBeautyListModel {
    /**
     * 从数据源获得数据
     */
    void onLoadBeautyList(IBeautyListListener iBeautyListListener);
    /**
     * 设计一个内部回调接口
     */
    interface IBeautyListListener {
        /**
         *  获取数据成功
         * @param beautyList
         */
       void onComplete(List<Beauty> beautyList);
        /**
         * 获取数据失败
         */
       void onFail();
    }

}
