package com.transcendence.wan.module.setting.act;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.transcendence.core.utils.L;
import com.transcendence.ui.dialog.hjq.layout.UpdateDialog;
import com.transcendence.wan.R;
import com.transcendence.wan.core.bean.NewWanBaseBean;
import com.transcendence.wan.core.mvp.WanTitleBarActivity;
import com.transcendence.wan.event.LoginEvent;
import com.transcendence.wan.module.setting.presenter.SettingPresenter;
import com.transcendence.wan.module.setting.view.SettingView;
import com.transcendence.wan.utils.StringUtils;
import com.transcendence.wan.utils.UserUtils;

/**
 * @Author Joephone on 2020/3/14 17:11
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class SettingActivity extends WanTitleBarActivity<SettingPresenter> implements SettingView,View.OnClickListener {


    TextView tvCache;
    private LinearLayout llLanguage,llCheckUpdate,llCache,llLogout;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_wan_setting;
    }

    @Override
    protected SettingPresenter initPresenter() {
        return new SettingPresenter();
    }


    @Override
    protected void initView() {
        setTitle(StringUtils.getString(R.string.setting));
        tvCache = findViewById(R.id.tv_cache);
        llLanguage = findViewById(R.id.ll_language);
        llLanguage.setOnClickListener(this);
        llCheckUpdate = findViewById(R.id.ll_check_update);
        llCheckUpdate.setOnClickListener(this);
        llCache = findViewById(R.id.ll_cache);
        llCache.setOnClickListener(this);
        llLogout = findViewById(R.id.ll_logout);
        llLogout.setOnClickListener(this);
        if(!UserUtils.getInstance().isLogin()){
            llLogout.setVisibility(View.INVISIBLE);
        }
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
            case R.id.ll_language:
                LanguageActivity.start(getContext());
                break;
            case R.id.ll_check_update:
                new UpdateDialog.Builder(mActivity)
                        // 版本名
                        .setVersionName("1.0.0")
                        // 是否强制更新
                        .setForceUpdate(false)
                        // 更新日志
                        .setUpdateLog("到底更新了啥\n到底更新了啥\n到底更新了啥\n到底更新了啥\n到底更新了啥")
                        // 下载 URL
                        .setDownloadUrl("https://dldir1.qq.com/weixin/android/weixin7014android1660.apk")
                        // 文件 MD5
                        .setFileMd5("6ec99cb762ffd9158e8b27dc33d9680d")
                        .show();
                break;
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
        finish();
    }

    @Override
    public void logoutFailed(int code, String msg) {

    }

    @Override
    public void getCacheSizeSuccess(String size) {
        tvCache.setText(TextUtils.isEmpty(size)?"":size);
    }
}
