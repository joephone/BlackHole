package com.transcendence.ui.scroll.autoscroll;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author Joephone on 2020/4/22 1:40
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class AutoScrollView extends ScrollView {
    public AutoScrollView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public AutoScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AutoScrollView(Context context) {
        super(context);
        init();
    }

    private Timer timer;

    private void init() {
        timer = new Timer();
        timer.schedule(timeTask, 100,30);      //period 速率
    }

    private TimerTask timeTask = new TimerTask() {

        @Override
        public void run() {
            int S = computeVerticalScrollRange()/2+getMeasuredHeight() ;
            int A = getScrollY() + getHeight() ;
//            L.d("S--"+S);
//            L.d("A--"+A);
            if (S - A <= 3)
                scrollTo(0, 0);
            smoothScrollBy(0, 2);
        }
    };
}
