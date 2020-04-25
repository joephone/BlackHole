package com.transcendence.wan.module.mine.adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.transcendence.ui.recyclerview.adapter.BaseAbsAdapter;
import com.transcendence.wan.R;
import com.transcendence.wan.module.mine.model.RankListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Joephone on 2020/4/25 0:35
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class RankAdapter extends BaseAbsAdapter<RankListBean.DatasBean>{
    private Context mContext;
    private int mMax = 0;
    public RankAdapter(Context context) {
        super(context);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_mine_rank_item,parent,false);
        return new RankListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof RankListViewHolder) {

            RankListViewHolder holder = (RankListViewHolder) viewHolder;
            if(holder.pb.getTag()!=null){
                mMax = (int)holder.pb.getTag();
            } else {
                mMax = mList.get(0).getCoinCount();
                holder.pb.setTag(mMax);
            }


            doProgressAnim(holder.pb, mList.get(position).getCoinCount());

            holder.tv_index.setText("" + position);
            holder.tv_user_name.setText(mList.get(position).getUsername());
            holder.tv_coin_count.setText("" + mList.get(position).getCoinCount());

            if (position == 0) {
                holder.iv_index.setImageResource(R.drawable.ic_rank_1);
                holder.tv_index.setTextColor(ContextCompat.getColor(holder.tv_index.getContext(), R.color.text_surface_alpha));
                holder.tv_index.setTextSize(TypedValue.COMPLEX_UNIT_PX, holder.tv_index.getContext().getResources().getDimension(R.dimen.text_auxiliary));
            } else if (position == 1) {
                holder.iv_index.setImageResource(R.drawable.ic_rank_2);
                holder.tv_index.setTextColor(ContextCompat.getColor(holder.tv_index.getContext(), R.color.text_surface_alpha));
                holder.tv_index.setTextSize(TypedValue.COMPLEX_UNIT_PX, holder.tv_index.getContext().getResources().getDimension(R.dimen.text_auxiliary));
            } else if (position == 2) {
                holder.iv_index.setImageResource(R.drawable.ic_rank_3);
                holder.tv_index.setTextColor(ContextCompat.getColor(holder.tv_index.getContext(), R.color.text_surface_alpha));
                holder.tv_index.setTextSize(TypedValue.COMPLEX_UNIT_PX, holder.tv_index.getContext().getResources().getDimension(R.dimen.text_auxiliary));
            } else {
                holder.iv_index.setImageResource(R.color.transparent);
                holder.tv_index.setTextColor(ContextCompat.getColor(holder.tv_index.getContext(), R.color.text_second));
                holder.tv_index.setTextSize(TypedValue.COMPLEX_UNIT_PX, holder.tv_index.getContext().getResources().getDimension(R.dimen.text_content));
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList == null? 0 :mList.size();
    }


    @Override
    public void onRefresh(List<RankListBean.DatasBean> list) {

    }

    @Override
    public void onLoadMore(List<RankListBean.DatasBean> list) {
        mList.addAll(list == null? new ArrayList<>():list);

    }

    private class RankListViewHolder extends RecyclerView.ViewHolder {

        ProgressBar pb;
        ImageView iv_index;
        TextView tv_index;
        TextView tv_user_name;
        TextView tv_coin_count;



        private RankListViewHolder(View view) {
            super(view);
            pb = view.findViewById(R.id.pb);
            iv_index = view.findViewById(R.id.iv_index);
            tv_index = view.findViewById(R.id.tv_index);
            tv_user_name = view.findViewById(R.id.tv_user_name);
            tv_coin_count = view.findViewById(R.id.tv_coin_count);

        }
    }


    private void doProgressAnim(final ProgressBar pb, int to) {
        final int f = 1000;
        pb.setMax(mMax * f);
        ValueAnimator animator = ValueAnimator.ofInt(0, to);
        animator.setDuration(1000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                pb.setProgress((int) animation.getAnimatedValue() * f);
            }
        });
        animator.start();
    }
}
