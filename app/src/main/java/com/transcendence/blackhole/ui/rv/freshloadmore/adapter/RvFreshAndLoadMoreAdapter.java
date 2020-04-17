package com.transcendence.blackhole.ui.rv.freshloadmore.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.transcendence.blackhole.R;
import com.transcendence.blackhole.ui.rv.freshloadmore.bean.RvFreshLoadMoreBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Joephone on 2020/4/16 23:06
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class RvFreshAndLoadMoreAdapter extends RecyclerView.Adapter<RvFreshAndLoadMoreAdapter.RvFreshAndLoadMoreViewHolder>{
    private Context mContext;
    private List<RvFreshLoadMoreBean.DataBean> sourceList = new ArrayList();

    public RvFreshAndLoadMoreAdapter(Context context, List<RvFreshLoadMoreBean.DataBean> data) {
        mContext = context;
        sourceList = data;
    }

    @NonNull
    @Override
    public RvFreshAndLoadMoreAdapter.RvFreshAndLoadMoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int positon) {
        View inflater =LayoutInflater.from(mContext).inflate(R.layout.activity_ui_rv_fresh_loadmore_item,parent,false);
        RvFreshAndLoadMoreViewHolder holder = new RvFreshAndLoadMoreViewHolder(inflater);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RvFreshAndLoadMoreAdapter.RvFreshAndLoadMoreViewHolder holder, int positon) {
        Glide.with(mContext)
                .load(sourceList.get(positon).getUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade(5,1000)
                .override(200,300)
                .placeholder(R.mipmap.beauty01)
                .into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return sourceList.size();
    }

    public class RvFreshAndLoadMoreViewHolder extends RecyclerView.ViewHolder {

        ImageView iv;

        public RvFreshAndLoadMoreViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
        }
    }
}
