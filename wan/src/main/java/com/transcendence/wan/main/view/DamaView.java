package com.transcendence.wan.main.view;

import com.transcendence.wan.mvp.WanBaseView;

/**
 * @author CuiZhen
 * @date 2019/10/3
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public interface DamaView extends WanBaseView {
//    void getUserArticleListSuccess(int code, ArticleListBean data);

    void getUserArticleListFailed(int code, String msg);
}
