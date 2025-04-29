package com.transcendence.freeland.basefun.dispatchtouchevent.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.transcendence.core.utils.log.LogUtils;

/**
 * @author Joephone on 2025/3/14 19:03
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class CustomRecyclerView  extends RecyclerView {
    private int startX, startY;

    public CustomRecyclerView(@NonNull Context context) {
        super(context);
    }

    public CustomRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 记录按下时的坐标
                startX = (int) ev.getX();
                startY = (int) ev.getY();
                // 请求父容器不要拦截事件
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int endX = (int) ev.getX();
                int endY = (int) ev.getY();
                int dx = endX - startX; // 水平滑动距离
                int dy = endY - startY; // 垂直滑动距离
                // 判断滑动方向
                if (Math.abs(dx) > Math.abs(dy)) {
                    // 水平滑动，允许父容器（ViewPager）拦截事件
                    LogUtils.d("水平滑动，允许父容器（ViewPager）拦截事件");
                    getParent().requestDisallowInterceptTouchEvent(false);
                } else {
                    // 垂直滑动，禁止父容器拦截事件
                    LogUtils.d("垂直滑动，禁止父容器拦截事件");
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // 恢复默认行为
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
