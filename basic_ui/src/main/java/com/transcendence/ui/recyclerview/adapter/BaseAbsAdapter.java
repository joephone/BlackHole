package com.transcendence.ui.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Joephone on 2020/4/23 0:45
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public abstract class BaseAbsAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    protected List<T> mList;
    protected Context mContext;

    public BaseAbsAdapter(Context context) {
        this.mList = new ArrayList<>();
        this.mContext = context;
    }

    protected View create(int layout,ViewGroup parent){
        return LayoutInflater.from(mContext).inflate(layout,parent,false);
    }

    /**
     * 下拉刷新
     * @param list 列表
     */
    public abstract void onRefresh(List<T> list);

    /**
     * 加载更多
     * @param list 列表
     */
    public abstract void onLoadMore(List<T> list);

}
