package com.transcendence.blackhole.activity.base.act;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;

/**
 * @author Joephone on 2019/6/4 17:46
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class HandlerMainActivity extends TitleBarActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_base_handler_main;
    }

    @Override
    public void init() {
        setTitle("什么是Handler");
    }
}
