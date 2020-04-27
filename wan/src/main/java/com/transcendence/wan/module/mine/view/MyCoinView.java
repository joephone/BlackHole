package com.transcendence.wan.module.mine.view;

import com.transcendence.wan.core.mvp.view.WanBaseView;
import com.transcendence.wan.module.mine.model.MyCoinBean;
import com.transcendence.wan.module.mine.model.MyCoinListBean;

import java.util.List;

/**
 * @Author Joephone on 2020/4/25 14:42
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public interface MyCoinView extends WanBaseView {

    void getMyCoinSuc(int code, MyCoinBean bean);

    void getMyCoinFail(int code);

    void getMyCoinListSuc(int code, List<MyCoinListBean> list);

    void getMyCoinListFail(int code);
}
