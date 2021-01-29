package com.transcendence.core.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joephone on 2018/10/22 15:43
 * E-Mail Address：joephonechen@gmail.com
 */

public class MyPageAdapter extends FragmentPagerAdapter {
    private SparseArray<String> mFragmentTags = new SparseArray<>();
    private List<String> titles = new ArrayList<>();
    private List<Fragment> list = new ArrayList<>();

    public MyPageAdapter(FragmentManager fm) {
        super(fm);
    }
    public void setFragments(List<Fragment> list) {
        this.list = list;
    }

    /**
     * 设置标题
     *
     * @param titles
     */
    public void setTitles(List<String> titles) {
        this.titles = titles;
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
    public Object instantiateItem(ViewGroup container, int position) {
        Object obj = super.instantiateItem(container, position);
        if (obj instanceof Fragment) {
            Fragment f = (Fragment) obj;
            String tag = f.getTag();
            mFragmentTags.put(position, tag);
        }
        return obj;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
