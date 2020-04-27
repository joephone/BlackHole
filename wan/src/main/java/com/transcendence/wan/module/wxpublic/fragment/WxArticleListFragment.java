package com.transcendence.wan.module.wxpublic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.transcendence.ui.recyclerview.adapter.BaseAbsAdapter;
import com.transcendence.ui.recyclerview.view.RefreshLayout;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseFragment;
import com.transcendence.wan.module.wxpublic.adapter.WxChapterArticleListAdapter;
import com.transcendence.wan.module.wxpublic.model.WxChapterArticleBean;
import com.transcendence.wan.module.wxpublic.model.WxChapterBean;
import com.transcendence.wan.module.wxpublic.presenter.WxChapterArticleListPresenter;
import com.transcendence.wan.module.wxpublic.view.WxChapterArticleListView;

import java.util.List;

/**
 * @Author Joephone on 2020/4/18 15:47
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class WxArticleListFragment extends WanBaseFragment<WxChapterArticleListPresenter> implements WxChapterArticleListView,RefreshLayout.RefreshCallback {

    private static int PAGE = 1;
    private RefreshLayout mRefreshView;
    private BaseAbsAdapter mAdapter;
    private boolean isLoadMore;

    private WxChapterBean mBean;

    public static WxArticleListFragment newInstance(WxChapterBean bean) {
        WxArticleListFragment fragment = new WxArticleListFragment();
        Bundle args = new Bundle();
        args.putSerializable("bean", bean);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_navi_wx_public_account_list;
    }

    @Nullable
    @Override
    protected WxChapterArticleListPresenter initPresenter() {
        return new WxChapterArticleListPresenter();
    }

    @Override
    protected void initView() {
        if (getArguments() != null) {
            mBean = (WxChapterBean) getArguments().getSerializable("bean");
//            L.d("WxArticleListFragment onCreateView"+mList.size());
        }
        mRefreshView = findViewById(R.id.refreshView);
        mAdapter = new WxChapterArticleListAdapter(getContext());
        mRefreshView.setAdapter(mAdapter,getContext());
        mRefreshView.addCallback(this);
    }

    @Override
    protected void loadData() {
        presenter.getWxArticleChapterList(mBean.getId(),PAGE);
    }


    @Override
    public void getWxChapterArticleListSuc(int code, List<WxChapterArticleBean> list) {
        if(isLoadMore){
            mRefreshView.onLoadMore(list);
        }else {
            mRefreshView.onRefresh(list);
        }
    }

    @Override
    public void getWxChapterArticleListFail() {

    }

    @Override
    public void onViewRefresh() {
        PAGE = 1;
        isLoadMore = false;
        presenter.getWxArticleChapterList(mBean.getId(),PAGE);
    }

    @Override
    public void onViewLoadMore() {
        PAGE++;
        isLoadMore = true;
        presenter.getWxArticleChapterList(mBean.getId(),PAGE);
    }
}
