package com.transcendence.wan.module.home.view;

import com.transcendence.wan.core.mvp.view.WanBaseView;
import com.transcendence.wan.module.home.model.BannerBean;
import com.transcendence.wan.module.main.bean.ArticleListBean;

import java.util.List;

/**
 * @author Joephone on 2019/12/10 11:35
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public interface HomeView extends WanBaseView {
    void getBannerSuccess(int code, List<BannerBean> data);
    void getBannerFail(int code, String msg);

    void getArticleListHomeSuccess(int code, List<ArticleListBean.DataBean.DatasBean> data);
    void getArticleListHomeFailed(int code, String msg);
}
