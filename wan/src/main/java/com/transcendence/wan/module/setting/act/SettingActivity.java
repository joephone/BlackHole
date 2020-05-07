package com.transcendence.wan.module.setting.act;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.transcendence.blackhole.utils.L;
import com.transcendence.wan.R;
import com.transcendence.wan.base.act.WanBaseActivity;
import com.transcendence.wan.base.bean.NewWanBaseBean;
import com.transcendence.wan.event.LoginEvent;
import com.transcendence.wan.module.setting.presenter.SettingPresenter;
import com.transcendence.wan.module.setting.view.SettingView;
import com.transcendence.wan.utils.UserUtils;

/**
 * @Author Joephone on 2020/3/14 17:11
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class SettingActivity extends WanBaseActivity<SettingPresenter> implements SettingView,View.OnClickListener {


    TextView tv_cache;
    private LinearLayout ll_cache,llLogout;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_wan_setting;
    }

    @Nullable
    @Override
    protected SettingPresenter initPresenter() {
        return new SettingPresenter();
    }


    @Override
    protected void initView() {
        tv_cache = findViewById(R.id.tv_cache);
        ll_cache = findViewById(R.id.ll_cache);
        ll_cache.setOnClickListener(this);
        llLogout = findViewById(R.id.ll_logout);
        llLogout.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        presenter.getCacheSize();
    }


    public static void start(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.fl_right:
//
//                break;
            case R.id.ll_cache:
                presenter.clearCache();
                break;
            case R.id.ll_logout:
                if(UserUtils.getInstance().toDoIfLogin(getContext())){
                    presenter.logout();
                }else {
                    L.d("未登录");
                }
                break;
        }
    }


    @Override
    public void logoutSuccess(int code, NewWanBaseBean data) {
        L.d("logoutSuccess");
        UserUtils.getInstance().logout();
        new LoginEvent(false).post();
    }

    @Override
    public void logoutFailed(int code, String msg) {

    }

    @Override
    public void getCacheSizeSuccess(String size) {
        tv_cache.setText(TextUtils.isEmpty(size)?"":size);
    }
}
