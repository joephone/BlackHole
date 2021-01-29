package com.transcendence.wan.module.setting.view;

import com.transcendence.wan.core.bean.NewWanBaseBean;
import com.transcendence.wan.core.mvp.view.WanBaseView;
import com.transcendence.wan.core.mvp.view.WanTitleBarView;

/**
 * @Author Joephone on 2020/4/28 14:31
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public interface SettingView extends WanTitleBarView {
    void logoutSuccess(int code, NewWanBaseBean data);
    void logoutFailed(int code, String msg);

    void getCacheSizeSuccess(String size);
}
