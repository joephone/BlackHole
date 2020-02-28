package com.transcendence.blackhole.ui.scroll.personalscroll.act;

import android.view.View;
import android.widget.TextView;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;

/**
 * Created by Administrator on 2020/2/28.
 */

public class PersonalScrollMainAct extends TitleBarActivity implements View.OnClickListener{

    private TextView tvPullDown,tvStretch;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scroll_presonalscroll_main;
    }

    @Override
    protected void init() {
        tvPullDown = findViewById(R.id.tvPullDown);
        tvStretch = findViewById(R.id.tvStretch);
        tvPullDown.setOnClickListener(this);
        tvStretch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvPullDown:
                startActivity(PersonalScrollPulldownViewActivity.class);
                break;
            case R.id.tvStretch:
                startActivity(PersonalScrollStretchViewActivity.class);
                break;
        }
    }
}
