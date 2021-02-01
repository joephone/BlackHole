package com.transcendence.blackhole.demo.animation360.activity;


import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.demo.animation360.service.MyFloatService;
import com.transcendence.core.base.activity.TitleBarActivity;


public class FloatViewMainActivity extends TitleBarActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo_float_view_main;
    }

    @Override
    protected void init() {

    }

    public void startService(View view){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.canDrawOverlays(FloatViewMainActivity.this)) {
                Intent intent = new Intent(FloatViewMainActivity.this, MyFloatService.class);
                startService(intent);
            } else {
                //若没有权限，提示获取.
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//                Toast.makeText(MainActivity.this,"需要取得权限以使用悬浮窗",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }

        }else {
            //SDK在23以下，不用管.
            Intent intent = new Intent(FloatViewMainActivity.this, MyFloatService.class);
            startService(intent);
        }
    }
}
