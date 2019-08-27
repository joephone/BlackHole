package com.transcendence.blackhole.ui.scroll.xiaohuoshu.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

/**
 * @author Joephone on 2019/8/5 16:37
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

@SuppressLint("AppCompatCustomView")
public class FitImageView2 extends ImageView {

    public FitImageView2(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
