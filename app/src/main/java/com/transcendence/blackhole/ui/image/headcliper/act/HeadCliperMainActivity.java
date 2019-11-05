package com.transcendence.blackhole.ui.image.headcliper.act;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;

/**
 * @author Joephone on 2019/11/5 11:00
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class HeadCliperMainActivity extends TitleBarActivity implements View.OnClickListener{

    private TextView mTvQQ,mTvWeChat;
    private ImageView iv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_headcliper_main;
    }

    @Override
    protected void init() {
        setTitle("仿微信QQ上传头像");
        mTvQQ = findViewById(R.id.tvQQ);
        mTvWeChat = findViewById(R.id.tvWeChat);
        iv = findViewById(R.id.iv);
        mTvQQ.setOnClickListener(this);
        mTvWeChat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvQQ:
                startActivity(HeadCliperActivity.class);
                break;
            case R.id.tvWeChat:
                startActivity(HeadCliperActivity.class);
                break;
        }
    }
}
