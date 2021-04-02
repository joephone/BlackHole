package com.transcendence.device.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.device.R;
import com.transcendence.device.utils.HandlerUtils;


/**
 * <pre>
 *     author: Blankj
 *     blog : http://blankj.com
 *     time : 2016/9/27
 *     desc : Device工具类测试
 * </pre>
 */
public class HandlerActivity extends TitleBarActivity
        implements View.OnClickListener, HandlerUtils.OnReceiveMessageListener {

    private TextView tvAboutHandler0;
    private TextView tvAboutHandler1;
    HandlerUtils.HandlerHolder handlerHolder;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_handler;
    }

    @Override
    protected void init() {
        setTitle("Device工具类测试");
        tvAboutHandler0 = (TextView) findViewById(R.id.tv_about_handler0);
        tvAboutHandler1 = (TextView) findViewById(R.id.tv_about_handler1);
        findViewById(R.id.btn_send_msg_after_3s).setOnClickListener(this);

        handlerHolder = new HandlerUtils.HandlerHolder(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send_msg_after_3s:
                handlerHolder.sendEmptyMessageDelayed(0, 3000);
                break;
        }
    }

    @Override
    public void handlerMessage(Message msg) {
        tvAboutHandler1.setText("get_msg_after_3s");
    }
}
