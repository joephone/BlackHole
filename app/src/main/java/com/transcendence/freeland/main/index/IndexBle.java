package com.transcendence.freeland.main.index;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.core.utils.StringUtils;
import com.transcendence.freeland.R;
import com.transcendence.freeland.main.route.AppConstantValue;

import java.util.List;

/**
 * @author Joephone on 2023/6/3 20:12
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc Ble序列
 * @Edition 1.0
 * @EditionHistory
 */

public class IndexBle extends AppAc implements AdapterView.OnItemClickListener {
    private ArrayAdapter<String> adapter;
    private ListView lvIndex;


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(mActivity, AppConstantValue.bleIndex[position]);
        startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_index;
    }

    @Override
    protected void initView() {
        setTitle("BLE序列");
        lvIndex = findViewById(R.id.lvIndex);

        List<String> items = StringUtils.getStringListAndIndex(mActivity,R.array.ble_index_item);
        adapter = new ArrayAdapter<>(mActivity,
                android.R.layout.simple_list_item_1, items);
        lvIndex.setAdapter(adapter);
        lvIndex.setOnItemClickListener(this);
    }
}
