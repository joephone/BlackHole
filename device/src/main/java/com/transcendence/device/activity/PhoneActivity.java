package com.transcendence.device.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.device.R;
import com.transcendence.device.utils.PhoneUtils;

public class PhoneActivity extends TitleBarActivity implements
        View.OnClickListener {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_phone;
    }

    @Override
    protected void init() {
        setTitle("手机相关工具类");
        TextView tvAboutPhone = (TextView) findViewById(R.id.tv_about_phone);

        findViewById(R.id.btn_dial).setOnClickListener(this);
        findViewById(R.id.btn_call).setOnClickListener(this);
        findViewById(R.id.btn_send_sms).setOnClickListener(this);
        findViewById(R.id.btn_send_sms_silent).setOnClickListener(this);

        tvAboutPhone.setText("isPhone: " + PhoneUtils.isPhone(this) +
                "\ngetIMEI: " + PhoneUtils.getIMEI(this) +
                "\ngetIMSI: " + PhoneUtils.getIMSI(this) +
                "\ngetPhoneType: " + PhoneUtils.getPhoneType(this) +
                "\nisSimCardReady: " + PhoneUtils.isSimCardReady(this) +
                "\ngetSimOperatorName: " + PhoneUtils.getSimOperatorName(this) +
                "\ngetSimOperatorByMnc: " + PhoneUtils.getSimOperatorByMnc(this) +
                "\n获取手机状态信息: " + PhoneUtils.getPhoneStatus(this)
        );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_dial:
                PhoneUtils.dial(this, "10000");
                break;
            case R.id.btn_call:
                PhoneUtils.call(this, "10000");
                break;
            case R.id.btn_send_sms:
                PhoneUtils.sendSms(this, "10000", "test");
                break;
            case R.id.btn_send_sms_silent:
                PhoneUtils.sendSmsSilent(this, "10000", "test");
                break;
        }
    }
}
