package com.transcendence.blackhole.ui.image.act;

import android.util.DisplayMetrics;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.ui.scroll.ScrollEndLessView;

/**
 * @Author Joephone on 2020/4/21 22:50
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class ImageAutoCycleOneActivity extends TitleBarActivity implements ScrollEndLessView.ISmartScrollChangedListener{

//    private ImageView mIv;
    private ScrollEndLessView mScrollView;
//    private AutoScrollView asv;
    private int ScreenWidth,ScreenHeight;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ui_image_auto_cycle_one;
    }

    @Override
    protected void init() {
        setTitle("背景循环");
        mScrollView = findViewById(R.id.scrollView);
//        asv = findViewById(R.id.asv);
//        mIv = findViewById(R.id.iv);
        /*获取屏幕宽高*/
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        ScreenWidth = dm.widthPixels;
        ScreenHeight = dm.heightPixels;

//        measureAndlayout();

//        cycle();

//        mScrollView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mScrollView.fullScroll(HorizontalScrollView.FOCUS_DOWN);
//            }
//        },2000);

        mScrollView.setScanScrollChangedListener(this);
        //滚动条自动滚动
        mScrollView.setAutoToScroll(true);
        //开始滚动时间
        mScrollView.setFistTimeScroll(500);
        //滚动的速率
        mScrollView.setScrollRate(30);
        //是否循环滑动
        mScrollView.setScrollLoop(true);


//        asv.setStartScrolled(true);

    }

    @Override
    public void onScrolledToBottom() {

    }

    @Override
    public void onScrolledToTop() {
        mScrollView.scrollTo(0,0);
    }

//    private void measureAndlayout() {
//        int height = ScreenWidth * (16/9);
//        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mIv.getLayoutParams();
//        layoutParams.width = ScreenWidth;
//        layoutParams.height = height;
//        mIv.setLayoutParams(layoutParams);
//    }


}
