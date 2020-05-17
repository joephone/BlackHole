package com.transcendence.wan.module.knowledge.view;

import com.transcendence.wan.core.mvp.view.WanBaseView;
import com.transcendence.wan.module.knowledge.model.NaviBean;

import java.util.List;

/**
 * @Author Joephone on 2020/5/9 4:55
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public interface KnowledgeTwoView extends WanBaseView {

    void getNaviSuc(int code, List<NaviBean> data);

    void getNaviFail(int code);
}
