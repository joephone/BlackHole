package com.transcendence.structure;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.transcendence.blackhole.adapter.MyFragmentPagerAdapter;
import com.transcendence.blackhole.fragment.BlankFragment;
import com.transcendence.blackhole.widget.viewpager.NoScrollViewPager;
import com.transcendence.structure.fragment.MagazineFragement;
import com.transcendence.structure.fragment.StructureMineFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joephone
 */
public class StructureMainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView mBottomNavigationView;
    private NoScrollViewPager mViewPager;

    private List<Fragment> mFragments;
    private FragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_structure_main);

        mBottomNavigationView = findViewById(R.id.bvHome);
        mViewPager = findViewById(R.id.viewPager);


        mFragments = new ArrayList<>();
        mFragments.add(new MagazineFragement());
        mFragments.add(BlankFragment.newInstance("二"));
        mFragments.add(BlankFragment.newInstance("三"));
        mFragments.add(StructureMineFragment.newInstance("四"));
        // init view pager
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mAdapter);

        // 不使用图标默认变色
        mBottomNavigationView.setItemIconTintList(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.zuimei_home_one:
//                mViewPager.setCurrentItem(0);
//                return true;
//            case R.id.zuimei_home_two:
//                mViewPager.setCurrentItem(1);
//                return true;
//            case R.id.zuimei_home_three:
//                mViewPager.setCurrentItem(2);
//                return true;
//            case R.id.zuimei_home_four:
//                mViewPager.setCurrentItem(3);
//                return true;
        }
        return false;
    }


}
