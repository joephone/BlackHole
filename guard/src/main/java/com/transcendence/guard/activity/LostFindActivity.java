package com.transcendence.guard.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;

import com.transcendence.guard.R;

/**
 * @Author Joephone on 2021/2/10 0010 上午 10:55
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class LostFindActivity extends GuardBaseActivity {

    private TextView mSafePhoneTv;
    private RelativeLayout mInterSetupRl;
    private SharedPreferences sp;
    private ToggleButton mToggleButton;
    private TextView mProtectStatusTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_find);
        sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        if(!isSetUp()){
            startSetUpActivity();
        }
        initView();
    }

    private void initView() {
    }

    private void startSetUpActivity() {
    }

    private boolean isSetUp() {
        return sp.getBoolean("isSetUp",false);
    }
}
