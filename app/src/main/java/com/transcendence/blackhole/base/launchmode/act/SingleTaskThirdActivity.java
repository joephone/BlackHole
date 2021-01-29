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

public class SingleTaskThirdActivity extends TitleBarActivity implements View.OnClickListener{

    private TextView mTvFirst,mTvSecond;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_launchmode_third;
    }

    @Override
    protected void init() {
        L.d("third init");
        setTitle("第三界面");
        mTvFirst = findViewById(R.id.tvFirst);
        mTvFirst.setOnClickListener(this);
        mTvSecond = findViewById(R.id.tvSecond);
        mTvSecond.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvFirst:
                Intent intent = new Intent(mActivity,SingleTaskFirstActivity.class);
                startActivity(intent);
                break;
            case R.id.tvSecond:
                intent = new Intent(mActivity,SingleTaskSecondActivity.class);
                startActivity(intent);
                break;
        }
    }
}
