package com.transcendence.wan.module.wxpublic.view;

import com.transcendence.wan.core.mvp.view.WanBaseView;
import com.transcendence.wan.module.wxpublic.model.WxChapterBean;

import java.util.List;

/**
 * @Author Joephone on 2020/4/26 14:54
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public interface NaviWxPublicView extends WanBaseView {
    void getWxChapterSuc(int code, List<WxChapterBean> list);

    void getWxChapterFail(int code);
}
