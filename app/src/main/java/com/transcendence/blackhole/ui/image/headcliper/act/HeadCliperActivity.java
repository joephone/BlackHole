package com.transcendence.blackhole.ui.image.headcliper.act;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;

/**
 * @author Joephone on 2019/11/5 11:37
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc 裁剪
 * @Edition 1.0
 * @EditionHistory
 */

public class HeadCliperActivity extends TitleBarActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_headcliper_cliper;
    }

    @Override
    protected void init() {
        setTitle("移动与缩放");
    }
}
