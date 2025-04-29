package com.transcendence.freeland.basefun.nostart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.core.utils.log.LogUtils;
import com.transcendence.freeland.R;

/**
 * @author joephone
 * @date 2023/8/8
 * @desc
 */
public class NoStartC extends AppAc {

    private TextView mTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d("NoStartC的taskId:"+getTaskId());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_basefun_nostart;
    }

    @Override
    protected void initView() {
        mTv = findViewById(R.id.tv);
        mTv.setText("C");
        mTv.setOnClickListener(v ->{
            Intent intent = new Intent();
            intent.setClass(mActivity, NoStartA.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
            startActivity(intent);

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d("NoStartC+++onResume");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtils.d("NoStartC+++onNewIntent");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d("NoStartC+++onDestroy");
    }
}
