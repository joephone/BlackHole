package com.transcendence.blackhole.widget.custom.banner.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Joephone on 2018/4/9 15:26
 * E-Mail Address：joephonechen@gmail.com
 */

public class BannerNoScrollViewPager extends ViewPager {
    private boolean noScroll = false;

    public BannerNoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
// TODO Auto-generated constructor stub
    }

    public BannerNoScrollViewPager(Context context) {
        super(context);
    }

    /**
     * 供外部调用的方法:设置是否可以滑动
     *
     * @param noScroll true:表示禁止滑动,false:表示可以滑动
     */
    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
/* return false;//super.onTouchEvent(arg0); */
        if (noScroll) {
            return false;
        }
        else {
            return super.onTouchEvent(arg0);
        }
    }

    /**
     * 返回true:表示拦截
     * 返回false:表示不拦截
     *
     * @param arg0
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (noScroll) {
            return false;
        }
        else {
            return super.onInterceptTouchEvent(arg0);
        }
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }
}
