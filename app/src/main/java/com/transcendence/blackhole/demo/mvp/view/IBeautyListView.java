package com.transcendence.blackhole.demo.mvp.view;

import com.transcendence.core.base.mvp.LibBaseView;
import com.transcendence.blackhole.demo.mvp.bean.Beauty;

import java.util.List;

/**
 * @author Joephone on 2019/7/9 16:19
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public interface IBeautyListView extends LibBaseView {

    /**
     * 加载程度条
     */
    void onShowLoading();

    /**
     * 收起程度条
     */
    void onDismissLoading();

    /**
     * 显示列表数据 (使用回调的方式返回数据)
     */
    void onShowBeautyList(List<Beauty> beautyList);
}
