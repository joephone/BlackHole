package com.transcendence.ui.recyclerview.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.transcendence.ui.R;
import com.transcendence.ui.recyclerview.adapter.BaseAbsAdapter;
import com.transcendence.ui.recyclerview.utils.LoadingState;
import com.transcendence.ui.recyclerview.wrapper.ReFreshAndLoadMoreWrapper;

import java.util.List;

/**
 * @Author Joephone on 2020/4/23 1:24
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class RefreshLayout<T> extends LinearLayout{

    private MyRecyclerView mRv;
    private SwipeRefreshLayout mSrfl;
    private ReFreshAndLoadMoreWrapper<T> mAdapter;
    private boolean mIsSlidingUp = false;   //是否正在向上滑动
    private View emptyView;


    public RefreshLayout(@NonNull Context context) {
        this(context,null);
    }

    public RefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        LayoutInflater.from(context).inflate(R.layout.activity_srf_rv_new, this, true);
        View view = LayoutInflater.from(context).inflate(R.layout.activity_srf_rv_new, this, true);
//        View.inflate(context,R.layout.activity_srf_rv_new,this);
        mRv = view.findViewById(R.id.id_recyclerView);
        mSrfl = view.findViewById(R.id.id_swipeRefreshLayout);
//        emptyView = view.findViewById(R.id.id_emptyView);
    }


    public void setAdapter(BaseAbsAdapter adapter, Context context){
        mAdapter = new ReFreshAndLoadMoreWrapper<>(context,adapter);
        if(mRv ==null){
            Log.d("BlackHole","mRv ==null");
        }
        mRv.setLayoutManager(new LinearLayoutManager(context));
//        mRv.setEmptyView(emptyView);
        mRv.setAdapter(mAdapter);
    }

    public void schemeColors(){
        mSrfl.setColorSchemeResources(R.color.srflone,R.color.srfltwo,R.color.srflthree);
    }

    public void addCallback(final RefreshCallback callback){
        mSrfl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.setLoadState(LoadingState.INIT);
                if(callback !=null){
                    callback.onViewRefresh();
                }
            }
        });
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(RecyclerView.SCROLL_STATE_IDLE !=newState){
                    return;
                }
                RecyclerView.LayoutManager lm = recyclerView.getLayoutManager();
                if(lm == null || !(lm instanceof LinearLayoutManager)){
                    return;
                }
                LinearLayoutManager manager = (LinearLayoutManager) lm;
                int last = manager.findLastCompletelyVisibleItemPosition();
                boolean isEnd = manager.getItemCount() -1  == last; //滑到底
                boolean isState = mAdapter.getLoadingState() != LoadingState.LOADING_END;
                if(isEnd && mIsSlidingUp && isState && callback!=null){
                    mAdapter.setLoadState(LoadingState.LOADING);
                    callback.onViewLoadMore();
                }
                //到底时不执行加载更多，加载更多时修改为“加载”
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // > 0 indicates that it is scroll up, <= 0 means stop or scroll down.
                mIsSlidingUp = dy > 0;
                RecyclerView.LayoutManager lm = recyclerView.getLayoutManager();
                if(lm == null || !(lm instanceof LinearLayoutManager)){
                    return;
                }
                LinearLayoutManager manager = (LinearLayoutManager) lm;
                int first = manager.findFirstCompletelyVisibleItemPosition();
                int last = manager.findLastCompletelyVisibleItemPosition();
                int count = manager.getItemCount();
                if(last +1 == count && first == 0){  //少于一页
                    mAdapter.setLoadState(LoadingState.INIT);
                }else {
                    mAdapter.setLoadState(LoadingState.LOADING_COMPLETE);  //不是到底
                }
                // 总数据不足一页时，不显示FooterView 到底时不修改状态
            }
        });
    }


    public void onRefresh(List<T> list){
        if(list!=null){
            mAdapter.onRefresh(list);
        }
        stopSrfl();
    }

    public void stopSrfl(){
        if(!mSrfl.isRefreshing()){
            return;
        }
        mSrfl.setRefreshing(false);
    }

    public void onLoadMore(List<T> list){
        if(list == null || list.size() ==0){
            mAdapter.setLoadState(LoadingState.LOADING_END);
        }else {
            mAdapter.onLoadMore(list);
            mAdapter.setLoadState(LoadingState.LOADING_COMPLETE);
        }
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
    }

    public interface RefreshCallback {
        void onViewRefresh();
        void onViewLoadMore();
    }
}
