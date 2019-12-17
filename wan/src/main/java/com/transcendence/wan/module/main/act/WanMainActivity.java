package com.transcendence.wan.module.main.act;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.transcendence.blackhole.adapter.GoweiiFragmentPagerAdapter;
import com.transcendence.wan.R;
import com.transcendence.wan.base.act.WanBaseActivity;
import com.transcendence.wan.module.dama.fragment.DamaFragment;
import com.transcendence.wan.module.home.fragment.MainFragment;

/**
 * @author Administrator
 */
@Route(path = "/wan/WanMainActivity")
public class WanMainActivity extends WanBaseActivity {


    private ViewPager mVp;

    /**
     * Goweii的vpAdapter
     */
    private GoweiiFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wan_main);

        initView();
        initVP();
    }



    private void initView() {
        mVp = findViewById(R.id.vp);

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
