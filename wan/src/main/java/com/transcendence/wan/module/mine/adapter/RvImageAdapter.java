package com.transcendence.wan.module.mine.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.transcendence.core.utils.GlideUtils;
import com.transcendence.core.utils.L;
import com.transcendence.wan.R;
import com.transcendence.wan.listener.OnMyItemClickListener;
import com.transcendence.wan.module.beauty.model.BeautyBean;
import com.transcendence.wan.module.mine.model.MineBean;

import java.util.List;

/**
 * @Author Joephone on 2021/4/6 0006 下午 2:04
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class RvImageAdapter extends BaseQuickAdapter<BeautyBean, RvImageAdapter.BeautyViewHolder> {



    public RvImageAdapter(@Nullable List<BeautyBean> data) {
        super(R.layout.fragment_navi_beauty_item, data);
        L.d("RvImageAdapter");
    }

    @Override
    protected void convert(BeautyViewHolder helper, BeautyBean item) {
        L.d("convert");
        helper.tv_name.setText(item.getDesc());
        GlideUtils.loadImageFromUrl1(item.getUrl(),helper.iv_img);
    }


    private OnMyItemClickListener listener;

    public void setListener(OnMyItemClickListener listener) {
        this.listener = listener;
    }

    public class BeautyViewHolder extends BaseViewHolder {

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
}
