package com.transcendence.blackhole.base;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.activity.toast.ToastActivity;
import com.transcendence.blackhole.activity.widget.custom.AutoScrollActivity;
import com.transcendence.blackhole.base.mvp.BaseActivity;
import com.transcendence.blackhole.utils.StringUtils;

import java.util.List;

/**
 * @author Joephone on 2019/5/15 18:45
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc  app 索引页
 */
public class IndexActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    private ArrayAdapter<String> adapter;
    private ListView lvIndex;

    @Override
    public int getLayoutId() {
        return R.layout.activity_index;
    }

    @Override
    public void initView() {
        lvIndex = findViewById(R.id.lvIndex);
    }

    @Override
    public void init() {
        List<String> items = StringUtils.getStringList(mActivity,R.array.index_item);
        adapter = new ArrayAdapter<>(mActivity,
                android.R.layout.simple_list_item_1, items);
        lvIndex.setAdapter(adapter);
        lvIndex.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(MainActivity.class);
                break;
            case 1:
                startActivity(AutoScrollActivity.class);
                break;
            case 2:
                startActivity(ToastActivity.class);
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                break;
        }
    }
}
