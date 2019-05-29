package com.transcendence.blackhole.activity.widget.custom;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.BaseActivity;
import com.transcendence.blackhole.widget.custom.autoscroll.VerticalScrollTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joephone on 2019/5/17 15:59
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc  自动滑动展示页
 */

public class AutoScrollActivity extends BaseActivity {
    private VerticalScrollTextView vTextView;
    @Override
    public int getLayoutId() {
        return R.layout.activity_widget_custom_auto_scroll;
    }



    @Override
    public void init() {
        initVTextView();
    }

    private void initVTextView() {
        vTextView = findViewById(R.id.v_text_view);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            data.add(String.format("测试竖向滚动文字%s", i));
        }
        vTextView.setDataSource(data);
    }
}
