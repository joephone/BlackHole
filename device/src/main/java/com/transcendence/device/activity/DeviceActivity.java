package com.transcendence.device.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.device.R;
import com.transcendence.device.app.DeviceApp;
import com.transcendence.device.utils.DeviceUtils;

/**
 * <pre>
 *     author: Blankj
 *     blog : http://blankj.com
 *     time : 2016/9/27
 *     desc : Device工具类测试
 * </pre>
 */
public class DeviceActivity extends TitleBarActivity
        implements View.OnClickListener {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_device;
    }

    @Override
    protected void init() {
        setTitle("Device工具类测试");
        TextView tvAboutDevice = (TextView) findViewById(R.id.tv_about_device);

        findViewById(R.id.btn_shutdown).setOnClickListener(this);
        findViewById(R.id.btn_reboot).setOnClickListener(this);
        findViewById(R.id.btn_reboot_to_recovery).setOnClickListener(this);
        findViewById(R.id.btn_reboot_to_bootloader).setOnClickListener(this);

        tvAboutDevice.setText("isRoot: " + DeviceUtils.isDeviceRoot() +
                "\ngetSDKVersion: " + DeviceUtils.getSDKVersion() +
                "\ngetAndroidID: " + DeviceUtils.getAndroidID(DeviceApp.getInstance()) +
                "\ngetMacAddress: " + DeviceUtils.getMacAddress(DeviceApp.getInstance()) +
                "\ngetManufacturer: " + DeviceUtils.getManufacturer() +
                "\ngetModel: " + DeviceUtils.getModel()
        );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_shutdown:
                DeviceUtils.shutdown();
                break;
            case R.id.btn_reboot:
                DeviceUtils.reboot();
            case R.id.btn_reboot_to_recovery:
                DeviceUtils.reboot2Recovery();
            case R.id.btn_reboot_to_bootloader:
                DeviceUtils.reboot2Bootloader();
                break;
        }
    }
}
