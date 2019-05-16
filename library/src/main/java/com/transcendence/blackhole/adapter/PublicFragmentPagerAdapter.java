package com.transcendence.blackhole.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author Joephone on 2019/5/15 16:01
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 */

public class PublicFragmentPagerAdapter extends FragmentPagerAdapter{
    private List<?> mFragments;
    private List<String> mTitleList;

    /**
     * 普通，主页使用
     */
    public PublicFragmentPagerAdapter(FragmentManager fm, List<?> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    /**
     * 接收首页传递的标题
     */
    public PublicFragmentPagerAdapter(FragmentManager fm, List<?> fragments, List<String> titleList) {
        super(fm);
        this.mFragments = fragments;
        this.mTitleList = titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments ==null ? 0:mFragments.size();
    }


//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        return fragment;
//    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) mFragments.get(position));
    }
}
