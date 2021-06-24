package com.transcendence.core.widget.custom.autoscroll;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

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
