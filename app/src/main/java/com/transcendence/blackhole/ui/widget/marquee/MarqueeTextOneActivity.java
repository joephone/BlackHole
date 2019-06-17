package com.transcendence.blackhole.ui.widget.marquee;

import android.view.View;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.widget.custom.autoscroll.MarqueeText;

/**
 * @author Joephone on 2019/6/17 10:27
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class MarqueeTextOneActivity extends TitleBarActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_widget_marquee_one;
    }

    @Override
    public void init() {
        setTitle("MarqueeTextOne");
        test = (MarqueeText) findViewById(R.id.tv);
        test.startScroll();
    }


    private MarqueeText test;


    public void start(View v) {

    }

    public void stop(View v) {
        test.stopScroll();
    }

    public void startFor0(View v) {
        test.startFor0();
    }
}
