package com.transcendence.core.utils;

import android.support.v4.widget.SwipeRefreshLayout;

import com.transcendence.core.R;
import com.transcendence.core.listener.OnSrflRefreshListener;

/**
 * @Author Joephone on 2020/4/19 14:21
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class SwipeRefreshLayoutUtils {

    private SwipeRefreshLayoutUtils(){}

    private static SwipeRefreshLayoutUtils instance;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public static SwipeRefreshLayoutUtils getInstance(){
        if(instance == null){
            instance = new SwipeRefreshLayoutUtils();
        }
        return instance;
    }


    /**
     * @param colors 下拉刷新的样式颜色数组
     */
    public void setSplStyle(int... colors) {
        //设置下拉进度的背景颜色,默认就是白色
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        //设置下拉进度的主题颜色     该代码错误  mSwipeRefreshLayout.setColorSchemeColors(colors);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.srflone,R.color.srfltwo,R.color.srflthree);
    }

    /**
     * 设置SeRefreshLayout的方法
     *  进入页面自动刷新
     * @param srfl
     */
    public void initSrfl(SwipeRefreshLayout srfl, final OnSrflRefreshListener linstener) {
        mSwipeRefreshLayout = srfl;
        setSplStyle();
        mSwipeRefreshLayout.measure(0, 0);
        mSwipeRefreshLayout.setRefreshing(true);
        //设置下拉的监听
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (linstener != null) {
                    linstener.onSrflRefresh();
                }
            }
        });
    }

    public void finishRefresh(){
        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

}
