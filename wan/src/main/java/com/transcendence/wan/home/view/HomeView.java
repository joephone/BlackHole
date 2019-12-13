package com.transcendence.wan.home.view;

import com.transcendence.wan.home.model.BannerBean;

import java.util.List;

/**
 * @author Joephone on 2019/12/10 11:35
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public interface HomeView {

    void getBannerSuccess(int code, List<BannerBean.DataBean> data);
    void getBannerFail(int code, String msg);

//    void getArticleListSuccess(int code, ArticleListBean data);
    void getArticleListFailed(int code, String msg);
}
