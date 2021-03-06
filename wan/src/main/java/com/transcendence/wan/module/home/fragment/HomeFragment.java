package com.transcendence.wan.module.home.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.transcendence.core.global.Global;
import com.transcendence.core.utils.L;
import com.transcendence.core.utils.ScreenUtils;
import com.transcendence.core.widget.custom.banner.BannerLayout;
import com.transcendence.ui.recyclerview.adapter.BaseAbsAdapter;
import com.transcendence.ui.recyclerview.hjq.BaseAdapter;
import com.transcendence.ui.recyclerview.hjq.layout.WrapRecyclerView;
import com.transcendence.ui.recyclerview.view.LoadMoreLayout;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseFragment;
import com.transcendence.wan.module.beauty.adapter.BeautyAdapter;
import com.transcendence.wan.module.home.model.BannerBean;
import com.transcendence.wan.module.home.presenter.HomePresenter;
import com.transcendence.wan.module.home.view.HomeView;
import com.transcendence.wan.module.main.act.WanMainActivity;
import com.transcendence.wan.module.main.act.WanWebActivity;
import com.transcendence.wan.module.main.adapter.ArticleListAdapter;
import com.transcendence.wan.module.main.bean.ArticleListBean;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Joephone on 2019/11/27 11:50
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class HomeFragment extends WanBaseFragment<HomePresenter> implements HomeView,
                                                                    BannerLayout.OnBannerItemClickListener,
                                                                    OnRefreshLoadMoreListener,
                                                                    BaseAdapter.OnItemClickListener  {

    private static final String ARG_SHOW_TEXT = "text";
    private static int PAGE = 0;
    private ImageView ivLeft,ivRight;


    private BannerLayout mBanner;
    private List<BannerBean> mBannerBeans;
    private List<String> mBannerUrls;

    private SmartRefreshLayout mRefreshLayout;
    private WrapRecyclerView mRecyclerView;
    private ArticleListAdapter mAdapter;
    private boolean isLoadMore;
    Handler mHandler = new Handler();

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_navi_home;
    }


    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView() {
        L.d("HomeFragment initView");
        mBanner = findViewById(R.id.banner);

        ivLeft = findViewById(R.id.ivLeft);
        ivRight = findViewById(R.id.ivRight);
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity();
                if(activity instanceof WanMainActivity){
                    WanMainActivity mainActivity = (WanMainActivity) activity;
                    mainActivity.slideToDama();
                }
            }
        });
        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        initRv();
    }


    @Override
    protected void loadData() {
        presenter.initBannerData();
        presenter.getArticleList(PAGE);
    }



    private void createHeaderBanner() {
        if (mBanner == null) {
            mBanner = new BannerLayout(getContext());
            int height = (int) (ScreenUtils.getScreenWidth(getContext()) * (9F / 16F));
            mBanner.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
            mBanner.setOnBannerItemClickListener(this);
            mBanner.startAutoPlay();
        }
    }

    private void initRv() {
        mRefreshLayout = findViewById(R.id.rl_refresh);
        mAdapter = new ArticleListAdapter(getContext());
        mRecyclerView = findViewById(R.id.rv_home);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshLoadMoreListener(this);
        mRefreshLayout.setEnableRefresh(false);
    }



    public static HomeFragment newInstance(String title) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onItemClick(int position) {
        BannerBean item = mBannerBeans.get(position);
        if (item != null) {
            WanWebActivity.start(getContext(), item);
        }
    }

    @Override
    public void onScroll(int position) {

    }

    @Override
    public void onMove(int position) {

    }


    @Override
    public void onStart() {
        super.onStart();
        if (mBanner != null) {
            mBanner.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBanner != null) {
            mBanner.stopAutoPlay();
        }
    }

    @Override
    public void getBannerSuccess(int code, List<BannerBean> model) {
        L.d("getBannerSuccess");
        mBannerBeans = model;
        mBannerUrls = new ArrayList<>(model.size());
        List<String> titles = new ArrayList<>(model.size());
        for (BannerBean bean : model) {
            mBannerUrls.add(bean.getImagePath());
            titles.add(bean.getTitle());
        }
        createHeaderBanner();
        mBanner.setViewUrls(mBannerUrls);
        mBanner.setOnBannerItemClickListener(this);

    }

    @Override
    public void getBannerFail(int code, String msg) {

    }

    @Override
    public void getArticleListHomeSuccess(int code, List<ArticleListBean.DataBean.DatasBean> data) {
        L.d("getArticleListSuccess");
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
    public void getArticleListHomeFailed(int code, String msg) {

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
