package com.transcendence.wan.module.hierarchy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.transcendence.core.adapter.GoweiiFragmentPagerAdapter;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseFragment;
import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;
import com.transcendence.wan.module.knowledge.fragment.NaviFragment;
import com.transcendence.wan.module.knowledge.fragment.TreeFragment;

/**
 * @author Joephone on 2021/2/19 9:58
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc 体系
 * @Edition 1.0
 * @EditionHistory
 */

public class HierarchyFragment extends WanBaseFragment {

    private ViewPager mVp;
    private GoweiiFragmentPagerAdapter adapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_navi_knowledge;
    }

    @Nullable
    @Override
    protected WanBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        initVP();
    }

    @Override
    protected void loadData() {

    }


    private void initVP() {
        mVp = findViewById(R.id.vp);
        //预加载
        mVp.setOffscreenPageLimit(1);
        adapter = new GoweiiFragmentPagerAdapter(getChildFragmentManager());
        adapter.setFragments(TreeFragment.create("体系"));
        mVp.setAdapter(adapter);
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Parameter 1.
     * @return A new instance of fragment .
     */
    public static HierarchyFragment newInstance(String title) {
        HierarchyFragment fragment = new HierarchyFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

}
