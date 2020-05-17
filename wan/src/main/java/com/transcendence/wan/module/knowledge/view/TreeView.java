package com.transcendence.wan.module.knowledge.view;

import com.transcendence.wan.core.mvp.view.WanBaseView;
import com.transcendence.wan.module.knowledge.model.TreeBean;

import java.util.List;

/**
 * @Author Joephone on 2020/5/9 5:20
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public interface TreeView extends WanBaseView {

    void getTreeSuc(int code, List<TreeBean> data);

    void getTreeFail(int code);
}
