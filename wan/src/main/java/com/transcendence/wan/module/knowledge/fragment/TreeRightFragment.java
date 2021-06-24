package com.transcendence.wan.module.knowledge.fragment;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseFragment;
import com.transcendence.wan.module.knowledge.adapter.TreeRightAdapter;
import com.transcendence.wan.module.knowledge.model.TreeBean;
import com.transcendence.wan.module.knowledge.presenter.TreePresenter;

/**
 * @author Joephone on 2020/5//9 5:50
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class TreeRightFragment extends WanBaseFragment<TreePresenter>  {

    private TreeRightAdapter mAdapter;
    private RecyclerView mRv;
    private FrameLayout mFlContent;
    private TreeBean mData;

    public static TreeRightFragment create(TreeBean data) {

        TreeRightFragment fragment = new TreeRightFragment();
        Bundle args = new Bundle();
        args.putSerializable("list", data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_navi_tree_right;
    }

    @Override
    protected TreePresenter initPresenter() {
        return new TreePresenter();
    }

    @Override
    protected void initView() {
        mRv = findViewById(R.id.rv);
        mFlContent = findViewById(R.id.fl_content);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRv.setLayoutManager(manager);

        if (getArguments() != null) {
            mData = (TreeBean) getArguments().getSerializable("list");
//            L.d("WxArticleListFragment onCreateView"+mList.size());
            if (mData != null) {
                mAdapter = new TreeRightAdapter(mData.getChildren());
                mRv.setAdapter(mAdapter);
            }
        }


    }

    @Override
    protected void loadData() {

    }




}
