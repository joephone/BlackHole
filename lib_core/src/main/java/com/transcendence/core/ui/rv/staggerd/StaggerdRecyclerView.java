package com.transcendence.core.ui.rv.staggerd;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.transcendence.core.R;
import com.transcendence.core.ui.rv.staggerd.callbacks.LoadMoreAndRefresh;


public class StaggerdRecyclerView extends LinearLayout {

    Context c;
    public RecyclerView rv;
    SmartRefreshLayout smartRefreshLayout;
    View root;
    StaggedAdapter staggedAdapter;
    StaggeredGridLayoutManager layoutManager;


    public StaggerdRecyclerView(@NonNull Context context) {
        super(context);

        initViews(context);
    }

    public StaggerdRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context);

    }

    public StaggerdRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initViews(context);
    }


    private void initViews(Context c) {
        this.c = c;
        root = inflate(c, R.layout.ui_rv_stagged_layout, this);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        smartRefreshLayout = root.findViewById(R.id.refresh);
        rv = root.findViewById(R.id.rv);

    }

    /**
     * 配置staggedrv
     */

    public void link(StaggedAdapter staggedAdapter, int spanCount) {

        this.staggedAdapter = staggedAdapter;
        layoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(staggedAdapter);



    }

    public void addCallbackListener(final LoadMoreAndRefresh refreshCallback) {

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshCallback.onRefresh();
                smartRefreshLayout.finishRefresh();
            }
        });

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshCallback.onLoadMore();
                smartRefreshLayout.finishLoadMore();
            }
        });

    }


    public void addAnimation(int anim){

        Animation animation = AnimationUtils.loadAnimation(c,anim);
        LayoutAnimationController animationController = new LayoutAnimationController(animation);
        rv.setLayoutAnimation(animationController);

    }

    /**
     * 设置间距
     * @param itemDecoration
     */
    public void addDecoration(RecyclerView.ItemDecoration itemDecoration){

        rv.addItemDecoration(itemDecoration);

    }

    /**
     * 禁止或开启刷新
     * @param enable
     */
    public void enableRefresh(boolean enable){

        smartRefreshLayout.setEnableRefresh(enable);

    }
    /**
     * 禁止或开启加载更多
     * @param enable
     */
    public void enableLoadMore(boolean enable){

        smartRefreshLayout.setEnableLoadMore(enable);
    }

}
