package com.transcendence.blackhole.ui.widget.custom;

import android.view.View;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.demo.guide.GuideActivity;
import com.transcendence.blackhole.base.activity.BaseActivity;
import com.transcendence.blackhole.widget.custom.StandardLayout;

/**
 * @author Joephone
 */
public class StandardLayoutActivity extends BaseActivity implements View.OnClickListener{

    private StandardLayout standardLayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_widget_standard_layout;
    }


    @Override
    public void init() {
        standardLayout = findViewById(R.id.standardLayout);
        standardLayout.setOnClickListener(this);
        standardLayout.setRightHint("右提示");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.standardLayout:
                startActivity(GuideActivity.class);
                break;
        }
    }
}
