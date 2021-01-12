package com.transcendence.wan.module.home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.transcendence.blackhole.adapter.GoweiiFragmentPagerAdapter;
import com.transcendence.blackhole.fragment.BlankFragment;
import com.transcendence.blackhole.utils.StringUtils;
import com.transcendence.blackhole.widget.custom.TabView;
import com.transcendence.wan.R;
import com.transcendence.wan.module.mine.fragment.FiveFragment;
import com.transcendence.wan.module.mine.fragment.MineFragment;
import com.transcendence.wan.module.wxpublic.fragment.NaviWxPublicFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joephone on 2019/11/27 11:50
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class MainFragment extends Fragment implements View.OnClickListener{

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Parameter 1.
     * @return A new instance of fragment .
     */
    public static MainFragment newInstance(String title) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, title);
        fragment.setArguments(args);
        return fragment;
    }


    TabView mTabOne;
    TabView mTabTwo;
    TabView mTabThree;
    TabView mTabFour;
    TabView mTabFive;

    private List<TabView> mTabs = new ArrayList<>();
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";

    private ViewPager mVpMain;
    private GoweiiFragmentPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_navi_main, container, false);
//        L.d("MainFragment onCreateView");
        initView(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initVP();
        initTabs();
    }


    private void initView(View rootView) {
        mVpMain = rootView.findViewById(R.id.vp);
        mTabOne = rootView.findViewById(R.id.tabOne);
        mTabTwo = rootView.findViewById(R.id.tabTwo);
        mTabThree = rootView.findViewById(R.id.tabThree);
        mTabFour = rootView.findViewById(R.id.tabFour);
//        mTabFive = rootView.findViewById(R.id.tabFive);
        mTabOne.setOnClickListener(this);
        mTabTwo.setOnClickListener(this);
        mTabThree.setOnClickListener(this);
        mTabFour.setOnClickListener(this);
//        mTabFive.setOnClickListener(this);
    }


    private void initVP() {
        //预加载   , ScrollFragment.newInstance("项目")
        mVpMain.setOffscreenPageLimit(5);
        adapter = new GoweiiFragmentPagerAdapter(getChildFragmentManager());
        adapter.setFragments(HomeFragment.newInstance("主页面")
                , BlankFragment.newInstance("体系")
                , NaviWxPublicFragment.create()
                , MineFragment.newInstance("我的"));
        mVpMain.setAdapter(adapter);


        mVpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * @param position
             * @param positionOffset  当前页面偏移的百分比
             * @param positionOffsetPixel 当前页面偏移的像素位置
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixel) {
//                L.d("onPageScrolled pos=" + position + ",positionOffset=" + positionOffset);
                if(positionOffset>0 && position< mTabs.size()){
                    TabView left = mTabs.get(position);
                    TabView right = mTabs.get(position+1);
                    right.setProgress(positionOffset);
                    left.setProgress(1-positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {
//                L.d("onPageSelected pos=" + position);
            }

            @Override
            public void onPageScrollStateChanged(int position) {
//                L.d("onPageScrollStateChanged pos=" + position);
            }
        });
    }



    private void initTabs() {
        mTabOne.setIconAndText(R.drawable.ic_home_white_24dp,R.drawable.ic_home_github_green_24dp,StringUtils.getString(R.string.wan_tab_one));
        mTabTwo.setIconAndText(R.drawable.ic_tixi_white_24dp,R.drawable.ic_tixi_githubgreen_24dp,StringUtils.getString(R.string.wan_tab_two));
        mTabThree.setIconAndText(R.drawable.ic_wx_public_white_24dp,R.drawable.ic_wx_public_githubgreen_24dp,StringUtils.getString(R.string.wan_tab_three));
//        mTabFour.setIconAndText(R.drawable.ic_bottom_bar_project,R.mipmap.ic_navi_contacts_press,StringUtils.getString(R.string.wan_tab_four));
        mTabFour.setIconAndText(R.drawable.ic_person_white_24dp,R.drawable.ic_person_githubgreen_24dp,StringUtils.getString(R.string.wan_tab_five));
        mTabs.add(mTabOne); mTabs.add(mTabTwo);mTabs.add(mTabThree);mTabs.add(mTabFour);  //mTabs.add(mTabFive);
        setCurrentTabs(0);
    }

    private void setCurrentTabs(int position) {
        for (int i = 0; i < mTabs.size(); i++) {
            if(i==position){
                mTabs.get(i).setProgress(1);
            }else {
                mTabs.get(i).setProgress(0);
            }
        }
        mVpMain.setCurrentItem(position,false);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tabOne) {
            setCurrentTabs(0);
        } else if (i == R.id.tabTwo) {
            setCurrentTabs(1);

        } else if (i == R.id.tabThree) {
            setCurrentTabs(2);

        } else if (i == R.id.tabFour) {
            setCurrentTabs(3);

        }
//        else if (i == R.id.tabFive) {
//            setCurrentTabs(4);
//        }
    }
}
