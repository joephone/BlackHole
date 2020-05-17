package com.transcendence.wan.module.main.act;

import android.support.v4.view.ViewPager;
import android.view.KeyEvent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjq.toast.ToastUtils;
import com.transcendence.blackhole.adapter.GoweiiFragmentPagerAdapter;
import com.transcendence.blackhole.arouter.ARouterController;
import com.transcendence.blackhole.arouter.ARouterUtils;
import com.transcendence.wan.R;
import com.transcendence.wan.base.act.WanBaseActivity;
import com.transcendence.wan.module.dama.fragment.DamaFragment;
import com.transcendence.wan.module.home.fragment.MainFragment;
import com.transcendence.wan.module.main.presenter.WanMainPresenter;
import com.transcendence.wan.module.main.view.WanMainView;

/**
 * @author Administrator
 */

@Route(path = ARouterController.WAN_MAIN)
public class WanMainActivity extends WanBaseActivity<WanMainPresenter> implements WanMainView {

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
    protected WanMainPresenter initPresenter() {
        return new WanMainPresenter();
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
        mVp.setCurrentItem(4);
    }

    public void slideToDama(){
        mVp.setCurrentItem(0);
    }

    @Override
    public boolean swipeBackEnable() {
        return false;
    }

    //记录用户首次点击返回键的时间
    private long firstTime=0;
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    ToastUtils.show("再按一次返回键退出程序");
                    firstTime = secondTime;
                    return true;
                } else {
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

}
