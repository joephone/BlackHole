package com.transcendence.blackhole.demo.weixinnavigation.act;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;

import com.transcendence.blackhole.R;
import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.core.fragment.BlankFragment;
import com.transcendence.blackhole.main.fragment.HomeFragment;
import com.transcendence.core.utils.L;
import com.transcendence.core.utils.StringUtils;
import com.transcendence.core.widget.custom.TabView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Joephone on 2019/8/12 15:59
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class WeixinHomeActivity extends TitleBarActivity implements View.OnClickListener{

    ViewPager mVpMain;
    TabView mTabOne;
    TabView mTabTwo;
    TabView mTabThree;
    TabView mTabFour;

    private List<String> mTitleList = new ArrayList<>(Arrays.asList(StringUtils.getString(R.string.tab_one)
            ,StringUtils.getString(R.string.tab_two)
            ,StringUtils.getString(R.string.tab_three)
            ,StringUtils.getString(R.string.tab_four)));
    private SparseArray<Fragment> mFragments = new SparseArray<>();
    private List<TabView> mTabs = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.activity_navi_weixin_home;
    }

    @Override
    public void init() {
        setTitle(false,false);
        initViews();

        initTabs();

        initVp();


    }

    private void initViews() {
        mVpMain = findViewById(R.id.vpMain);
        mTabOne = findViewById(R.id.tabOne);
        mTabTwo = findViewById(R.id.tabTwo);
        mTabThree = findViewById(R.id.tabThree);
        mTabFour = findViewById(R.id.tabFour);
        mTabOne.setOnClickListener(this);
        mTabTwo.setOnClickListener(this);
        mTabThree.setOnClickListener(this);
        mTabFour.setOnClickListener(this);
    }


    private void initTabs() {
        mTabOne.setIconAndText(R.mipmap.ic_navi_message_normal,R.mipmap.ic_navi_message_press,"消息");
        mTabTwo.setIconAndText(R.mipmap.ic_navi_contacts_normal,R.mipmap.ic_navi_contacts_press,"联系人");
        mTabThree.setIconAndText(R.mipmap.ic_navi_discovery_normal,R.mipmap.ic_navi_discovery_press,"发现");
        mTabFour.setIconAndText(R.mipmap.ic_navi_me_normal,R.mipmap.ic_navi_me_press,"我的");
        mTabs.add(mTabOne); mTabs.add(mTabTwo);mTabs.add(mTabThree);mTabs.add(mTabFour);
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


    private void initVp() {
        mVpMain.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                if(i==0){
                    return HomeFragment.newInstance("home");
                }
                Fragment fragment = BlankFragment.newInstance(i+"");
                return fragment;
            }

            @Override
            public int getCount() {
                return mTitleList.size();
            }
        });

        mVpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixel) {
                L.d("onPageScrolled pos=" + position + ",positionOffset=" + positionOffset);
                if(positionOffset>0){
                    TabView left = mTabs.get(position);
                    TabView right = mTabs.get(position+1);
                    right.setProgress(positionOffset);
                    left.setProgress(1-positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {
                L.d("onPageSelected pos=" + position);
            }

            @Override
            public void onPageScrollStateChanged(int position) {
//                L.d("onPageScrollStateChanged pos=" + position);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tabOne:
                setCurrentTabs(0);
                break;
            case R.id.tabTwo:
                setCurrentTabs(1);
                break;
            case R.id.tabThree:
                setCurrentTabs(2);
                break;
            case R.id.tabFour:
                setCurrentTabs(3);
                break;
        }
    }
}
