package com.transcendence.wan.module.mine.act;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.transcendence.core.utils.L;
import com.transcendence.ui.recyclerview.adapter.BaseAbsAdapter;
import com.transcendence.ui.recyclerview.view.LoadMoreLayout;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseActivity;
import com.transcendence.wan.module.mine.adapter.MyCoinListAdapter;
import com.transcendence.wan.module.mine.model.MyCoinBean;
import com.transcendence.wan.module.mine.model.MyCoinListBean;
import com.transcendence.wan.module.mine.presenter.MyCoinPresenter;
import com.transcendence.wan.module.mine.view.MyCoinView;
import com.transcendence.wan.utils.AnimatorUtils;

import java.util.List;

/**
 * @Author Joephone on 2020/4/25 14:41
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class MyCoinActivity extends WanBaseActivity<MyCoinPresenter> implements MyCoinView,LoadMoreLayout.LoadMoreCallback {
    private static int PAGE = 1;
    private TextView tvCoin;
    private LoadMoreLayout mLoadMoreLayout;
    private BaseAbsAdapter mAdapter;

    public static void start(Context context) {
        Intent intent = new Intent(context, MyCoinActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_my_rank;
    }

    @Nullable
    @Override
    protected MyCoinPresenter initPresenter() {
        return new MyCoinPresenter();
    }

    @Override
    protected void initView() {
        tvCoin = findViewById(R.id.tv_coin);
        mLoadMoreLayout = findViewById(R.id.loadMoreLayout);
        mAdapter = new MyCoinListAdapter(this);
        mLoadMoreLayout.setAdapter(mAdapter,getContext());
        mLoadMoreLayout.addCallback(this);
    }

    @Override
    protected void loadData() {
        presenter.getCoin();
        presenter.getMyCoinList(PAGE);
    }

    @Override
    public void getMyCoinSuc(int code, MyCoinBean bean) {
        AnimatorUtils.doIntAnim(tvCoin, bean.getCoinCount(), 1000);
    }

    @Override
    public void getMyCoinFail(int code) {

    }

    @Override
    public void getMyCoinListSuc(int code, List<MyCoinListBean> list) {
        mLoadMoreLayout.onLoadMore(list);
    }

    @Override
    public void getMyCoinListFail(int code) {

    }

    @Override
    public void onViewLoadMore() {
        L.d("onViewLoadMore");
        PAGE ++;
        presenter.getMyCoinList(PAGE);
    }
}
