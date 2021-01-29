package com.transcendence.core.widget.custom.bar;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

import com.transcendence.core.utils.AppUtils;
import com.transcendence.core.utils.GoweiiStatusBarCompat;
import com.transcendence.core.R;

/**
 * 高拓展性和定制性的ActionBar
 * 整个ActionBar分为3层：
 * ----BackgroundLayer 背景层：可自定义布局
 * ----ActionBarLayer 主体层：改层为垂直线性布局，包含下面三个部分：
 * --------StatusBar：系统状态栏
 * --------TitleBar：位于StatusBar和BottomLine之间，可自定义布局
 * --------BottomLine：分割线
 * ----ForegroundLayer 前景层：可自定义布局
 *
 * @author Cuizhen
 * @date 2018/8/30-上午11:10
 */

public class GoweiiActionBarEx extends FrameLayout {

    private static final int STATUS_BAR_MODE_LIGHT = 0;
    private static final int STATUS_BAR_MODE_DARK = 1;

    /**
     * 沉浸式
     */
    private boolean mAutoImmersion;
    private int mStatusBarHeight;
    private int mStatusBarColor;
    private int mTitleBarLayoutRes;
    private int mTitleBarHeight;
    private int mBottomLineColor;
    private int mBottomLineResId;
    private int mBottomLineHeight;

    private View mStatusBar;
    private FrameLayout mTitleBar;
    private View mBottomLine;

    public GoweiiActionBarEx(@NonNull Context context) {
        this(context,null);
    }

    public GoweiiActionBarEx(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GoweiiActionBarEx(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mStatusBarHeight = GoweiiStatusBarCompat.getStatusViewHeight(context);
        initAttrs(attrs);
        makeImmersion();
        initView();
    }



    protected void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.GoweiiActionBarEx);

        float titleBarHeightDef = getContext().getResources().getDimension(R.dimen.title_bar_height_def);
        float bottomLineHeightDef = getContext().getResources().getDimension(R.dimen.bottom_line_height_def);

        mAutoImmersion = typedArray.getBoolean(R.styleable.GoweiiActionBarEx_ab_autoImmersion,true);


        mStatusBarHeight = typedArray.getInt(R.styleable.GoweiiActionBarEx_ab_statusBarColor,0);
        mStatusBarColor = typedArray.getInt(R.styleable.GoweiiActionBarEx_ab_statusBarColor,0);

        mTitleBarLayoutRes = typedArray.getInt(R.styleable.GoweiiActionBarEx_ab_titleBarLayout,0);
        mTitleBarHeight = (int) typedArray.getDimension(R.styleable.GoweiiActionBarEx_ab_titleBarHeight, titleBarHeightDef);

//        mBottomLineColor = typedArray.getInt(R.styleable.GoweiiActionBarEx_ab_statusBarColor,0);
//        mBottomLineResId = typedArray.getInt(R.styleable.GoweiiActionBarEx_ab_statusBarColor,0);
//        mBottomLineHeight = typedArray.getInt(R.styleable.GoweiiActionBarEx_ab_statusBarColor,0);


        typedArray.recycle();
    }


    /**
     * 设置沉浸式
     */
    private void makeImmersion() {
        hintSystemActionBar();
        refreshStatusBar();
    }

    /**
     * 隐藏默认的ActionBar
     */
    private void hintSystemActionBar() {
        Activity activity = AppUtils.getInstance().getActivity(getContext());
        if (activity == null) {
            return;
        }
        if (activity.getActionBar() != null) {
            activity.getActionBar().hide();
        }
        if (activity instanceof AppCompatActivity) {
            AppCompatActivity compatActivity = (AppCompatActivity) activity;
            if (compatActivity.getSupportActionBar() != null) {
                compatActivity.getSupportActionBar().hide();
            }
        }
    }

    private void refreshStatusBar() {
        Activity activity = AppUtils.getInstance().getActivity(getContext());
        if (activity == null) {
            return;
        }
//        GoweiiStatusBarCompat.setIconMode(activity, mStatusBarDarkMode);
        if (mAutoImmersion) {
            GoweiiStatusBarCompat.transparent(activity);
        } else {
            Window window = activity.getWindow();
            GoweiiStatusBarCompat.setColor(window, mStatusBarColor);
        }
    }

    private void initView() {

    }
}
