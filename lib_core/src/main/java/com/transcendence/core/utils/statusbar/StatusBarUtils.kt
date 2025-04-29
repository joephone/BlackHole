package com.transcendence.core.utils.statusbar

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager

/**
 * @author Joephone on 2019/9/5 16:24
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc 原作者 senonwx 大大
 * @Edition 1.0
 * @EditionHistory
 */
class StatusBarUtils(private val mActivity: Activity) {
    fun init() {
        fullScreen(mActivity)
        //        if (mColor != -1) {
//            //设置了状态栏颜色
//            addStatusViewWithColor(mActivity, mColor);
//        }
//        if (mDrawable != null) {
//            //设置了状态栏 drawble，例如渐变色
//            addStatusViewWithDrawble(mActivity, mDrawable);
//        }
//        if (isDrawerLayout()) {
//            //未设置 fitsSystemWindows 且是侧滑菜单，需要设置 fitsSystemWindows 以解决 4.4 上侧滑菜单上方白条问题
//            fitsSystemWindows(mActivity);
//        }
//        if (isActionBar()) {
//            //要增加内容视图的 paddingTop,否则内容被 ActionBar 遮盖
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                ViewGroup rootView = (ViewGroup) mActivity.getWindow().getDecorView().findViewById(android.R.id.content);
//                rootView.setPadding(0, getStatusBarHeight(mActivity) + getActionBarHeight(mActivity), 0, 0);
//            }
//        }
    }

    /**
     *
     * 通过设置全屏，设置状态栏透明
     *
     * @param activity
     */
    private fun fullScreen(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                val window = activity.window
                val decorView = window.decorView
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                val option = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                decorView.systemUiVisibility = option
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = Color.TRANSPARENT
                //导航栏颜色也可以正常设置
//                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                val window = activity.window
                val attributes = window.attributes
                val flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                val flagTranslucentNavigation =
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
                attributes.flags = attributes.flags or flagTranslucentStatus
                //                attributes.flags |= flagTranslucentNavigation;
                window.attributes = attributes
            }
        }
    }

    companion object {
        fun with(activity: Activity): StatusBarUtils {
            return StatusBarUtils(activity)
        }
    }
}