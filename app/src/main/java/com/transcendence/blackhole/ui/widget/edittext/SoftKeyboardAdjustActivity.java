package com.transcendence.blackhole.ui.widget.edittext;

import com.transcendence.blackhole.R;
import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.blackhole.utils.SoftHideKeyBoardUtil;

/**
 * @Author Joephone on 2020/5/7 23:09
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class SoftKeyboardAdjustActivity extends TitleBarActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_widget_et_softkeykoard;
    }

    @Override
    protected void init() {
        setTitle("软键盘挡住输入框问题");
        SoftHideKeyBoardUtil.assistActivity(this);
    }
}
