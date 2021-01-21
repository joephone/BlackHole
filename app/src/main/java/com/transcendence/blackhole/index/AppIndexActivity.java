package com.transcendence.blackhole.index;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.transcendence.blackhole.R;
import com.transcendence.blackhole.arouter.ARouterController;
import com.transcendence.blackhole.core.AppConstantValue;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.utils.L;
import com.transcendence.blackhole.utils.StringUtils;

import java.util.List;

/**
 * @author Joephone on 2019/5/24 11:55
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 */

@Route(path = ARouterController.APP_MAIN)
public class AppIndexActivity extends TitleBarActivity implements AdapterView.OnItemClickListener {
    private ArrayAdapter<String> adapter;
    private ListView lvIndex;
    private List<String> items;


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        L.d("position--"+position);
        Intent intent = new Intent();
        intent.setClass(mActivity, AppConstantValue.appIndex[position]);
        startActivity(intent);
    }



    @Override
    public void init() {
        setTitle(false,"序列");
        lvIndex = findViewById(R.id.lvIndex);

        List<String> items = StringUtils.getStringList(mActivity,R.array.app_index_item);
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
