package com.transcendence.blackhole.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.StandardLayoutActivity;
import com.transcendence.blackhole.base.mvp.BaseActivity;
import com.transcendence.blackhole.widget.custom.StandardLayout;

/**
 * @author Joephone
 */
public class MainActivity extends BaseActivity implements View.OnClickListener{

    private StandardLayout standardLayout;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        standardLayout = findViewById(R.id.standardLayout);
        standardLayout.setOnClickListener(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.standardLayout:
                startActivity(StandardLayoutActivity.class);
                break;
        }
    }
}
