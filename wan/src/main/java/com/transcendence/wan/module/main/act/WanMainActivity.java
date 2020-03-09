package com.transcendence.wan.module.main.act;

import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.transcendence.blackhole.adapter.GoweiiFragmentPagerAdapter;
import com.transcendence.blackhole.arouter.ARouterController;
import com.transcendence.blackhole.arouter.ARouterUtils;
import com.transcendence.wan.R;
import com.transcendence.wan.base.act.WanBaseActivity;
import com.transcendence.wan.module.dama.fragment.DamaFragment;
import com.transcendence.wan.module.home.fragment.MainFragment;

/**
 * @author Administrator
 */

@Route(path = ARouterController.WAN_MAIN)
public class WanMainActivity extends WanBaseActivity {

    private ViewPager mVp;

    /**
     * Goweii的vpAdapter
     */
    private GoweiiFragmentPagerAdapter adapter;




    @Override
    protected int getLayoutId() {
        return R.layout.activity_wan_main;
    }

    @Override
    protected void initView() {
        ARouterUtils.injectActivity(mActivity);

        mVp = findViewById(R.id.vp);
        initVP();
    }

    @Override
    protected void loadData() {

    }


    private void initVP() {
        //预加载
        mVp.setOffscreenPageLimit(1);
        adapter = new GoweiiFragmentPagerAdapter(getSupportFragmentManager());
        adapter.setTitles("广场","主页面");
        adapter.setFragments(
                DamaFragment.newInstance("广场"),
                MainFragment.newInstance("主页面"));
        mVp.setAdapter(adapter);
        mVp.setCurrentItem(1);
    }

    public void slideToDama(){
        mVp.setCurrentItem(0);
    }



}
