package com.transcendence.wan.core.mvp.view;

import android.content.Context;

/**
 * @Author Joephone on 2020/4/24 0:17
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public interface WanMvpView {
    Context getContext();
    void showLoadingDialog();
    void dismissLoadingDialog();
    void clearLoading();
}
