package com.transcendence.wan.module.dama.view;

import com.transcendence.wan.core.mvp.view.WanBaseView;
import com.transcendence.wan.module.dama.model.DamaBean;

import java.util.List;

/**
 * @author CuiZhen
 * @date 2019/10/3
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public interface DamaView extends WanBaseView {

    void getUserArticleListSuccess(int code, List<DamaBean.DataBean.DatasBean> data);

    void getUserArticleListFailed(int code, String msg);
}
