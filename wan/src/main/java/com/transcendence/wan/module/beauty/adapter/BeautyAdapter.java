package com.transcendence.wan.module.beauty.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.transcendence.core.utils.GlideUtils;
import com.transcendence.core.utils.L;
import com.transcendence.ui.recyclerview.adapter.BaseAbsAdapter;
import com.transcendence.wan.R;
import com.transcendence.wan.listener.OnMyItemClickListener;
import com.transcendence.wan.module.beauty.model.BeautyBean;
import com.transcendence.wan.module.beauty.model.BeautyModel;
import com.transcendence.wan.module.main.adapter.ArticleListAdapter;
import com.transcendence.wan.module.main.bean.ArticleListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Joephone on 2021/3/31 0031 下午 5:01
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class BeautyAdapter extends BaseAbsAdapter<BeautyBean> {

    private Context mContext;

    public BeautyAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public void onRefresh(List<BeautyBean> list) {
        mList.clear();
        mList.addAll(list == null ? mList : list);
    }

    @Override
    public void onLoadMore(List<BeautyBean> list) {
        mList.addAll(list == null ? new ArrayList<>() : list);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof BeautyAdapter.BeautyViewHolder) {
            BeautyAdapter.BeautyViewHolder holder = (BeautyAdapter.BeautyViewHolder) viewHolder;
            if (mList.get(position) != null) {
//                L.d("beauty:"+mList.get(position).getUrl());
                if (!TextUtils.isEmpty(mList.get(position).getUrl())) {
                    GlideUtils.getInstance().loadImageFromUrl(mList.get(position).getUrl(), holder.iv_img);
                }
                if(!TextUtils.isEmpty(mList.get(position).getDesc())){
                    holder.tv_name.setText(mList.get(position).getDesc());
                }
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }
    //mList == null ? 0 : mList.size();
    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setList(List<BeautyBean> list) {
        mList = list;
    }

    @Override
    public BeautyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_navi_beauty_item,parent,false);
        return new BeautyViewHolder(view);
    }


    public class BeautyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_img;
        TextView tv_name;
//        CollectView cv_collect;

        BeautyViewHolder(View view) {
            super(view);
//            tvItem = itemView.findViewById(R.id.tv_item);
            iv_img = view.findViewById(R.id.iv_img);
            tv_name = view.findViewById(R.id.tv_name);
        }
    }

    private OnMyItemClickListener listener;

    public void setListener(OnMyItemClickListener listener) {
        this.listener = listener;
    }
}
