package com.transcendence.greenstar.demo.tabvprv.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.transcendence.greenstar.demo.tabvprv.fragment.TabFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // 返回对应的 Fragment
        return TabFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        // Tab 的数量
        return 3;
    }
}