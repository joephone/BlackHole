package com.transcendence.freeland.main.index;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.core.utils.StringUtils;
import com.transcendence.freeland.main.route.AppConstantValue;
import com.transcendence.greenstar.R;

import java.util.List;

/**
 * @author Joephone on 2023/6/9 16:37
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc  基本功能序列
 * @Edition 1.0
 * @EditionHistory
 */

public class BasefunIndexActivity extends AppAc implements AdapterView.OnItemClickListener {
    private ArrayAdapter<String> adapter;
    private ListView lvIndex;
    private List<String> items;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_index;
    }

    @Override
    protected void initView() {
        setTitle("基本功能序列");
        lvIndex = findViewById(R.id.lvIndex);

        List<String> items = StringUtils.getStringListAndIndex(mActivity, R.array.base_fun_index_item);
        adapter = new ArrayAdapter<>(mActivity,
                android.R.layout.simple_list_item_1, items);
        lvIndex.setAdapter(adapter);
        lvIndex.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(mActivity, AppConstantValue.baseFunIndex[position]);
        startActivity(intent);
    }


}
