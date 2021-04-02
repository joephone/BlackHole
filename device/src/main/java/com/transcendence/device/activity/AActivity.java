package com.transcendence.device.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.device.R;
import com.transcendence.device.utils.ActivityUtils;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/10/13
 *     desc  : Activity工具类测试
 * </pre>
 */

public class AActivity extends TitleBarActivity
        implements View.OnClickListener {

    private String packageName;
    private String className;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_act;
    }

    @Override
    protected void init() {
        setTitle("Activity工具类测试");
        packageName = this.getPackageName();
        className = packageName + ".activity.ImageActivity";

        TextView tvAboutActivity = (TextView) findViewById(R.id.tv_about_activity);

        findViewById(R.id.btn_launch_image_activity).setOnClickListener(this);

        tvAboutActivity.setText("Is ImageActivity Exists: " + ActivityUtils.isActivityExists(this, packageName, className) +
                "\ngetLauncherActivity: " + ActivityUtils.getLauncherActivity(this, packageName)
        );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_launch_image_activity:
                ActivityUtils.launchActivity(this, packageName, className);
                break;
        }
    }
}
