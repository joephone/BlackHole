package com.transcendence.wan.module.knowledge.adapter;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.transcendence.wan.R;
import com.transcendence.wan.module.knowledge.model.TreeBean;

import java.util.List;

/**
 * @Author Joephone on 2020/5/9 5:41
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class TreeRightAdapter extends BaseQuickAdapter<TreeBean.ChildrenBean,BaseViewHolder>{

    public TreeRightAdapter(@Nullable List<TreeBean.ChildrenBean> data) {
        super(R.layout.fragment_navi_tree_right_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TreeBean.ChildrenBean item) {
        helper.setText(R.id.tv_name, TextUtils.isEmpty(item.getName())?"":item.getName());
    }
}
