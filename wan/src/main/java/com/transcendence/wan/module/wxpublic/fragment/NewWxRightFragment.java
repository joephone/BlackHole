package com.transcendence.wan.module.wxpublic.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseFragment;
import com.transcendence.wan.module.knowledge.adapter.TreeRightAdapter;
import com.transcendence.wan.module.knowledge.model.TreeBean;
import com.transcendence.wan.module.knowledge.presenter.TreePresenter;
import com.transcendence.wan.module.wxpublic.model.WxChapterBean;
import com.transcendence.wan.module.wxpublic.presenter.NaviWxPublicPresenter;

/**
 * @author Joephone on 2020/5//9 5:50
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class NewWxRightFragment extends WanBaseFragment<NaviWxPublicPresenter>  {

    private TreeRightAdapter mAdapter;
    private RecyclerView mRv;
    private FrameLayout mFlContent;
    private TreeBean mData;

    public static NewWxRightFragment create(WxChapterBean data) {
        NewWxRightFragment fragment = new NewWxRightFragment();
        Bundle args = new Bundle();
        args.putSerializable("list", data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_navi_new_wx_right;
    }

    @Override
    protected NaviWxPublicPresenter initPresenter() {
        return new NaviWxPublicPresenter();
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
