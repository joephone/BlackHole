package com.transcendence.freeland.ui.rv.mogu.act;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.freeland.R;
import com.transcendence.freeland.ui.rv.mogu.fragment.ListFragment;
import com.transcendence.freeland.ui.rv.mogu.ui.AutoHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

public class MoguMainActivityV2 extends AppAc {
    private static final int NUM_FRAGMENT = 10;
    private ViewPager viewPager;
    private AutoHorizontalScrollView tab_sv;
    private LinearLayout tabLayouts;

    private List<ListFragment> fragmentList = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private List<TextView> textViews = new ArrayList<>();



    @Override
    protected int getLayoutId() {
        return R.layout.activity_ui_rv_mogu_main_v2;
    }



    private void initFragments() {
        for (int i = 0;i < NUM_FRAGMENT;i++){
            titles.add("title"+i);
            ListFragment fragment = new ListFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("type",i);
            fragment.setArguments(bundle);
            fragmentList.add(fragment);

            LinearLayout tabLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_ui_rv_mogu_tab_item,null);
            final TextView textView = (TextView)tabLayout.findViewById(R.id.tv_tab);
            textView.setText(titles.get(i));
            final int id = i;
            tabLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setSelector(id);
                }
            });
            tabLayouts.addView(tabLayout);
            textViews.add(textView);
        }
    }


    protected void initView() {
        setTitle("仿蘑菇街V2");
        viewPager = findViewById(R.id.id_viewpager);
        tab_sv = findViewById(R.id.id_horizontalmenu);
        tabLayouts = findViewById(R.id.tab_layout);

        initFragments();

        setSelector(0);
        viewPager.setCurrentItem(0);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setSelector(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return NUM_FRAGMENT;
            }

            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }
        });


    }

    /**
     * 选中效果
     * @param position
     */
    private void setSelector(final int position) {
        for (int i = 0;i < NUM_FRAGMENT; i++){
            if (position == i){
                viewPager.setCurrentItem(position);
                tab_sv.resetScrollWidth(position);
                textViews.get(i).setBackgroundResource(R.mipmap.activity_ui_rv_mogu_bg_nav_contacts);
            }else {
                textViews.get(i).setBackgroundResource(R.color.transparent);
            }
        }
    }



}
