package com.transcendence.blackhole.ui.scroll.meituantop.act;

import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.transcendence.blackhole.R;
import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.blackhole.ui.scroll.meituantop.view.MeiTuanScrollView;

/**
 * @blog http://blog.csdn.net/xiaanming
 *
 * @author xiaanming
 */

public class MeiTuanMainActivity extends TitleBarActivity implements MeiTuanScrollView.OnScrollListener {
    /**
     * 自定义的MyScrollView
     */
    private MeiTuanScrollView mScrollView;
    /**
     * 在MyScrollView里面的购买布局
     */
    private LinearLayout mBuyLayout;
    /**
     * 位于顶部的购买布局
     */
    private LinearLayout mTopBuyLayout;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_scroll_meituan_main;
    }

    @Override
    protected void init() {
        setTitle("美团锚定");
        mScrollView = (MeiTuanScrollView) findViewById(R.id.scrollView);
        mBuyLayout = (LinearLayout) findViewById(R.id.buy);
        mTopBuyLayout = (LinearLayout) findViewById(R.id.top_buy_layout);

        mScrollView.setOnScrollListener(this);

        // 当布局的状态或者控件的可见性发生改变回调的接口
        findViewById(R.id.parent_layout).getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        // 这一步很重要，使得上面的购买布局和下面的购买布局重合
                        onScroll(mScrollView.getScrollY());

                        System.out.println(mScrollView.getScrollY());
                    }
                });
    }


    @Override
    public void onScroll(int scrollY) {
        int mBuyLayout2ParentTop = Math.max(scrollY, mBuyLayout.getTop());
        mTopBuyLayout.layout(0, mBuyLayout2ParentTop, mTopBuyLayout.getWidth(),
                mBuyLayout2ParentTop + mTopBuyLayout.getHeight());
    }
}
