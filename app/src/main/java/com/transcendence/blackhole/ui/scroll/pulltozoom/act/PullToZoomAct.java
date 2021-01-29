package com.transcendence.blackhole.ui.scroll.pulltozoom.act;

import com.transcendence.blackhole.R;
import com.transcendence.core.base.activity.TitleBarActivity;

/**
 * @author Joephone on 2019/9/26 16:52
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class PullToZoomAct extends TitleBarActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_scroll_pulltozoom;
    }

    @Override
    protected void init() {
        setTitle("PullToZoom");
    }
}
