package com.transcendence.wan.module.dama.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.transcendence.blackhole.listener.OnSrflRefreshListener;
import com.transcendence.blackhole.utils.L;
import com.transcendence.blackhole.utils.SwipeRefreshLayoutUtils;
import com.transcendence.blackhole.widget.rv.listener.OnMyScrollListener;
import com.transcendence.blackhole.widget.rv.view.MyRecyclerView;
import com.transcendence.blackhole.widget.rv.wrapper.LoadMoreWrapper;
import com.transcendence.wan.R;
import com.transcendence.wan.module.dama.adapter.DamaAdapter;
import com.transcendence.wan.module.dama.model.DamaBean;
import com.transcendence.wan.core.mvp.WanBaseFragment;
import com.transcendence.wan.module.dama.presenter.DamaPresenter;
import com.transcendence.wan.module.dama.view.DamaView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joephone on 2019/11/27 11:50
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class DamaFragment extends WanBaseFragment<DamaPresenter> implements DamaView,OnSrflRefreshListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";

    private static int PAGE = 0;

    private View emptyView;
    private MyRecyclerView mRv;
    private SwipeRefreshLayout mSrf;
    private DamaAdapter adapter;
    private boolean isLoadMore;
    private LoadMoreWrapper mLoadMoreWrapper;
    private List<DamaBean.DataBean.DatasBean> sourceList = new ArrayList<>();

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    L.d("msg 1");
                    if(PAGE == 1){
                        sourceList.clear();
                    }

                    List<DamaBean.DataBean.DatasBean> temp = (List<DamaBean.DataBean.DatasBean>) msg.obj;
                    if(temp !=null){
                        if(isLoadMore){
                            if(sourceList.size()>=10){
                                mLoadMoreWrapper.setLoadStateNotify(mLoadMoreWrapper.LOADING_COMPLETE);
                            }else {
                                mLoadMoreWrapper.setLoadStateNotify(mLoadMoreWrapper.LOADING_END);
                            }
                            sourceList.addAll(temp);
                        }else {
                            sourceList = temp;
                        }
                    }

                    if(adapter == null){
                        L.d("adapter == null");
                        adapter = new DamaAdapter(sourceList);
                        mLoadMoreWrapper = new LoadMoreWrapper(adapter);
                        mRv.setAdapter(mLoadMoreWrapper);

                    }else if(adapter!=null){
                        if(sourceList!=null && sourceList.size()>0){
                            adapter.setList(sourceList);
                        }
                        adapter.notifyDataSetChanged();
                    }
                    if (mSrf != null && mSrf.isRefreshing()) {
                        SwipeRefreshLayoutUtils.getInstance().finishRefresh();
                    }

                    break;
                case 2:
                    SwipeRefreshLayoutUtils.getInstance().finishRefresh();
                    break;
            }
        }
    };

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_navi_dama;
    }

    @Nullable
    @Override
    protected DamaPresenter initPresenter() {
        return new DamaPresenter();
    }

    @Override
    protected void initView() {
        L.d("DamaFragment onCreateView");
        emptyView = findViewById(R.id.view_empty);
        mRv = (MyRecyclerView) findViewById(R.id.rv);
        mSrf = findViewById(R.id.srf);
        SwipeRefreshLayoutUtils.getInstance().initSrfl(mSrf,this);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRv.setLayoutManager(manager);
        mRv.setEmptyView(emptyView);
        mRv.addOnScrollListener(new OnMyScrollListener() {
            @Override
            public void onLoadMore() {
                PAGE ++;
                isLoadMore = true;
                mLoadMoreWrapper.setLoadStateNotify(mLoadMoreWrapper.LOADING);
                presenter.getArticleList(PAGE);
            }
        });
    }

    @Override
    protected void loadData() {
        L.d("dama loadData");
        onSrflRefresh();
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
    public void getUserArticleListSuccess(int code, List<DamaBean.DataBean.DatasBean> data) {
        Message message = Message.obtain();
        message.what = 1;
        message.obj = data;
        mHandler.sendMessage(message);

    }

    @Override
    public void getUserArticleListFailed(int code, String msg) {
        SwipeRefreshLayoutUtils.getInstance().finishRefresh();
    }


    @Override
    public void onSrflRefresh() {
        PAGE = 0;
        isLoadMore = false;
        presenter.getArticleList(PAGE);
        SwipeRefreshLayoutUtils.getInstance().finishRefresh();
    }



}
