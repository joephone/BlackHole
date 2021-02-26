package com.transcendence.wan.module.knowledge.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.transcendence.core.utils.L;
import com.transcendence.wan.R;
import com.transcendence.wan.listener.OnMyItemClickListener;
import com.transcendence.wan.module.knowledge.model.TreeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Joephone on 2020/5/9 5:28
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class TreeLeftAdapter extends BaseQuickAdapter<TreeBean,BaseViewHolder>{

    private Context mContext;
    private List<Boolean> isClicks;//控件是否被点击,默认为false，如果被点击，改变值，控件根据值改变自身颜色

    public TreeLeftAdapter(@Nullable List<TreeBean> data,Context context) {
        super(R.layout.fragment_navi_tree_left_item, data);
        this.mContext = context;
        isClicks = new ArrayList<>();
        for(int i = 0;i< data.size();i++){
            isClicks.add(false);
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, TreeBean item) {
        helper.setText(R.id.tv_name, TextUtils.isEmpty(item.getName())?"":item.getName());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = helper.getAdapterPosition();
                if(position==-1){
                    return;
                }
                for(int i = 0; i <isClicks.size();i++){
                    isClicks.set(i,false);
                }
                isClicks.set(position,true);
                notifyDataSetChanged();
                if(listener!=null){
                    listener.onItemClick(position);
                }
            }
        });

        if(isClicks.get(helper.getAdapterPosition())){
            helper.itemView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.bg_public));
        }else{
            helper.itemView.setBackground(ContextCompat.getDrawable(mContext,R.drawable.bg_press_color_surface));
        }
    }

    private OnMyItemClickListener listener;

    public void setListener(OnMyItemClickListener listener) {
        this.listener = listener;
    }
}
