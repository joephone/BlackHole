package com.transcendence.blackhole.demo.translationbehavior.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2018/3/3.
 * desciption : 效果就是向上滑动列表底部 FloatingActionButton上来，
 *              向下滑动列表底部 FloatingActionButton 隐藏
 */
public class TranslationBehavior extends FloatingActionButton.Behavior {


    // NoSuchMethodException: <init> [class android.content.Context, interface android.util.AttributeSet]
    public TranslationBehavior(Context context , AttributeSet attributeSet){
        super(context , attributeSet);
    }


    // 在这里，我们只需要关注垂直滚动，而且在向上的时候，底部button出来，向下滚动就隐藏


    /**
     *  当coordinatorLayout的子view试图开始嵌套滚动的时候被调用，当返回true的时候表明
     *  coordinatorLayout 充当nested scroll parent处理这次滑动，这里需要注意的是只有当返回为true的时候，
     *  Behavior才能收到后面一些事件的回调，比如onNestedPreScroll、onNestedScroll等
     *  这里有个重要的参数就是nestedScrollAxes ，表示处理滑动的方向
     *
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes    嵌套滚动  应用的滑动方向
     * @return
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;   //表示是垂直滚动
    }


    boolean isOut = false ;  //默认显示在上边
    /**
     * 正在滚动中
     *
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dxConsumed    已经消费的x方向的距离
     * @param dyConsumed    已经消费的y方向的距离
     * @param dxUnconsumed  x方向剩余的滚动距离
     * @param dyUnconsumed  y方向剩余的滚动距离
     */
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

        // 经过打印测试结果可知 往上边滑动dyConsumed永远大于0 ，dyUnconsumed永远等于0
        Log.e("TAG" , "dyConsumed -> " + dyConsumed + ", " + "dyUnconsumed -> " + dyUnconsumed) ;

        //往上边滑动
        if (dyConsumed > 0){
            if (!isOut){

            // 向上滑动列表底部 FloatingActionButton隐藏
            int translationY = ((CoordinatorLayout.LayoutParams)child.getLayoutParams()).bottomMargin + child.getMeasuredHeight() ;
            child.animate().translationY(translationY).setDuration(500).start();
                isOut = true ;               
            }
        }else{
            if (isOut) {
                // 向下滑动列表底部 FloatingActionButton上来显示
                child.animate().translationY(0).setDuration(500).start();
                isOut = false ;
            }
        }
    }
}
