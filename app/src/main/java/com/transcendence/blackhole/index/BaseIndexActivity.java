package com.transcendence.blackhole.index;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.AppConstantValue;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.utils.StringUtils;

import java.util.List;

/**
 * @author Joephone on 2019/6/6 10:47
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 */

public class BaseIndexActivity extends TitleBarActivity implements AdapterView.OnItemClickListener {
    private ArrayAdapter<String> adapter;
    private ListView lvIndex;
    List<String> items;


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(BaseIndexActivity.this, AppConstantValue.baseIndex[position]);
        startActivity(intent);
    }


    @Override
    public void init() {
        setTitle("base序列");
        lvIndex = findViewById(R.id.lvIndex);

        List<String> items = StringUtils.getStringList(BaseIndexActivity.this,R.array.base_index_item);
        adapter = new ArrayAdapter<>(BaseIndexActivity.this,
                android.R.layout.simple_list_item_1, items);
        lvIndex.setAdapter(adapter);
        lvIndex.setOnItemClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_index;
    }
}