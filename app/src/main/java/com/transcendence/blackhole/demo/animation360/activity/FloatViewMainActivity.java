package com.transcendence.blackhole.demo.animation360.activity;


import android.content.Intent;
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
        Intent intent=new Intent(this, MyFloatService.class);
        startService(intent);


    }
}
