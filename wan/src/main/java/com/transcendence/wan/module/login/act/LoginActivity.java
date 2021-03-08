package com.transcendence.wan.module.login.act;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.transcendence.core.adapter.GoweiiFragmentPagerAdapter;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseActivity;
import com.transcendence.wan.core.mvp.presenter.WanBasePresenter;
import com.transcendence.wan.module.login.fragment.LoginFragment;
import com.transcendence.wan.module.login.fragment.RegisterFragment;
import com.transcendence.wan.utils.SoftInputHelper;
import com.transcendence.wan.utils.StringUtils;

import per.goweii.swipeback.SwipeBackDirection;

/**
 * @author CuiZhen
 * @date 2019/5/15
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */

public class LoginActivity extends WanBaseActivity {

    private ViewPager mVp;

    private boolean isRunning = false;
    private AnimatorSet mSet1;
    private AnimatorSet mSet2;
    private SoftInputHelper mSoftInputHelper;


    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            activity.overridePendingTransition(R.anim.swipeback_activity_open_bottom_in,
                    R.anim.swipeback_activity_open_top_out);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wan_login;
    }

    @Nullable
    @Override
    protected WanBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        mVp = findViewById(R.id.vp);
        initVP();
    }


    @Override
    protected void loadData() {

    }


    private void initVP() {
        //预加载
        mVp.setOffscreenPageLimit(1);
        GoweiiFragmentPagerAdapter adapter = new GoweiiFragmentPagerAdapter(getSupportFragmentManager());
        adapter.setTitles(StringUtils.getString(R.string.login),StringUtils.getString(R.string.register));
        adapter.setFragments(
                LoginFragment.newInstance(StringUtils.getString(R.string.login)),
                RegisterFragment.newInstance(StringUtils.getString(R.string.register)));
        mVp.setAdapter(adapter);
        mVp.setCurrentItem(0);
    }

    public void changeVp(int index) {
        mVp.setCurrentItem(index);
    }


    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void clearLoading() {

    }

    @Override
    protected int swipeBackDirection() {
        return SwipeBackDirection.FROM_TOP;
    }
}
