package com.transcendence.blackhole.base;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.widget.TextView;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.mvp.BaseActivity;
import com.transcendence.blackhole.fragment.BlankFragment;
import com.transcendence.blackhole.utils.Logs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Joephone on 2019/5/14 16:13
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc  仿微信主界面
 */

public class WeChatMainActivity extends BaseActivity {
    private ViewPager mViewPager;
    private TextView tvTabOne;
    private TextView tvTabTwo;
    private TextView tvTabThree;
    private TextView tvTabFour;
    private List<String> titleList = new ArrayList<>(Arrays.asList("一","二","三","四"));
    private SparseArray<Fragment> mFragments  = new SparseArray<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_wechat_main;
    }

    @Override
    public void initView() {
        mViewPager = findViewById(R.id.viewPager);
        tvTabOne = findViewById(R.id.tvTabOne);
        tvTabTwo = findViewById(R.id.tvTabTwo);
        tvTabThree = findViewById(R.id.tvTabThree);
        tvTabFour = findViewById(R.id.tvTabFour);
    }

    @Override
    public void init() {
        initVP();
    }

    private void initVP() {
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return BlankFragment.newInstance(titleList.get(i));
            }

            @Override
            public int getCount() {
                return titleList.size();
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                BlankFragment blankFragment = (BlankFragment) super.instantiateItem(container, position);
                mFragments.put(position,blankFragment);
                return blankFragment;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                mFragments.remove(position);
                super.destroyItem(container, position, object);
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixel) {
                Logs.d("onPageScrolled pos="+position+",positionOffset="+positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                Logs.d("onPageSelected pos="+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
