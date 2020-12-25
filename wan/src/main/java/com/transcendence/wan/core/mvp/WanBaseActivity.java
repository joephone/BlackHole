package com.transcendence.wan.core.mvp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.transcendence.blackhole.utils.StatusBarUtils;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;

/**
 * @author Joephone on 2019/9/5 16:16
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public abstract class WanBaseActivity<P extends WanBasePresenter> extends WanMvpActivity<P> {

    protected WanBaseActivity mActivity;
    private Dialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        StatusBarUtils.with(mActivity).init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    protected void startAct(Class<?> target) {
        Intent intent = new Intent(mActivity, target);
        startActivity(intent);
        overridePendingTransition(R.anim.wan_zoom_small_in, R.anim.wan_zoom_small_out);
    }


    @Override
    public Context getContext() {
        return this;
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


}
