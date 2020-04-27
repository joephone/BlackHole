package com.transcendence.wan.module.wxpublic.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.transcendence.blackhole.utils.GlideUtils;
import com.transcendence.ui.recyclerview.adapter.BaseAbsAdapter;
import com.transcendence.wan.R;
import com.transcendence.wan.module.main.act.WanWebActivity;
import com.transcendence.wan.module.wxpublic.model.WxChapterArticleBean;
import com.transcendence.wan.ui.utils.OnClickListener2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Joephone on 2020/4/28 0:10
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class WxChapterArticleListAdapter extends BaseAbsAdapter<WxChapterArticleBean>{


    public WxChapterArticleListAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_navi_wx_public_account_list_item,parent,false);
        return new ChapterArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof ChapterArticleViewHolder){
            ChapterArticleViewHolder holder = (ChapterArticleViewHolder)viewHolder;
            if(mList.get(position)!=null){
//                if (item.isTop()) {
//                    tv_top.setVisibility(View.VISIBLE);
//                } else {
//                    tv_top.setVisibility(View.GONE);
//                }
//                if (item.isFresh()) {
//                    tv_new.setVisibility(View.VISIBLE);
//                } else {
//                    tv_new.setVisibility(View.GONE);
//                }
                holder.tv_author.setText(mList.get(position).getAuthor());
                if (mList.get(position).getTags() != null && mList.get(position).getTags().size() > 0) {
                    holder.tv_tag.setText(mList.get(position).getTags().get(0).getName());
                    holder.tv_tag.setVisibility(View.VISIBLE);
                } else {
                    holder.tv_tag.setVisibility(View.GONE);
                }
                holder.tv_time.setText(mList.get(position).getNiceDate());
                if (!TextUtils.isEmpty(mList.get(position).getEnvelopePic())) {
                    GlideUtils.getInstance().loadImageFromUrl(mList.get(position).getEnvelopePic(),holder.iv_img);
                    holder.iv_img.setVisibility(View.VISIBLE);
                } else {
                    holder.iv_img.setVisibility(View.GONE);
                }
                holder.tv_title.setText(Html.fromHtml(mList.get(position).getTitle()));
                if (TextUtils.isEmpty(mList.get(position).getDesc())) {
                    holder.tv_desc.setVisibility(View.GONE);
                    holder.tv_title.setSingleLine(false);
                } else {
                    holder.tv_desc.setVisibility(View.VISIBLE);
                    holder.tv_title.setSingleLine(true);
                    String desc = Html.fromHtml(mList.get(position).getDesc()).toString();
//                    desc = StringUtils.removeAllBank(desc, 2);
                    holder.tv_desc.setText(desc);
                }
//                holder.tv_chapter_name.setText(Html.fromHtml(formatChapterName(item.getSuperChapterName(), item.getChapterName())));
//                if (item.isCollect()) {
//                    cv_collect.setChecked(true);
//                } else {
//                    cv_collect.setChecked(false);
//                }
                holder.tv_author.setOnClickListener(new OnClickListener2() {
                    @Override
                    public void onClick2(View v) {
//                        UserPageActivity.start(v.getContext(), item.getUserId());
                    }
                });

                holder.itemView.setOnClickListener(new OnClickListener2() {
                    @Override
                    public void onClick2(View v) {
                        WanWebActivity.start(v.getContext(), mList.get(position).getId(),
                                mList.get(position).getTitle(),mList.get(position).getLink(),
                                mList.get(position).getAuthor(),
                                false);
                    }
                });
            }


        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 :mList.size();
    }


    @Override
    public void onRefresh(List<WxChapterArticleBean> list) {
        mList.clear();
        mList.addAll(list == null? mList:list);
    }

    @Override
    public void onLoadMore(List<WxChapterArticleBean> list) {
        mList.addAll(list == null? new ArrayList<>():list);
    }


    public class ChapterArticleViewHolder extends RecyclerView.ViewHolder {

        TextView tv_top;
        TextView tv_new;
        TextView tv_author;
        TextView tv_tag;
        TextView tv_time;
        ImageView iv_img;
        TextView tv_title;
        TextView tv_desc;
        TextView tv_chapter_name;

        ChapterArticleViewHolder(View view) {
            super(view);

             tv_top = view.findViewById(R.id.tv_top);
             tv_new = view.findViewById(R.id.tv_new);
             tv_author = view.findViewById(R.id.tv_author);
             tv_tag = view.findViewById(R.id.tv_tag);
             tv_time = view.findViewById(R.id.tv_time);
             iv_img = view.findViewById(R.id.iv_img);
             tv_title = view.findViewById(R.id.tv_title);
             tv_desc = view.findViewById(R.id.tv_desc);
             tv_chapter_name = view.findViewById(R.id.tv_chapter_name);


        }
    }

}
