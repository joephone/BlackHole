package com.transcendence.ui.recyclerview.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.transcendence.ui.R;
import com.transcendence.ui.recyclerview.adapter.BaseAbsAdapter;
import com.transcendence.ui.recyclerview.utils.LoadingState;
import com.transcendence.ui.recyclerview.wrapper.LoadMoreWrapper;

import java.util.List;

/**
 * @Author Joephone on 2020/4/25 0:15
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class LoadMoreLayout<T> extends LinearLayout {

    private MyRecyclerView mRv;
    private LoadMoreWrapper<T> mAdapter;
    private boolean mIsSlidingUp = false;   //是否正在向上滑动
    private View emptyView;

    public LoadMoreLayout(Context context) {
        this(context,null);
    }

    public LoadMoreLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadMoreLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.activity_srf_rv_loadmore, this, true);
        mRv = view.findViewById(R.id.id_recyclerView);
    }


    public void setAdapter(BaseAbsAdapter adapter, Context context){
        mAdapter = new LoadMoreWrapper<>(context,adapter);
//        if(mRv ==null){
//            Log.d("BlackHole","mRv ==null");
//        }
        mRv.setLayoutManager(new LinearLayoutManager(context));
//        mRv.setEmptyView(emptyView);
        mRv.setAdapter(mAdapter);
    }


    public void addCallback(final LoadMoreCallback callback){


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

    public interface LoadMoreCallback {
        void onViewLoadMore();
    }
}
