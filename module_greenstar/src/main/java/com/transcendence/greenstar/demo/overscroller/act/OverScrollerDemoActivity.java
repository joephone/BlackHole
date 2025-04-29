package com.transcendence.greenstar.demo.overscroller.act;

import android.widget.Button;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.greenstar.R;
import com.transcendence.greenstar.demo.overscroller.ui.OverScrollerView;

/**
 * @author joephone
 * @date 2025/4/9
 * @desc OverScroller 实例调用
 */
public class OverScrollerDemoActivity extends AppAc {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo_overscroller;
    }

    @Override
    protected void initView() {
        setTitle("过渡滚动器");
        OverScrollerView overScrollerView = findViewById(R.id.overScrollerView);

        // 调试按钮：触发滚动到指定位置
        Button btnScroll = findViewById(R.id.btn_scroll);
        btnScroll.setOnClickListener(v -> {
            overScrollerView.smoothScrollTo(1000, 1000);
        });

        // 调试按钮：重置位置
        Button btnReset = findViewById(R.id.btn_reset);
        btnReset.setOnClickListener(v -> {
            overScrollerView.smoothScrollTo(0, 0);
        });
    }

}
