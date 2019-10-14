package com.transcendence.blackhole.ui.scroll.catchesmap.act;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.BaseActivity;
import com.transcendence.blackhole.ui.scroll.catchesmap.view.ScrollLayoutLiuF;
import com.transcendence.blackhole.utils.L;
import com.transcendence.blackhole.utils.ScreenUtils;

/**
 * @author Joephone on 2019/10/12 16:02
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class CatchesMapActivity extends BaseActivity {

    private ScrollLayoutLiuF mScrollLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scroll_catches_map_scroll;
    }

    @Override
    protected void init() {
        mScrollLayout = findViewById(R.id.scrollLayout);

        initScrollLayoutView();
    }


    /**
     * 设置底部抽屉菜单
     */
    private void initScrollLayoutView() {
        /**设置 setting*/
        //关闭状态时最上方预留高度
        mScrollLayout.setMinOffset((int) (ScreenUtils.getScreenHeight(this) * 0.1));
        //打开状态时内容显示区域的高度
        mScrollLayout.setMaxOffset((int) (ScreenUtils.getScreenHeight(this) * 0.3));
        //最低部退出状态时可看到的高度 //dip2px
        mScrollLayout.setExitOffset(ScreenUtils.dp2px(this, 70));
        //是否支持下滑退出，支持会有下滑到最底部时的回调
        mScrollLayout.setIsSupportExit(true);
        mScrollLayout.setAllowHorizontalScroll(false);
        mScrollLayout.setToOpen();
        mScrollLayout.getBackground().setAlpha(0);
//        rootRel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mScrollLayout.scrollToExit();
//            }
//        });

        mScrollLayout.setOnScrollChangedListener(mOnScrollChangedListener);
    }



    private ScrollLayoutLiuF.OnScrollChangedListener mOnScrollChangedListener =
            new ScrollLayoutLiuF.OnScrollChangedListener() {
                @Override
                public void onScrollProgressChanged(float currentProgress) {
                    if (currentProgress >= 0) {
                        if (currentProgress < 0.5) {
//                            if (back.getVisibility() != View.GONE) {
//                                back.setVisibility(View.GONE);
//                            }
                        } else {
//                            if (back.getVisibility() != View.VISIBLE) {
//                                back.setVisibility(View.VISIBLE);
//                            }
                        }
                        float precent = 255 * currentProgress;
                        if (precent > 255) {
                            precent = 255;
                        } else if (precent < 0) {
                            precent = 0;
                        }
                        mScrollLayout.getBackground().setAlpha(255 - (int) precent);
                    } else {
//                        if (back.getVisibility() != View.VISIBLE) {
//                            back.setVisibility(View.VISIBLE);
//                        }
                    }
                }

                @Override
                public void onScrollFinished(ScrollLayoutLiuF.Status currentStatus) {
                    L.d("currentStatus:" + currentStatus.name());
//                    if (myHasFocus&&searchEt.isFocused()){
//                        if (flag>1){
//                            searchEt.setFocusable(true);
//                            searchEt.setFocusableInTouchMode(true);
//                            searchEt.clearFocus();
//                        }else {
//                            flag=flag+1;
//                        }
//                    }
                    switch ( currentStatus ) {
                        case CLOSED://地图全部被盖住
                            break;
                        case OPENED://地图被盖住一半
//                            switch (nowType){
//                                case history:
//                                case menu:
//                                    searchCancel.setVisibility(View.GONE);
//                                    mapMenuAdapter(AllAdapter.AllAdapterUIType.history);
//                                    break;
//                            }
                            break;
                        case EXIT://地图底部被盖住
//                            back.setVisibility(View.VISIBLE);
//                            laySearch.setVisibility(View.VISIBLE);
                            break;
                    }

                }

                @Override
                public void onChildScroll(int top) {
                }
            };
}
