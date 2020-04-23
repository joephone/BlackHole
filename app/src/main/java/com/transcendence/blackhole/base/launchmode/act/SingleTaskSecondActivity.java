package com.transcendence.blackhole.base.launchmode.act;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.utils.L;

/**
 * @Author Joephone on 2020/4/21 16:58
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class SingleTaskSecondActivity extends TitleBarActivity implements View.OnClickListener {

    private TextView mTvSecond,mTvThird;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_launchmode_second;
    }

    @Override
    protected void init() {
        L.d("second init");
        setTitle("第二界面");
        mTvSecond = findViewById(R.id.tvFirst);
        mTvSecond.setOnClickListener(this);
        mTvThird = findViewById(R.id.tvThird);
        mTvThird.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvFirst:
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
