package com.transcendence.wan.core.mvp;

import android.app.Dialog;

import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * @author Joephone on 2019/11/26 17:03
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public abstract class WanBaseFragment<P extends WanBasePresenter> extends WanMvpFragment<P> {

    private Dialog mDialog;

    /**
     * 是否注册事件分发，默认不绑定
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override
    protected void initialize() {
        if (isRegisterEventBus()) {
            EventBus.getDefault().register(this);
        }
        super.initialize();
    }

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
