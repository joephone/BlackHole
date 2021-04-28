package com.transcendence.ui.textview;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * @Author Joephone on 2021/4/13 0013 下午 3:52
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class SmartTextView extends AppCompatTextView {

    public SmartTextView(Context context) {
        this(context, null);
    }

    public SmartTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public SmartTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        // 判断当前有没有设置文本达到自动隐藏和显示的效果
        if (TextUtils.isEmpty(text) && getVisibility() != GONE) {
            setVisibility(GONE);
            return;
        }

        if (getVisibility() != VISIBLE) {
            setVisibility(VISIBLE);
        }
    }



}
