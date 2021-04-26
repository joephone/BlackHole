package com.transcendence.wan.module.dama.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.transcendence.core.global.Global;
import com.transcendence.core.utils.L;
import com.transcendence.ui.recyclerview.adapter.BaseAbsAdapter;
import com.transcendence.ui.recyclerview.hjq.BaseAdapter;
import com.transcendence.ui.recyclerview.hjq.layout.WrapRecyclerView;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseFragment;
import com.transcendence.wan.module.main.adapter.ArticleListAdapter;
import com.transcendence.wan.module.main.bean.ArticleListBean;
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

public class DamaFragment extends WanBaseFragment<DamaPresenter> implements DamaView,
                                                                            OnRefreshLoadMoreListener,
                                                                            BaseAdapter.OnItemClickListener,
                                                                            View.OnClickListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";
    private static int PAGE = 0;

//    private View emptyView;
    private SmartRefreshLayout mRefreshLayout;
    private WrapRecyclerView mRecyclerView;
    private ArticleListAdapter mAdapter;
    private boolean isLoadMore;
    Handler mHandler = new Handler();

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
        flRight = findViewById(R.id.fl_right);
        flRight.setOnClickListener(this);

        initRv();
    }


    private void initRv() {
        mRefreshLayout = findViewById(R.id.rl_refresh);
        mAdapter = new ArticleListAdapter(getContext());
        mRecyclerView = findViewById(R.id.rv_dama);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshLoadMoreListener(this);

    }

    @Override
    protected void loadData() {
//        L.d("dama loadData");
        presenter.getArticleList(PAGE);
    }

    public static DamaFragment newInstance(String title) {
        DamaFragment fragment = new DamaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void getUserArticleListSuccess(int code, List<ArticleListBean.DataBean.DatasBean> data) {
        if(isLoadMore){
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAdapter.addData(data);
                    mRefreshLayout.finishLoadMore();
                    mAdapter.setLastPage(data.size()< Global.LIMIT);
                    mRefreshLayout.setNoMoreData(mAdapter.isLastPage());
                }
            }, 1000);
        }else {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAdapter.clearData();
                    mAdapter.addData(data);
                    mRefreshLayout.finishRefresh();
                }
            }, 1000);

        }
    }

    @Override
    public void getUserArticleListFailed(int code, String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fl_right:
                ToDoActivity.start(getContext());
                break;
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        PAGE ++;
        isLoadMore = true;
        presenter.getArticleList(PAGE);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        PAGE = 0;
        isLoadMore = false;
        presenter.getArticleList(PAGE);
    }

    @Override
    public void onItemClick(RecyclerView recyclerView, View itemView, int position) {

    }
}
