package com.transcendence.blackhole.index;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.core.AppConstantValue;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.global.Global;

import java.util.List;

/**
 * @author Joephone on 2019/8/28 15:17
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc 多用途序列
 * @Edition 1.0
 * @EditionHistory
 */

public class StandardIndexActivity extends TitleBarActivity implements AdapterView.OnItemClickListener {
    private ArrayAdapter<String> adapter;
    private ListView lvIndex;
    private List<String> items;
    private int layout;
    private String title;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(mActivity, AppConstantValue.otherIndex[position]);
        startActivity(intent);
    }


    @Override
    public void init() {
        lvIndex = findViewById(R.id.lvIndex);

        Bundle bundle = getIntent().getExtras();
        if(null!=bundle){
            items = bundle.getStringArrayList(Global.PUBLIC_INTENT_KEY.ITEMS);
            layout = bundle.getInt(Global.PUBLIC_INTENT_KEY.LAYOUT);
            title = bundle.getString(Global.PUBLIC_INTENT_KEY.TITLE);
            setTitle(title);
        }


        adapter = new ArrayAdapter<>(mActivity,
                android.R.layout.simple_list_item_1, items);
        lvIndex.setAdapter(adapter);
        lvIndex.setOnItemClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return layout;
    }
}
