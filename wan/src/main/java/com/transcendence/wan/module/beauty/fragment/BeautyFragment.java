package com.transcendence.wan.module.beauty.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.transcendence.core.global.Global;
import com.transcendence.core.utils.L;
import com.transcendence.ui.recyclerview.adapter.BaseAbsAdapter;
import com.transcendence.ui.recyclerview.view.RefreshLayout;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseFragment;
import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;
import com.transcendence.wan.listener.OnMyItemClickListener;
import com.transcendence.wan.module.beauty.adapter.BeautyAdapter;
import com.transcendence.wan.module.beauty.model.BeautyBean;
import com.transcendence.wan.module.beauty.presenter.BeautyPresenter;
import com.transcendence.wan.module.beauty.view.BeautyView;
import com.transcendence.wan.module.main.adapter.ArticleListAdapter;
import com.transcendence.wan.module.mine.act.ImageViewerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Joephone on 2021/3/5 0005 下午 1:53
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class BeautyFragment extends WanBaseFragment<BeautyPresenter> implements BeautyView,RefreshLayout.RefreshCallback,OnMyItemClickListener {
    private static int PAGE = 0;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";

    private RefreshLayout mRefreshLayout;
    private BaseAbsAdapter mAdapter;
    private boolean isLoadMore;
    private ArrayList<String> imageList = new ArrayList<>();

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
    protected BeautyPresenter initPresenter() {
        return new BeautyPresenter();
    }

    @Override
    protected void initView() {
        mRefreshLayout = findViewById(R.id.rv_beauty);
        mAdapter = new BeautyAdapter(getContext());
        mRefreshLayout.setAdapter(mAdapter,getContext());
        mRefreshLayout.schemeColors();
        mRefreshLayout.addCallback(this);
        ((BeautyAdapter) mAdapter).setListener(this);
    }

    @Override
    protected void loadData() {
        presenter.getBeautyList(PAGE, Global.LIMIT);
    }

    @Override
    public void getBeautyListSuc(int code, List<BeautyBean> list) {
        for (int i = 0; i < list.size(); i++) {
            L.d("list.get(i).getUrl()"+list.get(i).getUrl());
            imageList.add(list.get(i).getUrl());
        }
        if(isLoadMore){
            mRefreshLayout.onLoadMore(list);
        }else {
            mRefreshLayout.onRefresh(list);
        }
    }

    @Override
    public void getBeautyListFail(int code) {

    }

    @Override
    public void onViewRefresh() {
        L.d("onViewRefresh");
        PAGE = 0;
        isLoadMore = false;
        presenter.getBeautyList(PAGE, Global.LIMIT);
    }

    @Override
    public void onViewLoadMore() {
        L.d("onViewLoadMore");
        PAGE ++;
        isLoadMore = true;
        presenter.getBeautyList(PAGE, Global.LIMIT);
    }


    @Override
    public void onItemClick(int position) {
        L.d("onItemClick-position:"+position);
        Intent intent = new Intent(getActivity(), ImageViewerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("currentIndex",position);
        bundle.putStringArrayList("imageList",imageList);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
