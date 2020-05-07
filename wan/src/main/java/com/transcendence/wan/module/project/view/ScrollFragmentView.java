package com.transcendence.wan.module.project.view;

import com.transcendence.wan.core.mvp.view.WanBaseView;
import com.transcendence.wan.module.mine.model.MineBean;

import java.util.List;

/**
 * @Author Joephone on 2020/5/6 23:01
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public interface ScrollFragmentView extends WanBaseView {

    void inflateItemSuc(List<MineBean> list);

}
