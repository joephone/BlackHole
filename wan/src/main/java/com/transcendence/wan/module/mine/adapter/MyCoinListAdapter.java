package com.transcendence.wan.module.mine.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.transcendence.ui.recyclerview.adapter.BaseAbsAdapter;
import com.transcendence.utils.DateUtil;
import com.transcendence.wan.R;
import com.transcendence.wan.module.mine.model.MyCoinListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Joephone on 2020/4/27 10:09
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class MyCoinListAdapter extends BaseAbsAdapter<MyCoinListBean>{

    public MyCoinListAdapter(Context context) {
        super(context);
    }

    @Override
    public MyCoinListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_mine_my_coin_list_item,parent,false);
        return new MyCoinListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof MyCoinListHolder){
            MyCoinListHolder holder = (MyCoinListHolder)viewHolder;
            if(mList.get(position)!=null) {
                if(!TextUtils.isEmpty(mList.get(position).getReason())){
                    holder.tvTitle.setText(mList.get(position).getReason());
                }
                if(mList.get(position).getCoinCount()>=0){
                    holder.tvCoinCount.setText(mList.get(position).getCoinCount());
                }
//                if(mList.get(position).getDate()>0){
//                    holder.tvCreateTime.setText(DateUtil.timeStamp2Date(mList.get(position).getDate()));
//                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList == null? 0:mList.size();
    }

    @Override
    public void onRefresh(List<MyCoinListBean> list) {
        mList.clear();
        mList.addAll(list == null? mList:list);
    }

    @Override
    public void onLoadMore(List<MyCoinListBean> list) {
        mList.addAll(list == null? new ArrayList<>():list);
    }


    public class MyCoinListHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvCoinCount;
//        TextView tvCreateTime;

//        CollectView cv_collect;

        MyCoinListHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvCoinCount = view.findViewById(R.id.tvCoinCount);
//            tvCreateTime = view.findViewById(R.id.tvCreateTime);
        }
    }

}
