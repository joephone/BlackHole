package com.transcendence.blackhole.demo.calc.act;

import android.widget.TextView;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;

/**
 * @Author Joephone on 2020/4/5 23:46
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class XiaoMiCalcActivity extends TitleBarActivity {

    private TextView print;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo_calculator_xiaomi;
    }

    @Override
    protected void init() {
        setTitle("仿小米计算器");
    }
}
