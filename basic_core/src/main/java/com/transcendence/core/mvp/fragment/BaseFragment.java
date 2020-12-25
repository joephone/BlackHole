package com.transcendence.core.mvp.fragment;

import android.app.Dialog;

import com.transcendence.core.mvp.presenter.BasePresenter;

/**
 * @author Joephone on 2019/11/26 17:03
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public abstract class BaseFragment<P extends BasePresenter> extends MvpFragment<P> {

    private Dialog mDialog;

    @Override
    public void showLoadingDialog() {
//        if (mDialog == null) {
//            mDialog = LoadingDialog.with(getContext());
//        }
//        mDialog.show();
    }

    @Override
    public void dismissLoadingDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    @Override
    public void clearLoading() {
//        if (mLoadingBarManager != null) {
//            mLoadingBarManager.clear();
//            mLoadingBarManager.detach();
//        }
//        mLoadingBarManager = null;
//        if (mLoadingDialog != null) {
//            mLoadingDialog.clear();
//        }
//        mLoadingDialog = null;
    }

    public void finish() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }
}
