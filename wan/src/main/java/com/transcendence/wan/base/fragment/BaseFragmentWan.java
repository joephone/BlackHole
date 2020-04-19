package com.transcendence.wan.base.fragment;

import com.transcendence.wan.core.mvp.WanMvpFragment;
import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;
import com.transcendence.wan.ui.dialog.LoadingBarManager;
import com.transcendence.wan.ui.dialog.LoadingDialog;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Cuizhen
 * @version v1.0.0
 * @date 2018/3/10-下午12:38
 */
public abstract class BaseFragmentWan<P extends WanBasePresenter> extends WanMvpFragment<P> {

    private LoadingDialog mLoadingDialog = null;
    private LoadingBarManager mLoadingBarManager = null;
    private Unbinder mUnbinder = null;

    /**
     * 是否注册事件分发，默认不绑定
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override
    protected void initialize() {
        if (getRootView() != null) {
            mUnbinder = ButterKnife.bind(this, getRootView());
        }
        if (isRegisterEventBus()) {
            EventBus.getDefault().register(this);
        }
        super.initialize();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (isRegisterEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }




    public void finish() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }
}
