package com.transcendence.wan.module.main.act;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;

import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseActivity;
import com.transcendence.wan.module.main.presenter.WanMainPresenter;
import com.transcendence.wan.module.main.view.WanMainView;

public class DrawerMainActivity extends WanBaseActivity<WanMainPresenter> implements WanMainView {


    private DrawerLayout mDrawerLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wan_main_drawerlayout;
    }

    @Nullable
    @Override
    protected WanMainPresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        initDrawerLayout();

    }

    private void initDrawerLayout() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,mDrawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);

            }
        };
    }

    @Override
    protected void loadData() {

    }


//    @Override
//    public boolean swipeBackEnable() {
//        return false;
//    }
}
