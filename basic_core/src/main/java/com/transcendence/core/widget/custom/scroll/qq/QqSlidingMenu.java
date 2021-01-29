package com.transcendence.core.widget.custom.scroll.qq;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.transcendence.core.utils.L;
import com.transcendence.core.utils.ScreenUtils;

/**
 * @author Joephone on 2019/8/14 16:45
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class QqSlidingMenu extends HorizontalScrollView {
    private int mScreenWidth;
    private int mMenuWidth;
    private LinearLayout mScrollView;
    private ViewGroup mLeftView;
    private ViewGroup mRigthView;

    public QqSlidingMenu(Context context, AttributeSet attrs) {
        super(context,attrs);
        mScreenWidth = ScreenUtils.getScreenWidth(context);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mScrollView = (LinearLayout) getChildAt(0);
        mLeftView = (ViewGroup) mScrollView.getChildAt(0);
        mRigthView = (ViewGroup) mScrollView.getChildAt(1);

        //设置菜单宽度
        mMenuWidth = mLeftView.getLayoutParams().width = mScreenWidth;
        //设置主主显示区宽度
        mRigthView.getLayoutParams().width = mScreenWidth;
    }

    /**
     * 设置初始状态
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(changed){
            //滚动条向右移动，主显示区向左移动
            this.scrollTo(mMenuWidth, 0);
        }
    }

    /**
     * 设置滑动状态
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            //手指抬起
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                if (scrollX>=mMenuWidth/2){
                    this.scrollTo(mMenuWidth, 0);
                }else{
                    this.scrollTo(0,0);
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 滑动事件
     */
    @Override
    public void fling(int velocityX) {
        L.d("velocityX:"+velocityX);
        super.fling(velocityX/4);
    }
}
