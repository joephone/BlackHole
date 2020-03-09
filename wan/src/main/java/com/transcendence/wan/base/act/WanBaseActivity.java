package com.transcendence.wan.base.act;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.transcendence.blackhole.utils.StatusBarUtils;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanMvpActivity;

/**
 * @author Joephone on 2019/9/5 16:16
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public abstract class WanBaseActivity extends WanMvpActivity {

    protected WanBaseActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        StatusBarUtils.with(mActivity).init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    protected void startAct(Class<?> target) {
        Intent intent = new Intent(mActivity, target);
        startActivity(intent);
        overridePendingTransition(R.anim.wan_zoom_small_in, R.anim.wan_zoom_small_out);
    }
}
