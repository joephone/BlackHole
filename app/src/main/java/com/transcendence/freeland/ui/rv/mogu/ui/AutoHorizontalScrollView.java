package com.transcendence.freeland.ui.rv.mogu.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class AutoHorizontalScrollView extends HorizontalScrollView{

    public AutoHorizontalScrollView(Context context) {
        super(context);
    }

    public AutoHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 当item过多时,主屏幕显示不了,就重置item的width的位置,并让下一个item显示在屏幕的一半
     * @param index
     */
    public void resetScrollWidth(int index) {
        ViewGroup parent = (ViewGroup) getChildAt(0);
        if (index < 0 || index >= parent.getChildCount()) {
            return;
        }
        View view;
        int left = 0;
        for (int i = 0; i < index; i++) {
            view = parent.getChildAt(i);
            view.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            left += view.getMeasuredWidth();
        }
        view = parent.getChildAt(index);
        view.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int right = left + view.getMeasuredWidth();

        if (right < getWidth()/ 2) {
            this.smoothScrollTo(0, 0);
        }
        else {
            this.smoothScrollTo(right - (getWidth()/ 2), 0);
        }
    }
}
