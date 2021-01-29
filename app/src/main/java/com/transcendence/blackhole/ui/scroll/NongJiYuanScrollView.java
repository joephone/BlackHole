package com.transcendence.blackhole.ui.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.transcendence.core.utils.L;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author Joephone on 2020/4/22 2:40
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class NongJiYuanScrollView extends ScrollView {

    public NongJiYuanScrollView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public NongJiYuanScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NongJiYuanScrollView(Context context) {
        super(context);
        init();
    }

    private Timer timer;

    private void init() {
        timer = new Timer();
        timer.schedule(timeTask, 1000,100);
    }

    private TimerTask timeTask = new TimerTask() {

        @Override
        public void run() {
            int S = computeVerticalScrollRange()/2+getMeasuredHeight() ;
            int A = getScrollY() + getHeight() ;
            L.d("S--"+S);
            L.d("A--"+A);
            if (S - A <= 3)
                scrollTo(0, 0);
            smoothScrollBy(0, 2);
        }
    };

}
