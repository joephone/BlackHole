package com.transcendence.wan.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import com.transcendence.wan.R;
import com.transcendence.wan.base.WanBaseActivity;

/**
 * @Author Joephone on 2019/9/6 14:42
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class WanSearchActivity extends WanBaseActivity {

    FrameLayout mFl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wan_search);

        mFl = findViewById(R.id.fl);
    }
}
