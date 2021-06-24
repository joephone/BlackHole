package com.transcendence.wan.utils;

import android.content.res.Resources;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Field;

/**
 * @Author Joephone on 2020/4/27 1:24
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class AppUtils {


    /**
     * 设置tablaout的下划线宽度
     * @param tabs
     * @param leftDip
     * @param rightDip
     */
    public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            //API28下的TabLayout 源码，mTabStrip改名为slidingTabIndicator
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                tabStrip = tabLayout.getDeclaredField("slidingTabIndicator");
            }else {
                tabStrip = tabLayout.getDeclaredField("mTabStrip");
            }

            tabStrip.setAccessible(true);
            LinearLayout llTab = null;
            try {
                llTab = (LinearLayout) tabStrip.get(tabs);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
            int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

            for (int i = 0; i < llTab.getChildCount(); i++) {
                View tabView = llTab.getChildAt(i);

                Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                mTextViewField.setAccessible(true);
                TextView mTextView = null;
                try {
                    mTextView = (TextView) mTextViewField.get(tabView);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                tabView.setPadding(0, 0, 0, 0);

                //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                int width = 0;
                width = mTextView.getWidth();
                if (width == 0) {
                    mTextView.measure(0, 0);
                    width = mTextView.getMeasuredWidth();
                }

                LinearLayout.LayoutParams params =  (LinearLayout.LayoutParams) tabView.getLayoutParams();//new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
//                params.leftMargin = left;
//                params.rightMargin = right;
                params.leftMargin = (tabView.getMeasuredWidth() - width) / 2;
                params.rightMargin = (tabView.getMeasuredWidth() - width) / 2;

                tabView.setLayoutParams(params);
                tabView.invalidate();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }



    public static void setTextViewExtent(TabLayout.Tab tabView) {
        try {
            Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");


            //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
            mTextViewField.setAccessible(true);
            TextView mTextView = null;
            try {
                mTextView = (TextView) mTextViewField.get(tabView);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


            mTextView.setTextSize(40);
            tabView.setCustomView(mTextView);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }
}
