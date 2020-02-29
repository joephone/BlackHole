package com.transcendence.weibo.ui.login.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.transcendence.weibo.R;
import com.transcendence.weibo.base.fragment.BaseFragment;

/**
 * Created by wenmingvs on 15/12/26.
 */
public class UnLoginMessageFragment extends BaseFragment {
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.unlogin_messagefragment_layout, container, false);
        return mView;
    }


}
