package com.transcendence.blackhole.widget.custom.autoscroll;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class MarqueTextViewTwo extends AppCompatTextView {

    public MarqueTextViewTwo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public MarqueTextViewTwo(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public MarqueTextViewTwo(Context context) {
        super(context);

    }

    @Override

    public boolean isFocused() {

        return true;
    }
}
