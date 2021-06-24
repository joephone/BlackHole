package com.transcendence.blackhole.ui.base.act;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.transcendence.blackhole.R;
import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.core.utils.L;

/**
 * @author Joephone on 2019/5/27 14:57
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc  生命周期
 * @Edition 1.0
 * @EditionHistory
 */

public class LifeCycleTwoActivity extends TitleBarActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d("1 other onCreate ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        L.d("2 other onStart ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        L.d("other onRestart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        L.d("3 other onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        L.d("4 other onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        L.d("5 other onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.d("6 other onDestroy");
    }

    TextView mTvTab;

    @Override
    public void init() {
        setTitle("生命周期另");
        mTvTab = findViewById(R.id.tvTab);
        mTvTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_life_cycle;
    }
}
