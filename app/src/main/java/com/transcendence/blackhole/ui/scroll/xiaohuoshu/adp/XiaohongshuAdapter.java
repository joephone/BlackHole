package com.transcendence.blackhole.ui.scroll.xiaohuoshu.adp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.transcendence.blackhole.R;

/**
 * @author Joephone on 2019/8/5 14:18
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class XiaohongshuAdapter extends RecyclerView.Adapter<XiaohongshuAdapter.XiaohuoshuViewHolder>{


    @NonNull
    @Override
    public XiaohuoshuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View viewHolder = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_scroll_xiaohongshu_main_item,viewGroup,false);
        return new XiaohuoshuViewHolder(viewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull XiaohuoshuViewHolder xiaohuoshuViewHolder, int position) {

    }



    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public class XiaohuoshuViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivImg;

        public XiaohuoshuViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImg = itemView.findViewById(R.id.ivImg);
        }
    }
}
