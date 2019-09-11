package com.transcendence.wan.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.transcendence.blackhole.utils.StatusBarUtils;
import com.transcendence.wan.R;

/**
 * @author Joephone on 2019/9/5 16:16
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public abstract class WanBaseActivity extends AppCompatActivity {

    protected WanBaseActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        StatusBarUtils.with(mActivity).init();
    }


    protected void startAct(Class<?> target) {
        Intent intent = new Intent(mActivity, target);
        startActivity(intent);
        overridePendingTransition(R.anim.wan_zoom_small_in, R.anim.wan_zoom_small_out);
    }
}
