package com.transcendence.blackhole.ui.rv.mi.act;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.ui.rv.mi.adapter.XiaomiAdapter;


public class XiaoMIAct extends TitleBarActivity {

    private RecyclerView mRv;
    private FrameLayout mTopContainer;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_rv_xiaomi_act;
    }

    @Override
    protected void init() {
        setTitle("TitleRv");
        initView();
        initData();
        bindView();
    }

    private void initView() {
        mRv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(manager);
        mTopContainer = (FrameLayout) findViewById(R.id.topContainer);
    }

    private void initData() {
        XiaomiAdapter xiaomiAdapter = new XiaomiAdapter(this);
        mRv.setAdapter(xiaomiAdapter);
    }

    private void bindView() {
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(XiaoMIAct.this, 30));
            FrameLayout preView = null;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //分别设置两个锚点
                FrameLayout currentUpView = (FrameLayout) recyclerView.findChildViewUnder(0, dip2px(XiaoMIAct.this, 0));
                FrameLayout currentLowView = (FrameLayout) recyclerView.findChildViewUnder(0, dip2px(XiaoMIAct.this, 30));
                //初始化preView
                if (dy == 0) {
                    preView = currentUpView;
                }

                //当下移到临界点的时候，标题栏从原view中移到topContainer中
                if (currentLowView != preView || dy > 0) {
                    TextView tv = (TextView) mTopContainer.getChildAt(0);
                    if (null != tv) {
                        mTopContainer.removeView(tv);
                        params.gravity = Gravity.BOTTOM;
                        tv.setLayoutParams(params);
                        preView.addView(tv);
                        //将preView替换成现有的currentLowView
                        preView = currentLowView;
                    }
                }
                //当上移到临界点时
                if (currentUpView != preView || dy < 0) {
                    //上标题栏置底
                    TextView upTv = (TextView) currentUpView.findViewById(R.id.item_text);
                    if (null != upTv) {
                        params.gravity = Gravity.BOTTOM;
                        upTv.setLayoutParams(params);
                    }
                    //下标题栏置顶
                    TextView lowTv = (TextView) mTopContainer.getChildAt(0);
                    if (null != lowTv) {
                        mTopContainer.removeView(lowTv);
                        params.gravity = Gravity.TOP;
                        lowTv.setLayoutParams(params);
                        preView.addView(lowTv);
                    }
                    //将preView替换成现有的currentUpView
                    preView = currentUpView;
                }
                //只有一种情况会让标题栏上浮，就是两个锚点下的view相同的时候
                if (currentUpView.equals(currentLowView)) {
                    TextView tv = (TextView) currentLowView.findViewById(R.id.item_text);
                    if (null != tv) {
                        currentUpView.removeView(tv);
                        params.gravity = Gravity.TOP;
                        tv.setLayoutParams(params);
                        mTopContainer.addView(tv);
                    }
                }
            }
        });
    }


    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue （DisplayMetrics类中属性density）
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


}
