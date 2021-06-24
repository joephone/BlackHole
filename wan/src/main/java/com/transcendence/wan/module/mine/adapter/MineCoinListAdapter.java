package com.transcendence.wan.module.mine.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.transcendence.utils.DateUtils;
import com.transcendence.wan.R;
import com.transcendence.wan.module.mine.model.MyCoinListBean;

import java.util.List;

/**
 * @Author Joephone on 2021/01/21 18:03
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class MineCoinListAdapter extends BaseQuickAdapter<MyCoinListBean, MineCoinListAdapter.MineCoinListHolder> {


    public MineCoinListAdapter(int layoutResId, @Nullable List<MyCoinListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(MineCoinListHolder helper, MyCoinListBean item) {
        if(!TextUtils.isEmpty(item.getReason())){
            helper.tvTitle.setText(item.getReason());
        }

        if(item.getDate()>0){
            helper.tvCreateTime.setText(DateUtils.timeStamp2Date(item.getDate()));
        }

//        if(item.getCoinCount()>=0){
//            helper.tvCoinCount.setText(item.getCoinCount());
//        }
    }

    public class MineCoinListHolder extends BaseViewHolder {

        TextView tvTitle;
        TextView tvCoinCount;
        TextView tvCreateTime;

//        CollectView cv_collect;

        MineCoinListHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvCreateTime = view.findViewById(R.id.tvCreateTime);
//            tvCoinCount = view.findViewById(R.id.tvCoinCount);
        }
    }
}
