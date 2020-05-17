package com.transcendence.wan.module.mine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.transcendence.blackhole.utils.L;
import com.transcendence.ui.scroll.RollbackScrollView;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseFragment;
import com.transcendence.wan.listener.OnMyItemClickListener;
import com.transcendence.wan.module.mine.adapter.MineAdapter;
import com.transcendence.wan.module.mine.model.MineBean;
import com.transcendence.wan.module.project.presenter.ScrollFragmentPresenter;
import com.transcendence.wan.module.project.view.ScrollFragmentView;

import java.util.List;

/**
 * @Author Joephone on 2020/5/6 18:01
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class ScrollFragment extends WanBaseFragment<ScrollFragmentPresenter> implements ScrollFragmentView,RollbackScrollView.OnRollBackListener{
    private static final String ARG_SHOW_TEXT = "text";

    private RollbackScrollView mRollbackScrollView;
    private RecyclerView mRv;
    private MineAdapter mAdapter;

    public static ScrollFragment newInstance(String title) {
        ScrollFragment fragment = new ScrollFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_navi_six;
    }

    @Nullable
    @Override
    protected ScrollFragmentPresenter initPresenter() {
        return new ScrollFragmentPresenter();
    }

    @Override
    protected void initView() {
        mRollbackScrollView = findViewById(R.id.rollBackScrollView);
        mRollbackScrollView.setListener(this);
        mRv = findViewById(R.id.rv);

        LinearLayoutManager manager = new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(manager);


    }

    @Override
    protected void loadData() {
        presenter.inflateItem(getContext());
    }

    @Override
    public void onRefresh() {
        L.d("刷新");
    }

    @Override
    public void inflateItemSuc(List<MineBean> list) {
        mAdapter = new MineAdapter(list);
        mRv.setAdapter(mAdapter);
        mAdapter.setListener(new OnMyItemClickListener() {
            @Override
            public void onItemClick(int position) {
                L.d("我的积分");
            }
        });
    }

//    @Override
//    public void OnItemClick(int position) {
//        switch (position){
//            case 0:
//
//                break;
//        }
//    }
}
