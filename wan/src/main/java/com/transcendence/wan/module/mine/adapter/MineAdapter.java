package com.transcendence.wan.module.mine.adapter;

import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.transcendence.wan.R;
import com.transcendence.wan.module.mine.model.MineBean;

import java.util.List;

/**
 * @Author Joephone on 2020/5/6 22:20
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class MineAdapter extends BaseQuickAdapter<MineBean,BaseViewHolder> {


    public MineAdapter(List<MineBean> data) {
        super(R.layout.fragment_navi_mine_item,data);
    }


    @Override
    protected void convert(BaseViewHolder helper, MineBean item) {
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.OnItemClick(helper.getAdapterPosition());
                }
            }
        });
        helper.setText(R.id.tv_left, TextUtils.isEmpty(item.getTvLeft())?"":item.getTvLeft());
        helper.setText(R.id.tv_right, TextUtils.isEmpty(item.getTvLeft())?"":item.getTvRight());
        helper.setImageResource(R.id.iv_left,mContext.getResources().obtainTypedArray(R.array.mine_item_left_image).getResourceId(helper.getAdapterPosition(), 0));
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
}
