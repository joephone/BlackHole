package com.transcendence.blackhole.widget.custom.circlemenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * @author Joephone on 2019/8/29 11:49
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class CircleLayout extends ViewGroup {

    public CircleLayout(Context context) {
        this(context,null);
    }

    public CircleLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public CircleLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
