package com.transcendence.wan.main;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.transcendence.wan.R;
import com.transcendence.wan.base.WanBaseActivity;

@Route(path = "/wan/WanMainActivity")
public class WanMainActivity extends WanBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wan_main);
    }
}
