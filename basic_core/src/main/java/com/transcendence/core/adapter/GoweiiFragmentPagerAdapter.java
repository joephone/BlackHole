package com.transcendence.core.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @author CuiZhen
 * @date 2019/5/11
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */

public class GoweiiFragmentPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] mFragments = null;
    private String[] mTitles = null;

    public GoweiiFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * 添加fragments
     * @param fragments
     */
    public void setFragments(Fragment...fragments){
        mFragments = fragments;
    }

    /**
     * 添加标题
     * @param titles
     */
    public void setTitles(String... titles){
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments[position];
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }
}
