package com.transcendence.wan.module.knowledge.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.transcendence.blackhole.adapter.GoweiiFragmentPagerAdapter;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseFragment;
import com.transcendence.wan.module.knowledge.model.NaviBean;
import com.transcendence.wan.module.knowledge.presenter.KnowledgeTwoPresenter;
import com.transcendence.wan.module.knowledge.view.KnowledgeTwoView;

import java.util.List;

/**
 * @author Joephone on 2019/12/17 11:51
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class NaviFragment extends WanBaseFragment<KnowledgeTwoPresenter> implements KnowledgeTwoView {

    private GoweiiFragmentPagerAdapter adapter;
    private RecyclerView mRv;
    private FrameLayout mFlContent;


    public static NaviFragment create(String title) {
        NaviFragment fragment = new NaviFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_navi_knowledge_two;
    }

    @Override
    protected KnowledgeTwoPresenter initPresenter() {
        return new KnowledgeTwoPresenter();
    }

    @Override
    protected void initView() {
        mRv = findViewById(R.id.rv);
        mFlContent = findViewById(R.id.fl_content);
    }

    @Override
    protected void loadData() {
        presenter.getNavi();
    }


    @Override
    public void getNaviSuc(int code, List<NaviBean> data) {

    }

    @Override
    public void getNaviFail(int code) {

    }
}
