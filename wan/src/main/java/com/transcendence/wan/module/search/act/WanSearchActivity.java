package com.transcendence.wan.module.search.act;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseActivity;
import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;
import com.transcendence.wan.module.search.fragment.SearchHistoryFragment;
import com.transcendence.wan.module.search.fragment.SearchResultFragment;

/**
 * @Author Joephone on 2019/9/6 14:42
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class WanSearchActivity extends WanBaseActivity {

    private SearchHistoryFragment mSearchHistoryFragment;
    private SearchResultFragment mSearchResultFragment;
    private FrameLayout mFl;
    private FragmentManager mFm;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wan_search;
    }

    @Nullable
    @Override
    protected WanBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        mFl = findViewById(R.id.fl);
        mFm = getSupportFragmentManager();

        Fragment searchHistoryFragment = mFm.findFragmentByTag(SearchHistoryFragment.class.getName());
        if (searchHistoryFragment == null) {
            mSearchHistoryFragment = SearchHistoryFragment.getInstance();
        } else {
            mSearchHistoryFragment = (SearchHistoryFragment) searchHistoryFragment;
        }
        Fragment searchResultFragment = mFm.findFragmentByTag(SearchResultFragment.class.getName());
        if (searchResultFragment == null) {
            mSearchResultFragment = SearchResultFragment.getInstance();
        } else {
            mSearchResultFragment = (SearchResultFragment) searchResultFragment;
        }

        FragmentTransaction t = mFm.beginTransaction();
        t.add(R.id.fl,mSearchHistoryFragment,SearchHistoryFragment.class.getName());
        t.add(R.id.fl,mSearchResultFragment,SearchResultFragment.class.getName());

        t.show(mSearchHistoryFragment);
        t.hide(mSearchResultFragment);
        t.commit();
    }

    @Override
    protected void loadData() {

    }


    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void clearLoading() {

    }
}
