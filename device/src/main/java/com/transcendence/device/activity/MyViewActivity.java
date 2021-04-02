package com.transcendence.device.activity;

import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.device.R;

/**
 * @Author Joephone on 2021/3/24 0024 上午 11:05
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class MyViewActivity extends TitleBarActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_view;
    }

    @Override
    protected void init() {
        setTitle("My View");
    }
}
