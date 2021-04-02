package com.transcendence.device.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.transcendence.core.base.activity.TitleBarActivity;

import com.transcendence.core.base.app.LibApplication;
import com.transcendence.core.utils.L;
import com.transcendence.device.R;
import com.transcendence.device.utils.AppUtils;

/**
 * <pre>
 *     author: Blankj
 *     blog : http://blankj.com
 *     time : 2016/10/13
 *     desc : App工具类测试
 * </pre>
 */

public class AppActivity extends TitleBarActivity
        implements View.OnClickListener {

    private String appPath;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_app;
    }

    @Override
    protected void init() {
        setTitle("App工具类测试");
        appPath = AppUtils.getAppPath(this);

        TextView tvAboutApp = (TextView) findViewById(R.id.tv_about_app);

        findViewById(R.id.btn_install_app).setOnClickListener(this);
        findViewById(R.id.btn_install_app_silent).setOnClickListener(this);
        findViewById(R.id.btn_uninstall_app).setOnClickListener(this);
        findViewById(R.id.btn_uninstall_app_silent).setOnClickListener(this);
        findViewById(R.id.btn_launch_app).setOnClickListener(this);
        findViewById(R.id.btn_get_app_details_settings).setOnClickListener(this);

        tvAboutApp.setText(AppUtils.getAppInfo(this).toString() +
                "\nisInstallWeiXin: " + AppUtils.isInstallApp(this, "com.tencent.mm") +
                "\nisAppRoot: " + AppUtils.isAppRoot() +

                "\nisAppDebug: " + AppUtils.isAppDebug(this) +
                "\nisWeiXinAppDebug: " + AppUtils.isAppDebug(this, "com.tencent.mm") +
                "\nAppSignatureSHA1: " + AppUtils.getAppSignatureSHA1(this) +
                "\nisAppForeground: " + AppUtils.isAppForeground(this) +
                "\nisWeiXinForeground: " + AppUtils.isAppForeground(this, "com.tencent.mm")
        );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_install_app:
                L.d("appPath:"+appPath);
                AppUtils.installApp(this, appPath);
                break;
            case R.id.btn_install_app_silent:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        AppUtils.installAppSilent(LibApplication.getInstance(), appPath);
                    }
                }).start();
                break;
            case R.id.btn_uninstall_app:
                AppUtils.uninstallApp(this, this.getPackageName());
                break;
            case R.id.btn_uninstall_app_silent:
                AppUtils.uninstallAppSilent(this, this.getPackageName(), false);
                break;
            case R.id.btn_launch_app:
                AppUtils.launchApp(this, this.getPackageName());
                break;
            case R.id.btn_get_app_details_settings:
                AppUtils.getAppDetailsSettings(this);
                break;
        }
    }
}
