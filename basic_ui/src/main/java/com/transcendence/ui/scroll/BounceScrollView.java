package com.transcendence.ui.scroll;

/**
 * Created by wangjitao on 2016/10/19 0019.
 * 支持上下反弹效果的ScrollView
 * 下面是实现的思路
 * 1，首先通过onFinishInflate（）方法拿到该ScrollView中的View（）也就是我们后面布局文件中的LinearLayout
 * 2，通过onTouchEvent（）拿到用户的滑动时间，在ACTION_MOVE方法里面进行判断，调用isNeedMove（）方法判断View是否滑动到
 * 顶部或者底部，若是的话用Rect对象记录原先View的状态，
 * 对应的代码是： rect.set(view.getLeft(), view.getTop(),view.getRight(), view.getBottom());
 * 然后将View向下平移 1/2用户滑动的距离 ，对应的代码如下：
 * view.layout(view.getLeft(), view.getTop() - deltaY / 2,view.getRight(), view.getBottom() - deltaY / 2);
 * 3，在用户抬起手指的时候通过判断rect.isEmpty()来判断是否需要弹回动画，如果需要，则调用回缩动画
 */

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

public class BounceScrollView extends ScrollView {
    // 孩子View （也就是我们后面布局文件中的LinearLayout）
    private View view;

    // 点击时y坐标 （点击屏幕的时候Y坐标）
    private float y;

    // 矩形(用于记录平移前View的大小，只是用于判断是否需要动画.)
    private Rect rect = new Rect();

    // 是否开始计算（默认设置成false）
    private boolean isCount = false;

    public BounceScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /***
     * 根据 XML 生成视图工作完成.该函数在生成视图的最后调用，在所有子视图添加完之后. 即使子类覆盖了 onFinishInflate
     * 方法，也应该调用父类的方法，使该方法得以执行.
     */
    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            //这里为什么是获取到第0个孩子的View呢？ 因为scrollView只能包含一个View
            view = getChildAt(0);
            super.onFinishInflate();
        }
    }

    /***
     * 监听touch
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (view != null) {
            commOnTouchEvent(ev);
        }

        return super.onTouchEvent(ev);
    }

    /***
     * 触摸事件
     *
     * @param ev
     */
    public void commOnTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                // 手指松开，判断是否到达顶部之类的
                if (isNeedAnimation()) {
                    animation();
                    isCount = false;
                }
                break;
            /***
             * 排除出第一次移动计算，因为第一次无法得知y坐标， 在MotionEvent.ACTION_DOWN中获取不到，
             * 因为此时是MyScrollView的touch事件传递到到了LIstView的孩子item上面.所以从第二次计算开始.
             * 然而我们也要进行初始化，就是第一次移动的时候让滑动距离归0. 之后记录准确了就正常执行.
             *
             */
            case MotionEvent.ACTION_MOVE:
                final float preY = y;// 按下时的y坐标
                float nowY = ev.getY();// 时时y坐标
                int deltaY = (int) (preY - nowY);// 滑动距离
                if (!isCount) {
                    deltaY = 0; // 在这里要归0.
                }

                y = nowY;
                // 当滚动到最上或者最下时就不会再滚动，这时移动布局
                if (isNeedMove()) {
                    // 初始化头部矩形
                    if (rect.isEmpty()) {
                        // 保存正常的布局位置
                        rect.set(view.getLeft(), view.getTop(),
                                view.getRight(), view.getBottom());
                    }
                    // 移动布局（布局下滑的局势是手势花花的距离的一半）
                    view.layout(view.getLeft(), view.getTop() - deltaY / 2,
                            view.getRight(), view.getBottom() - deltaY / 2);
                }
                isCount = true;
                break;

            default:
                break;
        }
    }

    /***
     * 回缩动画
     */
    public void animation() {
        // 开启移动动画（向上、下平移已经平移的距离）
        TranslateAnimation ta = new TranslateAnimation(0, 0, view.getTop(),
                rect.top);
        ta.setDuration(200);
        view.startAnimation(ta);
        // 设置回到正常的布局位置
        view.layout(rect.left, rect.top, rect.right, rect.bottom);

        rect.setEmpty();

    }

    // 是否需要开启动画（通过判断rect对象）
    public boolean isNeedAnimation() {
        return !rect.isEmpty();
    }

    /***
     * 是否需要移动布局 inner.getMeasuredHeight():获取的是控件的总高度
     * getHeight()：获取的是屏幕的高度
     */
    public boolean isNeedMove() {
        int offset = view.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        // 0是顶部，后面那个是底部
        if (scrollY == 0 || scrollY == offset) {
            return true;
        }
        return false;
    }
}
