package com.transcendence.wan.module.mine.view;

import com.transcendence.wan.core.mvp.view.WanBaseView;
import com.transcendence.wan.module.mine.model.RankListBean;

import java.util.List;

/**
 * @Author Joephone on 2020/4/24 22:18
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public interface RankView extends WanBaseView {

    void getRankListSuc(int code, List<RankListBean.DatasBean> data);

    void getRankListFail(int code);
}
