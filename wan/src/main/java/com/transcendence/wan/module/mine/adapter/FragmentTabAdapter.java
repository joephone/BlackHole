package com.transcendence.wan.module.mine.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

public class FragmentTabAdapter implements RadioGroup.OnCheckedChangeListener {
    private List<Fragment> fragments; // 一个tab页面对应一个Fragment
    private RadioGroup radioGroups; // 用于切换tab
    private FragmentActivity fragmentActivity; // Fragment所属的Activity
    private int fragmentContentId; // Activity中所要被替换的区域的id
    private int currentTab;
    private OnTabCheckedListener onTabCheckedListener;
    private long currentTime;

    public FragmentTabAdapter(FragmentActivity fragmentActivity, List<Fragment> fragments, int fragmentContentId,
                              RadioGroup rgs, int currentId) {

        this.fragments = fragments;
        this.radioGroups = rgs;
        this.fragmentActivity = fragmentActivity;
        this.fragmentContentId = fragmentContentId;

        // 默认显示第一页
        FragmentTransaction ft = this.fragmentActivity.getSupportFragmentManager().beginTransaction();
        ft.add(this.fragmentContentId, this.fragments.get(currentId));
        ft.commit();

        this.radioGroups.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        if (System.currentTimeMillis() - currentTime < 100) {

            ((RadioButton) radioGroups.getChildAt(0)).setChecked(true);
            Fragment fragment = fragments.get(0);
            fragment.onResume();
            return;
        }
        currentTime = System.currentTimeMillis();

        for (int i = 0; i < radioGroups.getChildCount(); i++) {

            int childId = radioGroups.getChildAt(i).getId();

            if (childId == checkedId) {
                Fragment fragment = fragments.get(i);
                FragmentTransaction ft = obtainFragmentTransaction();
                getCurrentFragment().onPause(); // 暂停当前tab

                if (fragment.isAdded()) {
                    fragment.onResume(); // 启动目标tab的onResume()
                } else {
                    ft.add(fragmentContentId, fragment);
                }

                showTab(i); // 显示目标tab
                ft.commitAllowingStateLoss();

                // 如果设置了切换tab额外功能功能接口
                if (null != onTabCheckedListener) {
                    onTabCheckedListener.onTabChecked(radioGroup, checkedId, i);
                }
                return;
            }
        }

    }

    private void showTab(int idx) {
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);

            if (fragment.isAdded()) {
                FragmentTransaction ft = obtainFragmentTransaction();
                if (idx == i) {
                    ft.show(fragment);
                } else {
                    ft.hide(fragment);
                }
                ft.commitAllowingStateLoss();
            }

        }
        currentTab = idx; // 更新目标tab为当前tab
    }

    public void switchTo(int index) {
        RadioButton childAt = (RadioButton) radioGroups.getChildAt(index);
        childAt.setChecked(true);
    }

    private FragmentTransaction obtainFragmentTransaction() {
        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
        return ft;
    }

    public int getCurrentTab() {
        return currentTab;
    }

    public Fragment getCurrentFragment() {
        return fragments.get(currentTab);
    }

    public OnTabCheckedListener getOnTabCheckedListener() {
        return onTabCheckedListener;
    }

    public void setOnTabCheckedListener(OnTabCheckedListener onTabCheckedListener) {
        this.onTabCheckedListener = onTabCheckedListener;
    }

    public interface OnTabCheckedListener {
        void onTabChecked(RadioGroup radioGroup, int checkedId, int index);
    }
}
