package com.transcendence.wan.module.wxpublic.view;

import com.transcendence.wan.core.mvp.view.WanBaseView;
import com.transcendence.wan.module.wxpublic.model.WxChapterArticleBean;

import java.util.List;

/**
 * @Author Joephone on 2020/4/27 1:42
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public interface WxChapterArticleListView extends WanBaseView {

    void getWxChapterArticleListSuc(int code, List<WxChapterArticleBean> data);

    void getWxChapterArticleListFail();
}
