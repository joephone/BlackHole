package com.transcendence.greenstar.demo.tabvprv.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.transcendence.greenstar.R;
import com.transcendence.greenstar.demo.tabvprv.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabFragment extends Fragment {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private int tabPosition;

    public static TabFragment newInstance(int position) {
        TabFragment fragment = new TabFragment();
        Bundle args = new Bundle();
        args.putInt("tabPosition", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tabPosition = getArguments().getInt("tabPosition");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_demo_tabvprv_fragment_tab, container, false);

        // 初始化 RecyclerView
        recyclerView = view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // 设置 Adapter
        myAdapter = new MyAdapter(generateData());
        recyclerView.setAdapter(myAdapter);

        return view;
    }

    // 生成测试数据
    private List<String> generateData() {
        List<String> data = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            data.add("Tab " + (tabPosition + 1) + " - Item " + i);
        }
        return data;
    }
}
