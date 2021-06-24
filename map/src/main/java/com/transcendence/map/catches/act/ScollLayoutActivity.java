package com.transcendence.map.catches.act;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.transcendence.core.utils.L;
import com.transcendence.core.utils.ScreenUtils;
import com.transcendence.map.catches.view.ScrollLayoutLiuF;
import com.transcendence.map.mobike.main.act.AmapFragmentActivity;

/**
 * @author Joephone on 2019/10/15 16:52
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class ScollLayoutActivity extends AmapFragmentActivity {

    protected ScrollLayoutLiuF mScrollLayout;
    protected RelativeLayout mRlRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    protected void initScrollLayoutView() {
        /**设置 setting*/
        //关闭状态时最上方预留高度
        mScrollLayout.setMinOffset((int) (ScreenUtils.getScreenWidth(this) * 0.1));
        L.d("MinOffset---"+(ScreenUtils.getScreenWidth(this) * 0.1));
        //打开状态时内容显示区域的高度
        mScrollLayout.setMaxOffset((int) (ScreenUtils.getScreenHeight(this) * 0.3));
        L.d("MaxOffset---"+(int) (ScreenUtils.getScreenHeight(this) * 0.3));
        //最低部退出状态时可看到的高度
        mScrollLayout.setExitOffset(ScreenUtils.dp2px(this, 70));
        //是否支持下滑退出，支持会有下滑到最底部时的回调
        mScrollLayout.setIsSupportExit(true);
        mScrollLayout.setAllowHorizontalScroll(false);
        mScrollLayout.setToOpen();
//        mScrollLayout.getBackground().setAlpha(0);

        mRlRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScrollLayout.scrollToExit();
            }
        });
    }
}
