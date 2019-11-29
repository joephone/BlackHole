package com.transcendence.wan.search.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.transcendence.wan.R;
import com.transcendence.wan.base.act.WanBaseActivity;
import com.transcendence.wan.search.fragment.SearchHistoryFragment;
import com.transcendence.wan.search.fragment.SearchResultFragment;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wan_search);

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
}
