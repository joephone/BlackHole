package com.transcendence.wan.module.beauty.view;

import com.transcendence.wan.core.mvp.view.WanBaseView;
import com.transcendence.wan.module.beauty.model.BeautyBean;
import com.transcendence.wan.module.wxpublic.model.WxChapterBean;

import java.util.List;

/**
 * @Author Joephone on 2021/3/31 0031 下午 2:03
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public interface BeautyView extends WanBaseView {

    void getBeautyListSuc(int code, List<BeautyBean> list);

    void getBeautyListFail(int code);
}
