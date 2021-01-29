package com.transcendence.core.widget.custom.autoscroll;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * 类名：ScrollTextView
 * 描述：自动滚动的TextView,跑马灯效果
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @author Yun.Lei on 2017年6月21日
 */
public class MarqueeTextView extends AppCompatTextView {
    public MarqueeTextView(Context context) {
        super(context);
        initView();
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        //设置跑马灯滚动
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        //设置滚动次数
        setMarqueeRepeatLimit(10);
        setSingleLine(true);
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
