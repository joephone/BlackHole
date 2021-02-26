package com.transcendence.device.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.device.R;
import com.transcendence.device.utils.SDCardUtils;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/9/27
 *     desc  : SD卡工具类测试
 * </pre>
 */
public class SDCardActivity extends TitleBarActivity {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_sdcard;
    }

    @Override
    protected void init() {
        TextView tvAboutSdcard = (TextView) findViewById(R.id.tv_about_sdcard);
        tvAboutSdcard.setText("isSDCardEnable: " + SDCardUtils.isSDCardEnable() +
                "\ngetDataPath: " + SDCardUtils.getDataPath() +
                "\ngetSDCardPath: " + SDCardUtils.getSDCardPath() +
                "\ngetFreeSpace: " + SDCardUtils.getFreeSpace() +
                "\ngetSDCardInfo: " + SDCardUtils.getSDCardInfo()
        );
    }
}
