package com.transcendence.wan.module.dama.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.transcendence.blackhole.utils.L;
import com.transcendence.ui.recyclerview.adapter.BaseAbsAdapter;
import com.transcendence.ui.recyclerview.view.RefreshLayout;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseFragment;
import com.transcendence.wan.module.dama.adapter.DamaAdapter;
import com.transcendence.wan.module.dama.model.DamaBean;
import com.transcendence.wan.module.dama.presenter.DamaPresenter;
import com.transcendence.wan.module.dama.view.DamaView;
import com.transcendence.wan.module.mine.act.ToDoActivity;

import java.util.List;

/**
 * @author Joephone on 2019/11/27 11:50
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class DamaFragment extends WanBaseFragment<DamaPresenter> implements DamaView,RefreshLayout.RefreshCallback, View.OnClickListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";
    private static int PAGE = 0;

//    private View emptyView;
    private RefreshLayout mRefreshLayout;
    private BaseAbsAdapter mAdapter;
    private boolean isLoadMore;

    private FrameLayout flRight;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_navi_dama;
    }


    @Override
    protected DamaPresenter initPresenter() {
        return new DamaPresenter();
    }


    @Override
    protected void initView() {
//        L.d("DamaFragment onCreateView");
        mRefreshLayout = findViewById(R.id.refreshView);
        mAdapter = new DamaAdapter(getContext());
        mRefreshLayout.setAdapter(mAdapter,getContext());
        mRefreshLayout.schemeColors();
        mRefreshLayout.addCallback(this);

        flRight = findViewById(R.id.fl_right);
        flRight.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
//        L.d("dama loadData");
        onViewRefresh();
    }

    public static DamaFragment newInstance(String title) {
        DamaFragment fragment = new DamaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void getUserArticleListSuccess(int code, List<DamaBean.DataBean.DatasBean> data) {
        if(isLoadMore){
            mRefreshLayout.onLoadMore(data);
        }else {
            mRefreshLayout.onRefresh(data);
        }
    }

    @Override
    public void getUserArticleListFailed(int code, String msg) {
        mRefreshLayout.stopSrfl();
    }

    @Override
    public void onViewRefresh() {
        L.d("onViewRefresh");
        PAGE = 0;
        isLoadMore = false;
        presenter.getArticleList(PAGE);
    }

    @Override
    public void onViewLoadMore() {
        L.d("onViewLoadMore");
        PAGE ++;
        isLoadMore = true;
        presenter.getArticleList(PAGE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fl_right:
                ToDoActivity.start(getContext());
                break;
        }
    }
}
