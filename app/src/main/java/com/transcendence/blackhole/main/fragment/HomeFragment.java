package com.transcendence.blackhole.main.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.fragment.BlankFragment;
import com.transcendence.blackhole.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joephone on 2019/8/13 17:59
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class HomeFragment extends BlankFragment {

    private List<Fragment> mFragmentList = new ArrayList();
    private ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        Logs.logE("onCreateView");
        View view = inflater.inflate(R.layout.fragment_main_home, container, false);
        init(view);
        return view;

    }

    private void init(View view) {
        initView(view);
        mFragmentList = new ArrayList<>();
        mFragmentList.add(BlankFragment.newInstance(StringUtils.getString(R.string.home_one)));
        mFragmentList.add(BlankFragment.newInstance(StringUtils.getString(R.string.home_two)));
        mFragmentList.add(BlankFragment.newInstance(StringUtils.getString(R.string.home_three)));
        mFragmentList.add(BlankFragment.newInstance(StringUtils.getString(R.string.home_four)));
        mFragmentList.add(BlankFragment.newInstance(StringUtils.getString(R.string.home_five)));
        mFragmentList.add(BlankFragment.newInstance(StringUtils.getString(R.string.home_six)));
        // init view pager
//        mAdapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager(), mFragments);
//        mViewPager.setAdapter(mAdapter);
        mViewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragmentList.get(i);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        });
    }

    private void initView(View view) {
        mViewPager = view.findViewById(R.id.viewPager);
    }
}
