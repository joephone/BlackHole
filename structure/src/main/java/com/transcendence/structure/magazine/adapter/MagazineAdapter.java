package com.transcendence.structure.magazine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.transcendence.blackhole.utils.GlideUtils;
import com.transcendence.structure.R;
import com.transcendence.structure.magazine.bean.MagazineEntity;
import com.wirelesspienetwork.overview.model.OverviewAdapter;
import com.wirelesspienetwork.overview.model.ViewHolder;

import java.util.List;

/**
 * Created by Ziyu on 2016/10/24.
 */
public class MagazineAdapter extends OverviewAdapter<ViewHolder,MagazineEntity.DataBean.ArticlesBean> {
    private Context mContext;

    public MagazineAdapter(Context context,List<MagazineEntity.DataBean.ArticlesBean> articlesBeen) {
        super(articlesBeen);
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.zuimei_item_magezine_gallary,null);
        return new ViewHolder<View, Object>(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        TextView title = (TextView) viewHolder.itemView.findViewById(R.id.title);
        TextView tvName = (TextView) viewHolder.itemView.findViewById(R.id.tvName);
        TextView subTitle = (TextView) viewHolder.itemView.findViewById(R.id.sub);
        ImageView ivAvatar = (ImageView) viewHolder.itemView.findViewById(R.id.ivAvatar);
        ImageView image = (ImageView) viewHolder.itemView.findViewById(R.id.image);

        MagazineEntity.DataBean.ArticlesBean bean = (MagazineEntity.DataBean.ArticlesBean) viewHolder.model;
        title.setText(bean.getAuthorName()+"期待");
        tvName.setText(bean.getAuthorName());
//        subTitle.setText(bean.getSub_title());
        GlideUtils.getInstance().loadMipmap(mContext,R.mipmap.zuimei_ic_mine_portrait,ivAvatar);
        GlideUtils.getInstance().loadMipmap(mContext,bean.getResId(),image);

//        Glide.with(avatar.getContext()).load(bean.getAuthor().getAvatar_url()).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(avatar);
//        Glide.with(image.getContext()).load(bean.getImage_url()).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(image);
    }
}
