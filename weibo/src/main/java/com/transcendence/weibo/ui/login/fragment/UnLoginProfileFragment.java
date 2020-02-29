package com.transcendence.weibo.ui.login.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.transcendence.weibo.R;
import com.transcendence.weibo.base.fragment.BaseFragment;

/**
 * Created by wenmingvs on 15/12/26.
 */

public class UnLoginProfileFragment extends BaseFragment {
    private View mView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.unlogin_profilefragment_layout, container, false);
        return mView;
    }


}