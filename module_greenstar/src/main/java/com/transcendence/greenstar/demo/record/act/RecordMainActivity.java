package com.transcendence.greenstar.demo.record.act;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;


import com.transcendence.core.base.activity.AppAc;
import com.transcendence.greenstar.R;
import com.transcendence.greenstar.demo.record.example.RecordExampleActivity;


public class RecordMainActivity extends AppAc {
    private TextView mAmTvBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo_record_main;
    }

    @Override
    protected void initView() {
        mAmTvBtn= (TextView) findViewById(R.id.am_tv_btn);
        initListener();
    }



    private void initListener() {
        mAmTvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RecordMainActivity.this, RecordExampleActivity.class);
                startActivity(intent);

            }
        });
    }
}
