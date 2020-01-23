package com.transcendence.blackhole.index;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.arouter.ARouterUtils;
import com.transcendence.blackhole.base.AppConstantValue;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.utils.L;
import com.transcendence.blackhole.utils.StringUtils;

import java.util.List;

/**
 * @author Joephone on 2019/12/27 14:50
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 */


public class IndexActivity extends TitleBarActivity implements AdapterView.OnItemClickListener {
    private ArrayAdapter<String> adapter;
    private ListView lvIndex;
    private List<String> items;


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        L.d("position--"+position);
        ARouterUtils.navigation(AppConstantValue.mainIndex[position]);

    }



    @Override
    public void init() {
        setTitle(false,"序列");
        lvIndex = findViewById(R.id.lvIndex);

        List<String> items = StringUtils.getStringList(mActivity,R.array.index_item);
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
