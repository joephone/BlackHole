package com.transcendence.wan.module.setting.view;

import com.transcendence.wan.base.bean.NewWanBaseBean;
import com.transcendence.wan.core.mvp.view.WanBaseView;

/**
 * @Author Joephone on 2020/4/28 14:31
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public interface SettingView extends WanBaseView {
    void logoutSuccess(int code, NewWanBaseBean data);
    void logoutFailed(int code, String msg);

    void getCacheSizeSuccess(String size);
}
