package com.transcendence.blackhole.index;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.transcendence.blackhole.R;
import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.blackhole.core.AppConstantValue;
import com.transcendence.core.utils.StringUtils;

import java.util.List;

/**
 * @author Joephone on 2020/5/7 23:16
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc UI EditText序列
 * @Edition 1.0
 * @EditionHistory
 */

public class UIEditIndexActivity extends TitleBarActivity implements AdapterView.OnItemClickListener {
    private ArrayAdapter<String> adapter;
    private ListView lvIndex;
    private List<String> items;


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(mActivity, AppConstantValue.etIndex[position]);
        startActivity(intent);
    }


    @Override
    public void init() {
        setTitle("UI序列");
        lvIndex = findViewById(R.id.lvIndex);

        List<String> items = StringUtils.getStringList(mActivity,R.array.ui_edittext_index_item);
        adapter = new ArrayAdapter<>(mActivity,
                android.R.layout.simple_list_item_1, items);
        lvIndex.setAdapter(adapter);
        lvIndex.setOnItemClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_index;
    }
}
