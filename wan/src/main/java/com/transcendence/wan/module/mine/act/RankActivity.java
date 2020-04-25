package com.transcendence.wan.module.mine.act;

import android.content.Context;
import android.content.Intent;

import com.transcendence.blackhole.utils.L;
import com.transcendence.ui.recyclerview.adapter.BaseAbsAdapter;
import com.transcendence.ui.recyclerview.view.LoadMoreLayout;
import com.transcendence.wan.R;
import com.transcendence.wan.base.act.WanBaseActivity;
import com.transcendence.wan.module.mine.adapter.RankAdapter;
import com.transcendence.wan.module.mine.model.RankListBean;
import com.transcendence.wan.module.mine.presenter.RankListPresenter;
import com.transcendence.wan.module.mine.view.RankView;

import java.util.List;

/**
 * @author Joephone on 2019/12/17 15:17
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class RankActivity extends WanBaseActivity<RankListPresenter> implements RankView,LoadMoreLayout.LoadMoreCallback {

    private static int PAGE = 1;
    private boolean isLoadMore;
    private LoadMoreLayout mLoadMoreLayout;
    private BaseAbsAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_rank;
    }

    @Override
    protected RankListPresenter initPresenter() {
        return new RankListPresenter();
    }


    @Override
    protected void initView() {
        mLoadMoreLayout = findViewById(R.id.loadMoreLayout);
        mAdapter = new RankAdapter(getContext());
        mLoadMoreLayout.setAdapter(mAdapter,getContext());
        mLoadMoreLayout.addCallback(this);
    }

    @Override
    protected void loadData() {
        presenter.rankList(PAGE);
    }


    public static void start(Context context) {
        Intent intent = new Intent(context, RankActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void getRankListSuc(int code, List<RankListBean.DatasBean> data) {
        mLoadMoreLayout.onLoadMore(data);
    }

    @Override
    public void getRankListFail(int code) {

    }

    @Override
    public void onViewLoadMore() {
        L.d("onViewLoadMore");
        PAGE ++;
        isLoadMore = true;
        presenter.rankList(PAGE);
    }
}
