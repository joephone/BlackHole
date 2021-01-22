package com.transcendence.wan.module.setting.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.transcendence.ui.recyclerview.adapter.BaseAbsAdapter;
import com.transcendence.wan.R;
import com.transcendence.wan.module.mine.adapter.RankAdapter;
import com.transcendence.wan.module.mine.model.RankListBean;
import com.transcendence.wan.module.setting.bean.LanguageBean;
import com.wirelesspienetwork.overview.views.OverviewCard;

import java.util.ArrayList;
import java.util.List;

public class LanguageSetAdapter extends BaseAbsAdapter<LanguageBean> implements View.OnClickListener{

    private Context mContext;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_wan_setting_language_rv_item,parent,false);
        view.setOnClickListener(this);
        return new LanguageSetViewHolder(view);
    }


    public LanguageSetAdapter(Context context) {
        super(context);
    }

    @Override
    public void onRefresh(List<LanguageBean> list) {

    }

    @Override
    public void onLoadMore(List<LanguageBean> list) {
        mList.addAll(list == null? new ArrayList<>():list);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof LanguageSetViewHolder) {
            LanguageSetViewHolder holder = (LanguageSetViewHolder) viewHolder;
            holder.tvLanguage.setText(mList.get(position).getDesc());
            holder.iv.setVisibility(View.INVISIBLE);
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    holder.iv.setVisibility(View.VISIBLE);
//                }
//            });
        }
    }

    @Override
    public int getItemCount() {
        return mList == null? 0 :mList.size();
    }

    @Override
    public void onClick(View v) {

    }


    private class LanguageSetViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tvLanguage;

        private LanguageSetViewHolder(View view) {
            super(view);
            iv = view.findViewById(R.id.iv);
            tvLanguage = view.findViewById(R.id.tv_language);
        }
    }

    private AdapterView.OnItemClickListener listener;

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        this.listener = listener;
    }
}
