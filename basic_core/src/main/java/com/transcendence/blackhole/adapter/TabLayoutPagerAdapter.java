package com.transcendence.blackhole.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Joephone on 2019/2/28 18:03
 * E-Mail Address：joephonechen@gmail.com
 */

public class TabLayoutPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private List<String> mTitleList;

    public TabLayoutPagerAdapter(FragmentManager fm, List<Fragment> list, List<String> mTitleList) {
        super(fm);
        this.list = list;
        this.mTitleList = mTitleList;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }

}