package com.transcendence.wan.module.beauty.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.transcendence.core.global.Global;
import com.transcendence.core.utils.L;
import com.transcendence.ui.recyclerview.hjq.BaseAdapter;
import com.transcendence.ui.recyclerview.hjq.layout.WrapRecyclerView;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseFragment;
import com.transcendence.wan.module.beauty.adapter.BeautyAdapter;
import com.transcendence.wan.module.beauty.model.BeautyBean;
import com.transcendence.wan.module.beauty.presenter.BeautyPresenter;
import com.transcendence.wan.module.beauty.view.BeautyView;
import com.transcendence.wan.album.act.ImageViewerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Joephone on 2021/3/5 0005 下午 1:53
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class BeautyFragment extends WanBaseFragment<BeautyPresenter> implements BeautyView,
        OnRefreshLoadMoreListener,
        BaseAdapter.OnItemClickListener {
    private static int PAGE = 1;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";


    private SmartRefreshLayout mRefreshLayout;
    private WrapRecyclerView mRecyclerView;

    private BeautyAdapter mAdapter;
    private boolean isLoadMore;
    Handler mHandler = new Handler();
    private ArrayList<String> imageList = new ArrayList<>();
    private ArrayList<String> imageTitles = new ArrayList<>();

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
        mRefreshLayout = findViewById(R.id.rl_refresh);
        mRecyclerView = findViewById(R.id.rv_beauty);
        GridLayoutManager manager = new GridLayoutManager(getActivity(),2);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new BeautyAdapter(getContext());
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshLoadMoreListener(this);
    }

    @Override
    protected void loadData() {
        presenter.getBeautyList(PAGE, Global.LIMIT);
    }

    @Override
    public void getBeautyListSuc(int code, List<BeautyBean> list) {
        imageList.clear();imageTitles.clear();
        for (int i = 0; i < list.size(); i++) {
            imageList.add(list.get(i).getUrl());
            imageTitles.add(list.get(i).getTitle());
        }
        if(isLoadMore){
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAdapter.addData(list);
                    mRefreshLayout.finishLoadMore();
                    mAdapter.setLastPage(mAdapter.getItemCount() >= 100);
                    mRefreshLayout.setNoMoreData(mAdapter.isLastPage());
                }
            }, 1000);
        }else {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAdapter.clearData();
                    mAdapter.addData(list);
                    mRefreshLayout.finishRefresh();
                }
            }, 1000);

        }
    }

    @Override
    public void getBeautyListFail(int code) {

    }


    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        L.d("onViewLoadMore");
        PAGE ++;
        isLoadMore = true;
        presenter.getBeautyList(PAGE, Global.LIMIT);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        L.d("onViewRefresh");
        PAGE = 0;
        isLoadMore = false;
        presenter.getBeautyList(PAGE, Global.LIMIT);
    }

    @Override
    public void onItemClick(RecyclerView recyclerView, View itemView, int position) {
        L.d("onItemClick-position:"+position);
//        Intent intent = new Intent(getActivity(), ImageViewerActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putInt("currentIndex",position);
//        bundle.putStringArrayList("imageList",imageList);
//        intent.putExtras(bundle);
//        startActivity(intent);
        ImageViewerActivity.start(getActivity(),position,imageList,imageTitles);
    }
}
