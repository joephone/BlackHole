package com.transcendence.blackhole.ui.scroll.xiaohuoshu.act;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.ui.scroll.xiaohuoshu.adp.XiaohongshuAdapter;
import com.transcendence.blackhole.ui.scroll.xiaohuoshu.scroll.ScollLinearLayoutManager;

/**
 * @author Joephone on 2019/8/5 14:29
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class XiaohongshuActivity extends TitleBarActivity {

    private RecyclerView mRv;
    private XiaohongshuAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_scroll_xiaohongshu_main;
    }

    @Override
    public void init() {
        //"小红书",
        setTitle(false);
        mRv = findViewById(R.id.mRv);

        adapter = new XiaohongshuAdapter();

        ScollLinearLayoutManager manager = new ScollLinearLayoutManager(mActivity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setAdapter(adapter);
        mRv.setLayoutManager(manager);

        //smoothScrollToPosition滚动到某个位置（有滚动效果）
        mRv.smoothScrollToPosition(Integer.MAX_VALUE / 2);
    }
}
