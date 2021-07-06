package com.transcendence.wan.module.main.act;

import android.view.KeyEvent;

import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjq.toast.ToastUtils;
import com.transcendence.core.adapter.GoweiiFragmentPagerAdapter;
import com.transcendence.core.arouter.ARouterController;
import com.transcendence.core.arouter.ARouterUtils;
import com.transcendence.core.utils.L;
import com.transcendence.permissions.OnPermissionCallback;
import com.transcendence.permissions.PermissionPool;
import com.transcendence.permissions.Permissions;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseActivity;
import com.transcendence.wan.module.dama.fragment.DamaFragment;
import com.transcendence.wan.module.home.fragment.MainFragment;
import com.transcendence.wan.module.main.presenter.WanMainPresenter;
import com.transcendence.wan.module.main.view.WanMainView;
import com.transcendence.wan.ui.dialog.PrivacyPolicyDialog;

import java.util.List;

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

        showPrivacyPolicyDialog();

        Permissions.with(mActivity)
                .permission(PermissionPool.GROUP.STORAGE)
                .request(new OnPermissionCallback() {
                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        L.d("download enable");
                    }
                });
    }



    /**
     * Show Private Policy
     * Must run on UIThread
     */
    private void showPrivacyPolicyDialog() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        L.d("showPrivacyPolicyDialog");
                        PrivacyPolicyDialog.showIfFirst(getContext(), new PrivacyPolicyDialog.CompleteCallback() {
                            @Override
                            public void onComplete() {

                            }
                        });
                    }
                });
            }
        }).start();
    }


    public void slideToDama(){
        mVp.setCurrentItem(0);
    }

//    @Override
//    public boolean swipeBackEnable() {
//        return false;
//    }

    //记录用户首次点击返回键的时间
    private long mExitTime=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (System.currentTimeMillis() - mExitTime > 2000) {
                    ToastUtils.show("再按一次返回键退出程序");
                    mExitTime = System.currentTimeMillis();
                    return true;
                } else {
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }




}
