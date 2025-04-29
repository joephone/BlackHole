package com.transcendence.greenstar.main.index;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.transcendence.core.base.activity.AppAc;
import com.transcendence.core.base.route.RoutePath;
import com.transcendence.core.utils.StringUtils;
import com.transcendence.greenstar.R;
import com.transcendence.greenstar.base.route.GreenStarConstantValue;

import java.util.List;

/**
 * @author Joephone on 2019/6/17 11:51
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc 其他序列
 * @Edition 1.0
 * @EditionHistory
 */
@Route(path = RoutePath.GreenStar.PAGER_MAIN)
public class DemoIndexActivity extends AppAc implements AdapterView.OnItemClickListener {
    private ArrayAdapter<String> adapter;
    private ListView lvIndex;
    private List<String> items;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_index;
    }

    @Override
    protected void initView() {
        setTitle("其他序列");
        lvIndex = findViewById(R.id.lvIndex);

        List<String> items = StringUtils.getStringListAndIndex(mActivity, R.array.demo_index_item);
        adapter = new ArrayAdapter<>(mActivity,
                android.R.layout.simple_list_item_1, items);
        lvIndex.setAdapter(adapter);
        lvIndex.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(mActivity, GreenStarConstantValue.demoIndex[position]);
        startActivity(intent);
    }


}
