package com.transcendence.freeland.basefun.dispatchtouchevent.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.PagerAdapter;

import com.transcendence.core.utils.log.LogUtils;
import com.transcendence.freeland.R;
import com.transcendence.freeland.basefun.dispatchtouchevent.view.CustomRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyPagerAdapter extends PagerAdapter {
    private Context context;
    private List<String> data;

    public MyPagerAdapter(Context context) {
        this.context = context;
        this.data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            data.add("Page " + (i + 1));
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_basefun_dispatchtouchevent_item_page, container, false);

        // 设置标题
        TextView pageTitle = view.findViewById(R.id.pageTitle);
        pageTitle.setText(data.get(position));
        pageTitle.setVisibility(View.VISIBLE); // 确保TextView可见
        if(pageTitle ==null) {
            LogUtils.d("pageTitle == null");
        } else {
            LogUtils.d("pageTitle != null");
        }

        // 设置页面背景颜色
        int[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN}; // 定义颜色数组
        view.setBackgroundColor(colors[position % colors.length]); // 根据位置设置背景颜色

        // 设置RecyclerView
        CustomRecyclerView recyclerView = view.findViewById(R.id.rv);
        // 设置LayoutManager（必须设置，否则RecyclerView不会显示Item）
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        MyAdapter adapter = new MyAdapter(data.get(position), colors[position % colors.length]); // 传递颜色给适配器
        recyclerView.setAdapter(adapter);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
