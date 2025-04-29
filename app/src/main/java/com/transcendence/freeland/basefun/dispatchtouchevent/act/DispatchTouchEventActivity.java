package com.transcendence.freeland.basefun.dispatchtouchevent.act;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.freeland.R;
import com.transcendence.freeland.basefun.dispatchtouchevent.adapter.MyPagerAdapter;

import androidx.viewpager.widget.ViewPager;

/**
 * @author Joephone on 2025/3/14 19:10
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc 事件分发机制解决
 * @Edition 1.0
 * @EditionHistory
 */
public class DispatchTouchEventActivity extends AppAc {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_basefun_dispatchtouchevent_main;
    }

    @Override
    protected void initView() {
        setTitle("事件分发机制解决");
        // 设置ViewPager的适配器
        ViewPager viewPager = findViewById(R.id.vp);
        MyPagerAdapter adapter = new MyPagerAdapter(this);
        viewPager.setAdapter(adapter);
    }
}
