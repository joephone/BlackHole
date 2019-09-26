package com.transcendence.blackhole.ui.scroll.meituantop.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * @blog http://blog.csdn.net/xiaanming
 *
 * @author xiaanming
 */
public class MeiTuanScrollView extends ScrollView {
    private OnScrollListener onScrollListener;

    public MeiTuanScrollView(Context context) {
        this(context, null);
    }

    public MeiTuanScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MeiTuanScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    /**
     * 设置滚动接口
     * @param onScrollListener
     */
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    /**
     *  l, t代表left, top，也就是触摸点相对左上角的偏移量。而oldl, oldt就是滑动前的偏移量。
     * @param l
     * @param t
     * @param oldl
     * @param oldt
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(onScrollListener != null){
            onScrollListener.onScroll(t);
        }
    }

    /**
     *
     * 滚动的回调接口
     * @author xiaanming
     *
     */
    public interface OnScrollListener{
        /**
         * 回调方法， 返回MyScrollView滑动的Y方向距离
         * @param scrollY
         */
        void onScroll(int scrollY);
    }
}
