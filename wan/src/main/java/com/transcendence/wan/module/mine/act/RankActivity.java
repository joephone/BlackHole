package com.transcendence.wan.module.mine.act;

import android.content.Context;
import android.content.Intent;

import com.transcendence.wan.base.act.WanBaseActivity;

/**
 * @author Joephone on 2019/12/17 15:17
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class RankActivity extends WanBaseActivity {


    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }


    public static void start(Context context) {
        Intent intent = new Intent(context, RankActivity.class);
        context.startActivity(intent);
    }
}
