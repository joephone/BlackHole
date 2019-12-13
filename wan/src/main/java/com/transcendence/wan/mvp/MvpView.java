package com.transcendence.wan.mvp;

import android.content.Context;

/**
 * @author Joephone on 2019/12/10 11:20
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public interface MvpView {

    Context getContext();
    void showLoadingDialog();
    void dismissLoadingDialog();
    void showLoadingBar();
    void dismissLoadingBar();
    void clearLoading();

}
