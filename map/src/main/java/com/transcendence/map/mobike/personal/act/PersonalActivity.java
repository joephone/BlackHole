package com.transcendence.map.mobike.personal.act;

import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.map.R;
import com.transcendence.map.mobike.transformation.BlurTransformation;

/**
 * @author Joephone on 2019/11/6 16:37
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class PersonalActivity extends TitleBarActivity {

    private ImageView mIv,mIvBg;
    private RelativeLayout mRlBg;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mobike_personal;
    }

    @Override
    protected void init() {
        setTitle("个人信息");

        initListener();

    }

    private void initListener() {
        mRlBg = findViewById(R.id.rlBg);
        mIv = findViewById(R.id.iv);
        mIvBg = findViewById(R.id.ivBg);

        Glide.with(this).load(R.mipmap.ic_avatar_default)
                .bitmapTransform(new BlurTransformation(getApplicationContext()))
                .into(mIvBg);
    }
}
