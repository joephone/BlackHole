package com.transcendence.blackhole.ui.scroll;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.transcendence.blackhole.utils.L;

/**
 * @Author Joephone on 2020/4/21 23:38
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class ScrollEndLessView extends ScrollView {

    private boolean scrolledToTop = true;
    private boolean scrolledToBottom = false;
    private int paddingTop = 0;
    private final int MSG_SCROLL = 10;
    private final int MSG_SCROLL_Loop = 11;
    /**
     *  是否能滑动
     */
    private boolean mScrollAble = true;
    //是否自动滚动
    private boolean autoToScroll = true;
    /**
     * 是否循环滚动
     */
    private boolean mScrollLoop = false;
    /**
     * 多少秒后开始滚动，默认5秒
     */
    private int fistTimeScroll = 10;
    /**
     * 多少毫秒滚动一个像素点
     */
    private int scrollRate = 500;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SCROLL:
                    //能否滚动 与 是否自动滚动
                    if (mScrollAble && autoToScroll) {
                        //发生偏移
                        scrollTo(0, paddingTop);
                        paddingTop += 1;
                        mHandler.removeMessages(MSG_SCROLL);
                        mHandler.sendEmptyMessageDelayed(MSG_SCROLL, scrollRate);
                    }
                    break;
                case MSG_SCROLL_Loop:
                    paddingTop = 0;
                    autoToScroll = true;
                    mHandler.sendEmptyMessageDelayed(MSG_SCROLL, scrollRate);
                    break;
                default:
                    break;

            }

        }
    };

    public ScrollEndLessView(Context context) {
        this(context, null);
    }

    public ScrollEndLessView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollEndLessView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ScrollEndLessView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }


    private ISmartScrollChangedListener mSmartScrollChangedListener;

    /**
     * 定义监听接口
     */
    public interface ISmartScrollChangedListener {
        //滑动到底部
        void onScrolledToBottom();
        //滑动到顶部
        void onScrolledToTop();

    }


    /**
     * 设置滑动到顶部或底部的监听
     * @param smartScrollChangedListener
     */
    public void setScanScrollChangedListener(ISmartScrollChangedListener smartScrollChangedListener) {
        mSmartScrollChangedListener = smartScrollChangedListener;
    }

    /**
     * ScrollView内的视图进行滑动时的回调方法，据说是API 9后都是调用这个方法，但是我测试过并不准确
     */
//    @Override
//    protected void onOverScrolled(int scrollXaxis, int scrollYaxis, boolean clampedXaxis, boolean clampedYaxis) {
//        super.onOverScrolled(scrollXaxis, scrollYaxis, clampedXaxis, clampedYaxis);
//        if (scrollYaxis == 0) {
//            scrolledToTop = clampedYaxis;
//            scrolledToBottom = false;
//        } else {
//            scrolledToTop = false;
//            scrolledToBottom = clampedYaxis;//系统回调告诉你什么时候滑动到底部
//        }
//
//        notifyScrollChangedListeners();
//    }



    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        // l oldl 分别代表水平位移
        // t oldt 代表当前左上角距离Scrollview顶点的距离
        /**
         * 第一个参数为变化后的X轴位置
           第二个参数为变化后的Y轴的位置
            第三个参数为原先的X轴的位置
            第四个参数为原先的Y轴的位置
         */
        super.onScrollChanged(l, t, oldl, oldt);
        L.d("t--"+t);
        L.d("oldt--"+oldt);
        L.d("height--"+getHeight());
        if(t>0){
            scrolledToTop = false;
        }
        if(getHeight() -t <= 2){
            L.d("到底啦--");
            scrolledToBottom = true;
        }

        notifyScrollChangedListeners();
    }




    /**
     * 判断是否滑到底部
     */
    private void notifyScrollChangedListeners() {
        L.d("notifyScrollChangedListeners");
        if (scrolledToTop) {
            L.d("scrolledToTop == true");
//            if (mSmartScrollChangedListener != null) {
//                mSmartScrollChangedListener.onScrolledToTop();
//            }
        } else if (scrolledToBottom) {
            L.d("scrolledToBottom == true");
            scrolledToBottom = false;
            scrolledToTop = true;
            mHandler.removeMessages(MSG_SCROLL);
            if (!mScrollLoop) {
                mScrollAble = false;
            }

            if (mSmartScrollChangedListener != null) {
                mSmartScrollChangedListener.onScrolledToTop();
            }
            if (mScrollLoop) {
                mHandler.sendEmptyMessageDelayed(MSG_SCROLL_Loop, fistTimeScroll);
            }
        }
    }


    /**
     * 设置是否可以滚动
     *
     * @param autoToScroll
     */
    public void setAutoToScroll(boolean autoToScroll) {
        this.autoToScroll = autoToScroll;
    }

    /**
     * 设置开始滚动的时间
     *
     * @param fistTimeScroll
     */
    public void setFistTimeScroll(int fistTimeScroll) {
        this.fistTimeScroll = fistTimeScroll;
        mHandler.removeMessages(MSG_SCROLL);
        mHandler.sendEmptyMessageDelayed(MSG_SCROLL, fistTimeScroll);
    }

    /**
     * 设置滚动的速度
     *
     * @param scrollRate
     */
    public void setScrollRate(int scrollRate) {
        this.scrollRate = scrollRate;
    }

    /**
     * 设置是否循环滚动
     *
     * @param scrollLoop
     */
    public void setScrollLoop(boolean scrollLoop) {
        this.mScrollLoop = scrollLoop;
    }


}
