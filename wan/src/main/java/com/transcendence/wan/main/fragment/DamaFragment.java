package com.transcendence.wan.main.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.transcendence.wan.R;
import com.transcendence.wan.main.presenter.DamaPresenter;
import com.transcendence.wan.main.view.DamaView;
import com.transcendence.wan.ui.utils.MultiStateUtils;
import com.transcendence.wan.ui.utils.SimpleListener;
import com.transcendence.wan.ui.view.MultiStateView;

import butterknife.BindView;
import per.goweii.actionbarex.common.ActionBarCommon;

/**
 * @author Joephone on 2019/11/27 11:50
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class DamaFragment extends WanBaseFragment<DamaPresenter> implements DamaView {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";

    private static final int PAGE_START = 0;

//    @BindView(R.id.msv)
//    MultiStateView msv;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_dama;
    }

    @Nullable
    @Override
    protected DamaPresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Parameter 1.
     * @return A new instance of fragment .
     */
    public static DamaFragment newInstance(String title) {
        DamaFragment fragment = new DamaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void showLoadingBar() {

    }

    @Override
    public void dismissLoadingBar() {

    }

    @Override
    public void clearLoading() {

    }

    @Override
    public void getUserArticleListFailed(int code, String msg) {

    }
}
