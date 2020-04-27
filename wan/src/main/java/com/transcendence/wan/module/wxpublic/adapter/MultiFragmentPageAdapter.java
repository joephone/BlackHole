package com.transcendence.wan.module.wxpublic.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Joephone on 2020/4/26 17:13
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class MultiFragmentPageAdapter extends FragmentPagerAdapter {

    private SparseArray<String> mFragmentTags = new SparseArray<>();
    private List<String> mTitleList = new ArrayList<>();
    private List<Fragment> mFragmentList = new ArrayList<>();

    public MultiFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(List<Fragment> list) {
        this.mFragmentList = list;
    }

    public void setTitles(List<String> list) {
        this.mTitleList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList == null?0 :mFragmentList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //return super.instantiateItem(container, position);
        Object obj = super.instantiateItem(container,position);
        if(obj instanceof Fragment){
            Fragment f = (Fragment) obj;
            String tag = f.getTag();
            mFragmentTags.put(position,tag);
        }
        return obj;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
