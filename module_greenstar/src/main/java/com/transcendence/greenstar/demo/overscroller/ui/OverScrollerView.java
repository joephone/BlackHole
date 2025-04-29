package com.transcendence.greenstar.demo.overscroller.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.OverScroller;

import androidx.annotation.Nullable;

/**
 * @author joephone
 * @date 2025/4/9
 * @desc OverScroller 是 Android 提供的⼀个⽤于处理滚动和惯性滚动动画的⼯具类，它可以帮助开发者实现平滑的滚动效果。
 */
public class OverScrollerView extends View {
    private OverScroller mScroller;
    private VelocityTracker mVelocityTracker;
    private float mLastX;
    private float mLastY;

    // 滚动边界
    private int minX = 0;
    private int maxX = 1000;
    private int minY = 0;
    private int maxY = 1000;

    private Paint mPaint;
    private static final int RECT_COUNT = 20;
    private static final int RECT_SIZE = 200;

    public OverScrollerView(Context context) {
        this(context, null);
    }

    public OverScrollerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }



    public OverScrollerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 初始化 OverScroller 对象
        mScroller = new OverScroller(context);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);

        // 设置内容尺寸（示例：20个方块，每个200x200）
        maxX = RECT_COUNT * RECT_SIZE - getResources().getDisplayMetrics().widthPixels;
        maxY = RECT_COUNT * RECT_SIZE - getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制可滚动内容（红色方块网格）
        for (int i = 0; i < RECT_COUNT; i++) {
            for (int j = 0; j < RECT_COUNT; j++) {
                float left = i * RECT_SIZE;
                float top = j * RECT_SIZE;
                canvas.drawRect(
                        left, top,
                        left + RECT_SIZE * 0.9f,
                        top + RECT_SIZE * 0.9f,
                        mPaint
                );
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        // 初始化速度追踪器
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 如果滚动动画还未结束，停止动画
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                // 记录按下时的坐标
                mLastX = x;
                mLastY = y;
                return true;

            case MotionEvent.ACTION_MOVE:
                // 计算手指移动的距离
                float dx = mLastX - x;
                float dy = mLastY - y;
                // 调用 scrollBy 方法进行滚动
                scrollBy((int) dx, (int) dy);
                // 更新上次触摸的坐标
                mLastX = x;
                mLastY = y;
                break;

            case MotionEvent.ACTION_UP:
                // 计算速度，单位：像素/秒
                mVelocityTracker.computeCurrentVelocity(1000);
                float velocityX = mVelocityTracker.getXVelocity();
                float velocityY = mVelocityTracker.getYVelocity();

                // 处理惯性滚动
                mScroller.fling(
                        getScrollX(), getScrollY(),
                        (int) velocityX, (int) velocityY,
                        minX, maxX,
                        minY, maxY,
                        100, 100 // 过度滚动距离
                );

                // 释放速度追踪器
                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }

                // 触发重绘，以便调用 computeScroll 方法
                invalidate();
                break;

            case MotionEvent.ACTION_CANCEL:
                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        // 检查 OverScroller 是否还有滚动偏移量需要计算
        if (mScroller.computeScrollOffset()) {
            // 获取当前的滚动位置
            int currX = mScroller.getCurrX();
            int currY = mScroller.getCurrY();
            // 调用 scrollTo 方法将视图滚动到当前位置
            scrollTo(currX, currY);
            // 再次触发重绘，直到滚动动画结束
            invalidate();
        }
    }

    // 设置滚动边界
    public void setScrollBounds(int minX, int maxX, int minY, int maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    // 在 OverScrollerView 类中添加这个方法
    public void smoothScrollTo(int destX, int destY) {
        int startX = getScrollX();
        int startY = getScrollY();

        // 使用 OverScroller 实现平滑滚动
        mScroller.startScroll(
                startX,          // 起始X位置
                startY,          // 起始Y位置
                destX - startX,  // X方向偏移量
                destY - startY,  // Y方向偏移量
                1000             // 动画持续时间(毫秒)
        );

        // 触发滚动
        invalidate();
    }
}