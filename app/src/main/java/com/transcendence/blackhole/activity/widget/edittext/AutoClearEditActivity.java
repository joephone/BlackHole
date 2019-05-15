package com.transcendence.blackhole.activity.widget.edittext;

import android.view.View;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.mvp.BaseActivity;
import com.transcendence.blackhole.widget.edittext.ClearEditText;
import com.transcendence.blackhole.widget.edittext.EditTextAutoClear;

/**
 * @author Joephone on 2019/5/14 11:12
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 */

public class AutoClearEditActivity extends BaseActivity implements View.OnClickListener{

    private EditTextAutoClear editTextAutoClear;
    private ClearEditText clearEditText;

    @Override
    public int getLayoutId() {
        return R.layout.activity_edittext_autoclear;
    }

    @Override
    public void initView() {
        editTextAutoClear = findViewById(R.id.etStyleOne);
        clearEditText = findViewById(R.id.etStyleTwo);
    }

    @Override
    public void init() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.standardLayout:
//                startActivity(GuideActivity.class);
//                break;
        }
    }
}
