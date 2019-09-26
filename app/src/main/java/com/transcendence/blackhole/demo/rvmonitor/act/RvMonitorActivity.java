package com.transcendence.blackhole.demo.rvmonitor.act;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.demo.rvmonitor.adp.RvMonitorAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joephone on 2019/8/20 15:31
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class RvMonitorActivity extends TitleBarActivity {

    private RvMonitorAdapter adapter;
    private RecyclerView mRv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_scroll_recyclerview_monitor_main;
    }

    @Override
    public void init() {
        setTitle("自动播放视频列表");
        mRv = findViewById(R.id.rv);

        initData();

        setRv();
    }

    private void initData() {
        List<RvMonitorBean> list = new ArrayList<>();
        //播放状态   0播放，1停止
        for (int i = 0; i < 10; i++) {
            list.add(new RvMonitorBean(1));
        }
        adapter = new RvMonitorAdapter(list);
    }

    private void setRv() {
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(manager);
        mRv.setAdapter(adapter);
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}
