package com.transcendence.blackhole.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author CuiZhen
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class GoweiiStatusBarCompat {

    public static int getStatusViewHeight(@NonNull Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        try {
            return context.getResources().getDimensionPixelSize(resourceId);
        } catch (Exception e) {
            return 0;
        }
    }

    public static void setColor(@NonNull Fragment fragment, @ColorInt int color) {
        Activity activity = fragment.getActivity();
        if (activity == null) {return;}
        setColor(activity, color);
    }



    public static void setColor(@NonNull Activity activity, @ColorInt int color) {
        Window window = activity.getWindow();
        if (window == null) {return;}
        setColor(window, color);
    }

    public static void setColor(@NonNull Window window, @ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }


    /**
     * 设置状态栏透明
     */
    public static void transparent(Activity activity) {
        //API21 以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            activity.getWindow()
                    .getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow()
                    .setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }



}
