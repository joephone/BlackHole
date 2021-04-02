package com.transcendence.device.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.hjq.toast.ToastUtils;
import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.device.R;
import com.transcendence.device.utils.ProcessUtils;


public class ProcessActivity extends TitleBarActivity
        implements View.OnClickListener {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_process;
    }

    @Override
    protected void init() {
        findViewById(R.id.btn_kill_all_background_processes).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_kill_all_background_processes:
//                ToastUtils.showShortToast(this, ProcessUtils.killAllBackgroundProcesses(this));
                break;
        }
    }
}
