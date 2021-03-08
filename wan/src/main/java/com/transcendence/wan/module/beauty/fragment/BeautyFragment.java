package com.transcendence.wan.module.beauty.fragment;

import android.support.annotation.Nullable;

import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseFragment;
import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;
import com.transcendence.wan.module.dama.presenter.DamaPresenter;

/**
 * @Author Joephone on 2021/3/5 0005 下午 1:53
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class BeautyFragment extends WanBaseFragment {


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
