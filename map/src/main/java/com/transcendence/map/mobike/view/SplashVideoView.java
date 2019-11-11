package com.transcendence.map.mobike.view;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.VideoView;

/**
 * @author Joephone on 2019/11/6 15:20
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class SplashVideoView extends VideoView {

    public SplashVideoView(Context context) {
        super(context);
    }

    public SplashVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SplashVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(View.MeasureSpec.getSize(widthMeasureSpec), View.MeasureSpec.getSize(heightMeasureSpec));
    }


    public void play(Uri uri){
        if (uri == null) {
            throw new IllegalArgumentException("Uri can not be null");
        }
        //设置播放路径
        setVideoURI(uri);
        //开始播放
        start();
        //设置循环

    }
}
