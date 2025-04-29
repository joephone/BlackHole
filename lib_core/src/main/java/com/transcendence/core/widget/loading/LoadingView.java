package com.transcendence.core.widget.loading;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.transcendence.core.R;

/**
 * @author joephone
 * @date 2023/3/23
 * @desc
 */
public class LoadingView extends FrameLayout {

    private int loadingLayoutId;
    private View loadingView;

    public LoadingView(@NonNull Context context) {
        this(context, null);
    }

    public LoadingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // R.styleable.LoadLayout 对应attrs.xml中 name="LoadLayout"
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LoadLayout);
        //  R.styleable.LoadLayout_loadingView  对应attrs.xml中 name="loadingView"
        loadingLayoutId = a.getResourceId(R.styleable.LoadLayout_loadingView, R.layout.loading_greenstar);
        loadingView = LayoutInflater.from(getContext()).inflate(loadingLayoutId, null);
        a.recycle();
    }


    /**
     * 布局加载完成后隐藏所有View
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        for (int i = 0; i < getChildCount() - 1; i++) {
            getChildAt(i).setVisibility(GONE);
        }
    }

    public void showLoading() {
        //VISIBLE    0  可见
        if(loadingView != null){
            removeView(loadingView);
            loadingView.setVisibility(VISIBLE);
            addView(loadingView);
        }
    }

    public void hideLoading(){
        //不可见也不占用布局空间 8  不可见也不占用布局
        if(loadingView != null){
            removeView(loadingView);
            loadingView.setVisibility(GONE);
        }
    }


}
