package com.transcendence.greenstar.demo.tabvprv.act;

import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.transcendence.core.base.activity.AppAc;
import com.transcendence.greenstar.R;
import com.transcendence.greenstar.demo.tabvprv.adapter.ViewPagerAdapter;

public class TabVpRvActivity extends AppAc {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo_tabvprv;
    }

    @Override
    protected void initView() {
        setTitle("tablayout + viewpager + rv");
        // 初始化视图
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        // 设置 ViewPager 的 Adapter
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        // 将 TabLayout 和 ViewPager 绑定
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText("Tab " + (position + 1));
        }).attach();
    }
}
