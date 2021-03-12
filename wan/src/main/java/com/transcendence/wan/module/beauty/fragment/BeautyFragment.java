package com.transcendence.wan.module.beauty.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseFragment;
import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;

/**
 * @Author Joephone on 2021/3/5 0005 下午 1:53
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class BeautyFragment extends WanBaseFragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";

    public static BeautyFragment newInstance(String title) {
        BeautyFragment fragment = new BeautyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, title);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_navi_beauty;
    }

    @Nullable
    @Override
    protected WanBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }
}
