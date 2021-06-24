package com.transcendence.wan.module.knowledge.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.transcendence.core.adapter.GoweiiFragmentPagerAdapter;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseFragment;
import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;

/**
 * @author Joephone on 2019/11/27 11:50
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class KnowledgeNaviFragment extends WanBaseFragment {

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
        adapter.setFragments(TreeFragment.create("知识一"), NaviFragment.create("知识二"));
        mVp.setAdapter(adapter);
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Parameter 1.
     * @return A new instance of fragment .
     */
    public static KnowledgeNaviFragment newInstance(String title) {
        KnowledgeNaviFragment fragment = new KnowledgeNaviFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

}
