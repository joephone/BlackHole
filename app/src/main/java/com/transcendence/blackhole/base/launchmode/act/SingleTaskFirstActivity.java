package com.transcendence.blackhole.base.launchmode.act;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.transcendence.blackhole.R;
import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.core.utils.L;

/**
 * @Author Joephone on 2020/4/21 16:58
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class SingleTaskFirstActivity extends TitleBarActivity implements View.OnClickListener {

    private TextView mTvSecond,mTvThird;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_launchmode_first;
    }

    @Override
    protected void init() {
        setTitle("");
        L.d("first init");
        setTitle("第一界面");
        mTvSecond = findViewById(R.id.tvSecond);
        mTvSecond.setOnClickListener(this);
        mTvThird = findViewById(R.id.tvThird);
        mTvThird.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvSecond:
                Intent intent = new Intent(mActivity,SingleTaskSecondActivity.class);
                startActivity(intent);
                break;
            case R.id.tvThird:
                intent = new Intent(mActivity,SingleTaskThirdActivity.class);
                startActivity(intent);
                break;
        }
    }
}
