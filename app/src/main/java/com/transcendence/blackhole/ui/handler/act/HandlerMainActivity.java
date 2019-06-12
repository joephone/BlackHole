package com.transcendence.blackhole.ui.handler.act;

import android.view.View;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.widget.custom.autoscroll.MarqueeTextView;

/**
 * @author Joephone on 2019/6/4 17:46
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class HandlerMainActivity extends TitleBarActivity implements View.OnClickListener{

    private MarqueeTextView mTvhandler3,mTvhandler4;

    @Override
    public int getLayoutId() {
        return R.layout.activity_base_handler_main;
    }

    @Override
    public void init() {
        setTitle("什么是Handler");
        mTvhandler3 = findViewById(R.id.tvhandler3);
        mTvhandler4 = findViewById(R.id.tvhandler4);
        mTvhandler3.setOnClickListener(this);
        mTvhandler4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvhandler3:
                startActivity(HelloHandler01.class);
                break;
            case R.id.tvhandler4:
                startActivity(HelloHandler02.class);
                break;
        }
    }
}
