package com.transcendence.wan.module.dama.adapter;

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
import com.transcendence.wan.module.dama.model.DamaBean;
import com.transcendence.wan.ui.utils.OnClickListener2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Joephone on 2020/4/18 22:15
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class DamaAdapter extends BaseAbsAdapter<DamaBean.DataBean.DatasBean> {

    private Context mContext;
    public DamaAdapter(Context context){
        super(context);
    }

    @Override
    public DamaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_navi_dama_item,parent,false);
        return new DamaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof DamaViewHolder){
            DamaViewHolder holder = (DamaViewHolder)viewHolder;
            if(mList.get(position)!=null) {
                DamaBean.DataBean.DatasBean item = mList.get(position);
                if (item.isTop()) {
                    holder.tv_top.setVisibility(View.VISIBLE);
                } else {
                    holder.tv_top.setVisibility(View.GONE);
                }
                if (item.isFresh()) {
                    holder.tv_new.setVisibility(View.VISIBLE);
                } else {
                    holder.tv_new.setVisibility(View.GONE);
                }
                holder.tv_author.setText(item.getAuthor());
                if (item.getTags() != null && item.getTags().size() > 0) {
//                holder.tv_tag.setText(item.getTags().get(0).getName());
                    holder.tv_tag.setVisibility(View.VISIBLE);
                } else {
                    holder.tv_tag.setVisibility(View.GONE);
                }
                holder.tv_time.setText(item.getNiceDate());
                if (!TextUtils.isEmpty(item.getEnvelopePic())) {
                    GlideUtils.getInstance().loadImageFromUrl(item.getEnvelopePic(), holder.iv_img);
                    holder.iv_img.setVisibility(View.VISIBLE);
                } else {
                    holder.iv_img.setVisibility(View.GONE);
                }
                holder.tv_title.setText(Html.fromHtml(item.getTitle()));
                if (TextUtils.isEmpty(item.getDesc())) {
                    holder.tv_desc.setVisibility(View.GONE);
                    holder.tv_title.setSingleLine(false);
                } else {
                    holder.tv_desc.setVisibility(View.VISIBLE);
                    holder.tv_title.setSingleLine(true);
                    String desc = Html.fromHtml(item.getDesc()).toString();
//                desc = StringUtils.removeAllBank(desc, 2);
                    holder.tv_desc.setText(desc);
                }
//            holder.tv_chapter_name.setText(Html.fromHtml(formatChapterName(item.getSuperChapterName(), item.getChapterName())));
//            if (item.isCollect()) {
//                cv_collect.setChecked(true);
//            } else {
//                cv_collect.setChecked(false);
//            }
                holder.tv_author.setOnClickListener(new OnClickListener2() {
                    @Override
                    public void onClick2(View v) {
//                    UserPageActivity.start(v.getContext(), item.getUserId());
                    }
                });
//            cv_collect.setOnClickListener(new CollectView.OnClickListener() {
//                @Override
//                public void onClick(CollectView v) {
//                    if (!v.isChecked()) {
//                        if (onCollectListener != null) {
//                            onCollectListener.collect(item, v);
//                        }
//                    } else {
//                        if (onCollectListener != null) {
//                            onCollectListener.uncollect(item, v);
//                        }
//                    }
//                }
//            });
                holder.itemView.setOnClickListener(new OnClickListener2() {
                    @Override
                    public void onClick2(View v) {
//                    WebActivity.start(v.getContext(), item);
                    }
                });
            }
        }

    }

    @Override
    public int getItemCount() {
        return mList == null? 0 :mList.size();
    }


    public void setList(List<DamaBean.DataBean.DatasBean> list) {
        mList = list;
    }

    @Override
    public void onRefresh(List<DamaBean.DataBean.DatasBean> list) {
        mList.clear();
        mList.addAll(list == null? mList:list);
    }

    @Override
    public void onLoadMore(List<DamaBean.DataBean.DatasBean> list) {
        mList.addAll(list == null? new ArrayList<>():list);
    }

    public class DamaViewHolder extends RecyclerView.ViewHolder {
        TextView tv_top;
        TextView tv_new;
        TextView tv_author;
        TextView tv_tag;
        TextView tv_time;
        ImageView iv_img;
        TextView tv_title;
        TextView tv_desc;
        TextView tv_chapter_name ;
//        CollectView cv_collect;

        DamaViewHolder(View view) {
            super(view);
//            tvItem = itemView.findViewById(R.id.tv_item);
             tv_top = view.findViewById(R.id.tv_top);
             tv_new = view.findViewById(R.id.tv_new);
             tv_author = view.findViewById(R.id.tv_author);
             tv_tag = view.findViewById(R.id.tv_tag);
             tv_time = view.findViewById(R.id.tv_time);
             iv_img = view.findViewById(R.id.iv_img);
             tv_title = view.findViewById(R.id.tv_title);
             tv_desc = view.findViewById(R.id.tv_desc);
             tv_chapter_name = view.findViewById(R.id.tv_chapter_name);
//             cv_collect = view.findViewById(R.id.cv_collect);

        }
    }
}
