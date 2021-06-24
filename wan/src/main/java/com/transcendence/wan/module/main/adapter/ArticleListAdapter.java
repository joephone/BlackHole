package com.transcendence.wan.module.main.adapter;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.transcendence.core.utils.GlideUtils;
import com.transcendence.ui.recyclerview.adapter.BaseAbsAdapter;
import com.transcendence.ui.recyclerview.hjq.AppAdapter;
import com.transcendence.wan.R;
import com.transcendence.wan.module.beauty.model.BeautyBean;
import com.transcendence.wan.module.main.bean.ArticleListBean;
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

public class ArticleListAdapter extends AppAdapter<ArticleListBean.DataBean.DatasBean> {

    private Context mContext;
    public ArticleListAdapter(Context context){
        super(context);
    }

    @Override
    public ArticleListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArticleListViewHolder();
    }

    public class ArticleListViewHolder extends AppAdapter<?>.ViewHolder {
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

        ArticleListViewHolder() {
            super(R.layout.fragment_navi_main_article_list_item);
//            tvItem = itemView.findViewById(R.id.tv_item);
             tv_top = findViewById(R.id.tv_top);
             tv_new = findViewById(R.id.tv_new);
             tv_author = findViewById(R.id.tv_author);
             tv_tag = findViewById(R.id.tv_tag);
             tv_time = findViewById(R.id.tv_time);
             iv_img = findViewById(R.id.iv_img);
             tv_title = findViewById(R.id.tv_title);
             tv_desc = findViewById(R.id.tv_desc);
             tv_chapter_name = findViewById(R.id.tv_chapter_name);
//             cv_collect = view.findViewById(R.id.cv_collect);

        }

        @Override
        public void onBindView(int position) {
            if (getItem(position).isTop()) {
                tv_top.setVisibility(View.VISIBLE);
            } else {
                tv_top.setVisibility(View.GONE);
            }
            if (getItem(position).isFresh()) {
                tv_new.setVisibility(View.VISIBLE);
            } else {
                tv_new.setVisibility(View.GONE);
            }
            tv_author.setText(getItem(position).getAuthor());
            if (getItem(position).getTags() != null && getItem(position).getTags().size() > 0) {
                tv_tag.setText(getItem(position).getTags().get(0).getName());
                tv_tag.setVisibility(View.VISIBLE);
            } else {
                tv_tag.setVisibility(View.GONE);
            }
            tv_time.setText(getItem(position).getNiceDate());
            if (!TextUtils.isEmpty(getItem(position).getEnvelopePic())) {
                GlideUtils.getInstance().loadImageFromUrl(getItem(position).getEnvelopePic(), iv_img);
                iv_img.setVisibility(View.VISIBLE);
            } else {
                iv_img.setVisibility(View.GONE);
            }
            tv_title.setText(Html.fromHtml(getItem(position).getTitle()));
            if (TextUtils.isEmpty(getItem(position).getDesc())) {
                tv_desc.setVisibility(View.GONE);
                tv_title.setSingleLine(false);
            } else {
                tv_desc.setVisibility(View.VISIBLE);
                tv_title.setSingleLine(true);
                String desc = Html.fromHtml(getItem(position).getDesc()).toString();
//                desc = StringUtils.removeAllBank(desc, 2);
                tv_desc.setText(desc);
            }
//            holder.tv_chapter_name.setText(Html.fromHtml(formatChapterName(item.getSuperChapterName(), item.getChapterName())));
//            if (item.isCollect()) {
//                cv_collect.setChecked(true);
//            } else {
//                cv_collect.setChecked(false);
//            }
            tv_author.setOnClickListener(new OnClickListener2() {
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
            itemView.setOnClickListener(new OnClickListener2() {
                @Override
                public void onClick2(View v) {
//                    WebActivity.start(v.getContext(), item);
                }
            });
        }
    }
}
