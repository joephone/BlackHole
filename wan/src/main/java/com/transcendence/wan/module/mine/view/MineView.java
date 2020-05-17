package com.transcendence.wan.module.mine.view;

import com.transcendence.wan.core.mvp.view.WanBaseView;
import com.transcendence.wan.module.mine.model.MyCoinBean;

/**
 * @Author Joephone on 2020/4/24 17:17
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public interface MineView extends WanBaseView{

    void getMyCoinSuc(int code, MyCoinBean bean);


}
