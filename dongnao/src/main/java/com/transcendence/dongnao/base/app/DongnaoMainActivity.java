package com.transcendence.dongnao.base.app;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.utils.L;
import com.transcendence.blackhole.utils.StringUtils;
import com.transcendence.dongnao.R;

import java.util.List;

public class DongnaoMainActivity extends TitleBarActivity implements AdapterView.OnItemClickListener {


    private ArrayAdapter<String> adapter;
    private ListView lvIndex;
    private List<String> items;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_douya_main;
    }

    @Override
    protected void init() {
        setTitle(false,"序列");
        lvIndex = findViewById(R.id.lvIndex);

        List<String> items = StringUtils.getStringList(mActivity,R.array.dongnao_index_item);
        adapter = new ArrayAdapter<>(mActivity,
                android.R.layout.simple_list_item_1, items);
        lvIndex.setAdapter(adapter);
        lvIndex.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        L.d("position--"+position);
        Intent intent = new Intent();
        intent.setClass(mActivity, DongnaoConstantValue.dongnaoIndex[position]);
        startActivity(intent);
    }
}
