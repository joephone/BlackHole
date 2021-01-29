package com.transcendence.blackhole.ui.widget.button;

import android.view.View;
import android.widget.TextView;

import com.transcendence.blackhole.R;
import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.core.utils.AppUtils;

/**
 * @author Joephone on 2019/5/14 14:31
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 */

public class JianbianButtonActivity extends TitleBarActivity implements View.OnClickListener{

    private TextView tvTabOne,tvTabTwo,tvTabThree;

    @Override
    public int getLayoutId() {
        return R.layout.activity_button_jianbian;
    }


    @Override
    public void init() {
        setTitle("渐变");
        tvTabOne = findViewById(R.id.tvTabOne);
        tvTabTwo = findViewById(R.id.tvTabTwo);
        tvTabThree = findViewById(R.id.tvTabThree);
        tvTabOne.setOnClickListener(this);
        tvTabTwo.setOnClickListener(this);
        tvTabThree.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvTabOne:
                AppUtils.getInstance().trace("tvTabOne");
                break;
            case R.id.tvTabTwo:
                AppUtils.getInstance().trace("tvTabTwo");
                break;
            case R.id.tvTabThree:
                AppUtils.getInstance().trace("tvTabThree");
                break;
        }
    }
}
