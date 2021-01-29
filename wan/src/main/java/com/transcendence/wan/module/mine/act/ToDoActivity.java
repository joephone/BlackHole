package com.transcendence.wan.module.mine.act;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.transcendence.core.fragment.BlankFragment;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseActivity;
import com.transcendence.wan.module.mine.adapter.FragmentTabAdapter;
import com.transcendence.wan.module.mine.presenter.ToDoPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author Joephone on 2020/5/10 9:16
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class ToDoActivity extends WanBaseActivity<ToDoPresenter> implements FragmentTabAdapter.OnTabCheckedListener {

    private RadioGroup mRadioGroup;
    private ViewPager mVp;
    private List<Fragment> fragmentList = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_todo;
    }

    @Nullable
    @Override
    protected ToDoPresenter initPresenter() {
        return new ToDoPresenter();
    }

    @Override
    protected void initView() {
        mRadioGroup = findViewById(R.id.radio_group);
//        mVp = findViewById(R.id.vp);

        initTabsAndVp();
    }

    private void initTabsAndVp() {
        fragmentList.add(BlankFragment.newInstance("计划"));
        fragmentList.add(BlankFragment.newInstance("完成"));

        ((RadioButton)mRadioGroup.getChildAt(0)).setChecked(true);

        FragmentTabAdapter pagerAdapter = new FragmentTabAdapter(this, fragmentList, R.id.fl_content, mRadioGroup, 0);
        pagerAdapter.setOnTabCheckedListener(this);
    }

    @Override
    public void onTabChecked(RadioGroup radioGroup, int checkedId, int index) {

    }


    private String[] titles = {"计划","完成"};
    private int[] colors = {R.color.login_bg_end_1,R.color.tablayout_tv_gray};
    private int[] mipmaps = {R.drawable.ic_tabbar_order,R.drawable.ic_tabbar_mainframe};
    private int[] mipmaphls = {R.drawable.ic_tabbar_orderhl,R.drawable.ic_tabbar_mainframehl};




    @Override
    protected void loadData() {

    }




    public static void start(Context context) {
        Intent intent = new Intent(context, ToDoActivity.class);
        context.startActivity(intent);
    }


}
