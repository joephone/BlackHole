package com.transcendence.device.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.device.R;

public class DeviceMainActivity extends TitleBarActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_device;
    }

    @Override
    protected void init() {
        setTitle(false,"目录");
    }


    public void activityClick(View view) {
        startActivity(new Intent(this, AActivity.class));
    }

    public void appClick(View view) {
        startActivity(new Intent(this, AppActivity.class));
    }

    public void cleanClick(View view) {
        startActivity(new Intent(this, CleanActivity.class));
    }

    public void crashClick(View view) {
        int err = 1 / 0;
    }

    public void deviceClick(View view) {
        startActivity(new Intent(this, DeviceActivity.class));
    }

    public void handlerClick(View view) {
        startActivity(new Intent(this, HandlerActivity.class));
    }

    public void imageClick(View view) {
        startActivity(new Intent(this, ImageActivity.class));
    }

    public void keyboardClick(View view) {
        startActivity(new Intent(this, KeyboardActivity.class));
    }

    public void networkClick(View view) {
        startActivity(new Intent(this, NetworkActivity.class));
    }

    public void phoneClick(View view) {
        startActivity(new Intent(this, PhoneActivity.class));
    }

    public void processClick(View view) {
        startActivity(new Intent(this, ProcessActivity.class));
    }

    public void sdcardClick(View view) {
        startActivity(new Intent(this, SDCardActivity.class));
    }

//    public void snackbarClick(View view) {
//        startActivity(new Intent(this, SnackbarActivity.class));
//    }
//
//    public void toastClick(View view) {
//        startActivity(new Intent(this, ToastActivity.class));
//    }
}
