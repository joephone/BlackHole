package com.transcendence.wan.module.beauty.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.transcendence.ui.recyclerview.hjq.AppAdapter;
import com.transcendence.utils.glide.GlideUtils;
import com.transcendence.wan.R;
import com.transcendence.wan.listener.OnMyItemClickListener;
import com.transcendence.wan.module.beauty.model.BeautyBean;


/**
 * @Author Joephone on 2021/3/31 0031 下午 5:01
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class BeautyAdapter extends AppAdapter<BeautyBean> {

    private Context mContext;

    public BeautyAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new BeautyViewHolder();
    }

    public class BeautyViewHolder extends AppAdapter<?>.ViewHolder {

        ImageView iv_img;
        TextView tv_name;

        public BeautyViewHolder() {
            super(R.layout.fragment_navi_beauty_item);
        }

//        CollectView cv_collect;

        @Override
        public void onBindView(int position) {
            iv_img = findViewById(R.id.iv_img);
            tv_name = findViewById(R.id.tv_name);
            if (!TextUtils.isEmpty(getItem(position).getUrl())) {
//                    GlideUtils.loadImageFromUrl1(getItem(position).getUrl(), iv_img);
//                    ImageLoader.loadImage(holder.iv_img,mList.get(position).getUrl());
                GlideUtils.showImageView(mContext,R.drawable.img_default_meizi,getItem(position).getUrl()
                        ,iv_img);
            }
            if(!TextUtils.isEmpty(getItem(position).getDesc())){
                tv_name.setText(getItem(position).getDesc());
            }
        }
    }

    private OnMyItemClickListener listener;

    public void setListener(OnMyItemClickListener listener) {
        this.listener = listener;
    }
}
