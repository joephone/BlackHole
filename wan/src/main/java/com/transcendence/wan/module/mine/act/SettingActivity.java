package com.transcendence.wan.module.mine.act;

import android.content.Context;
import android.content.Intent;

import com.transcendence.wan.R;
import com.transcendence.wan.base.act.WanBaseActivity;

/**
 * @Author Joephone on 2020/3/14 17:11
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class SettingActivity extends WanBaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_wan_setting;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }


    public static void start(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }
}
