package com.transcendence.blackhole.core;

import android.view.View;
import android.widget.TextView;

import com.transcendence.blackhole.R;
import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.blackhole.ui.widget.button.JianbianButtonActivity;
import com.transcendence.blackhole.ui.widget.button.WaterButtonBlueActivity;
import com.transcendence.blackhole.ui.widget.custom.StandardLayoutActivity;
import com.transcendence.blackhole.ui.widget.edittext.AutoClearEditActivity;
import com.transcendence.core.widget.custom.StandardLayout;

/**
 * @author Joephone
 */
public class MainActivity extends TitleBarActivity implements View.OnClickListener{

    private StandardLayout standardLayout;
    private StandardLayout paint;
    private StandardLayout edittext;
    private StandardLayout slBlueThemeWaterButton;

    private TextView tvJianbian;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void init() {
        setTitle("主页面");
        standardLayout = findViewById(R.id.standardLayout);
        paint = findViewById(R.id.paintView);
        edittext = findViewById(R.id.edittext);
        tvJianbian = findViewById(R.id.tvJianbian);
        slBlueThemeWaterButton = findViewById(R.id.slBlueThemeWaterButton);
        standardLayout.setOnClickListener(this);
        paint.setOnClickListener(this);
        edittext.setOnClickListener(this);
        tvJianbian.setOnClickListener(this);
        slBlueThemeWaterButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.paintView:
//                startActivity(PaintActivity.class);
                break;
            case R.id.standardLayout:
                startActivity(StandardLayoutActivity.class);
                break;
            case R.id.edittext:
                startActivity(AutoClearEditActivity.class);
                break;
            case R.id.tvJianbian:
                startActivity(JianbianButtonActivity.class);
                break;
            case R.id.slBlueThemeWaterButton:
                startActivity(WaterButtonBlueActivity.class);
                break;
        }
    }
}
