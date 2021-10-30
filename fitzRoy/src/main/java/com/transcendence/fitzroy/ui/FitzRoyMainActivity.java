package com.transcendence.fitzroy.ui;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.transcendence.core.adapter.GoweiiFragmentPagerAdapter;
import com.transcendence.core.base.app.LibApplication;
import com.transcendence.core.utils.L;
import com.transcendence.core.utils.StringUtils;
import com.transcendence.core.utils.gps.GPSUtils;
import com.transcendence.core.widget.custom.TabView;
import com.transcendence.fitzroy.app.BaseActivity;
import com.transcendence.fitzroy.R;
import com.transcendence.fitzroy.fragment.GPSFragment;
import com.transcendence.fitzroy.fragment.GirlFragment;
import com.transcendence.fitzroy.fragment.OtherFragment;
import com.transcendence.permissions.OnPermissionCallback;
import com.transcendence.permissions.PermissionPool;
import com.transcendence.permissions.Permissions;

import java.util.ArrayList;
import java.util.List;

public class FitzRoyMainActivity extends BaseActivity implements View.OnClickListener {
    private Handler mHandler = new Handler();
    private GPSUtils gpsUtils;
    private Location location;

    private TabView mTabOne,mTabTwo,mTabThree;

    private List<TabView> mTabs = new ArrayList<>();
    private ViewPager mVp;
    /**
     * Goweii的vpAdapter
     */
    private GoweiiFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("福利社");


        initView();
        initVP();
        initTabs();

        requestWritePermission();
    }


    private void initVP() {
        mVp = findViewById(R.id.vp);
        //预加载
        mVp.setOffscreenPageLimit(1);
        adapter = new GoweiiFragmentPagerAdapter(getSupportFragmentManager());
        adapter.setTitles("主页面", "定位", "美女");
        adapter.setFragments(
                GirlFragment.newInstance("主页面"),
                GPSFragment.newInstance("定位"),
                OtherFragment.newInstance("美女"));

        mVp.setAdapter(adapter);
        mVp.setCurrentItem(0);

        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * @param position
             * @param positionOffset  当前页面偏移的百分比
             * @param positionOffsetPixel 当前页面偏移的像素位置
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixel) {
//                L.d("onPageScrolled pos=" + position + ",positionOffset=" + positionOffset);
                if (positionOffset > 0 && position < mTabs.size()) {
                    TabView left = mTabs.get(position);
                    TabView right = mTabs.get(position + 1);
                    right.setProgress(positionOffset);
                    left.setProgress(1 - positionOffset);
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

    private void initView() {
        mVp = findViewById(R.id.vp);
        mTabOne = findViewById(R.id.tabOne);
        mTabTwo = findViewById(R.id.tabTwo);
        mTabThree = findViewById(R.id.tabThree);
//        mTabFive = rootView.findViewById(R.id.tabFive);
        mTabOne.setOnClickListener(this);
        mTabTwo.setOnClickListener(this);
        mTabThree.setOnClickListener(this);
//        mTabFive.setOnClickListener(this);
    }

    private void initTabs() {
        mTabOne.setIconAndText(R.drawable.ic_tab_home_white_24dp, R.drawable.ic_tab_home_github_green_24dp, StringUtils.getString(R.string.wan_tab_one));
        mTabTwo.setIconAndText(R.drawable.ic_tab_person_white_24dp, R.drawable.ic_tab_person_githubgreen_24dp, StringUtils.getString(R.string.wan_tab_two));
        mTabThree.setIconAndText(R.drawable.ic_tab_person_white_24dp, R.drawable.ic_tab_person_githubgreen_24dp, StringUtils.getString(R.string.wan_tab_five));
        mTabs.add(mTabOne);mTabs.add(mTabTwo);mTabs.add(mTabThree);

        setCurrentTabs(0);
    }

    private void setCurrentTabs(int position) {
        for (int i = 0; i < mTabs.size(); i++) {
            if (i == position) {
                mTabs.get(i).setProgress(1);
            } else {
                mTabs.get(i).setProgress(0);
            }
        }
        mVp.setCurrentItem(position, false);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main_fitz_roy;
    }


    public static void start(Context context) {
        Intent intent = new Intent(context, FitzRoyMainActivity.class);
        context.startActivity(intent);
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
        }
    }


    public void requestWritePermission(){
        PackageManager pm = getPackageManager();
        boolean permission = (PackageManager.PERMISSION_GRANTED ==
                pm.checkPermission(PermissionPool.WRITE_EXTERNAL_STORAGE, getPackageName()));
        if (permission) {
            //("有这个权限");

        }else {
            Permissions.with(mActivity)
                    // 申请多个权限
                    .permission(PermissionPool.MANAGE_EXTERNAL_STORAGE)
                    .request(new OnPermissionCallback() {

                        @Override
                        public void onGranted(List<String> permissions, boolean all) {
                            if (all) {
//                            toast("获取录音和日历权限成功");
                                L.d("授权成功");
                            } else {
                                Toast.makeText(LibApplication.getAppContext(),"获取部分权限成功，但部分权限未正常授予",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onDenied(List<String> permissions, boolean never) {
                            if (never) {
                                Toast.makeText(mActivity,"被永久拒绝授权，请手动授予权限",Toast.LENGTH_SHORT).show();
                                // 如果是被永久拒绝就跳转到应用权限系统设置页面
                                Permissions.startPermissionActivity(mActivity, permissions);
                            } else {
//                            toast("获取录音和日历权限失败");
                                Toast.makeText(mActivity,"获取权限失败",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }


}
