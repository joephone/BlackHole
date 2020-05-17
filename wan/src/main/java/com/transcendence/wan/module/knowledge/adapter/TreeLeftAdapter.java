package com.transcendence.wan.module.knowledge.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.transcendence.wan.R;
import com.transcendence.wan.listener.OnMyItemClickListener;
import com.transcendence.wan.module.knowledge.model.TreeBean;

import java.util.List;

/**
 * @Author Joephone on 2020/5/9 5:28
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class TreeLeftAdapter extends BaseQuickAdapter<TreeBean,BaseViewHolder>{

    public TreeLeftAdapter(@Nullable List<TreeBean> data) {
        super(R.layout.fragment_navi_tree_left_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TreeBean item) {
        helper.setText(R.id.tv_name, TextUtils.isEmpty(item.getName())?"":item.getName());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onItemClick(helper.getAdapterPosition());
                }
            }
        });
    }

    private OnMyItemClickListener listener;

    public void setListener(OnMyItemClickListener listener) {
        this.listener = listener;
    }
}
