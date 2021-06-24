package com.transcendence.blackhole.ui.rv.freshloadmore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.transcendence.blackhole.R;
import com.transcendence.blackhole.ui.rv.freshloadmore.bean.RvLoadMoreBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Joephone on 2020/4/16 23:06
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class RvLoadMoreAdapter extends RecyclerView.Adapter<RvLoadMoreAdapter.RvFreshAndLoadMoreViewHolder>{
    private Context mContext;
    private List<RvLoadMoreBean.DataBean> sourceList = new ArrayList();

    public RvLoadMoreAdapter(Context context, List<RvLoadMoreBean.DataBean> data) {
        mContext = context;
        sourceList = data;
    }

    @NonNull
    @Override
    public RvLoadMoreAdapter.RvFreshAndLoadMoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int positon) {
        View inflater =LayoutInflater.from(mContext).inflate(R.layout.activity_ui_rv_loadmore_item,parent,false);
        RvFreshAndLoadMoreViewHolder holder = new RvFreshAndLoadMoreViewHolder(inflater);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RvLoadMoreAdapter.RvFreshAndLoadMoreViewHolder holder, int positon) {
        Glide.with(mContext)
                .load(sourceList.get(positon).getUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade(5,1000)
                .override(200,300)
                .placeholder(R.drawable.pic_404)
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
