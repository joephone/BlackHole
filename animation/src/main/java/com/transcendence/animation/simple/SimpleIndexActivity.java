package com.transcendence.animation.simple;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.transcendence.animation.ConstantValue;
import com.transcendence.animation.R;
import com.transcendence.blackhole.base.mvp.BaseActivity;
import com.transcendence.blackhole.utils.StringUtils;

import java.util.List;

/**
 * @author Joephone on 2019/5/16 10:55
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc  动画起动页
 */
public class SimpleIndexActivity extends BaseActivity implements AdapterView.OnItemClickListener{
    private ArrayAdapter<String> adapter;
    private ListView lvIndex;
    List<String> items;
    @Override
    public int getLayoutId() {
        return R.layout.activity_standard_anim_index;
    }


    @Override
    public void initView() {
        lvIndex = findViewById(R.id.lvIndex);
    }

    @Override
    public void init() {
        initAdapter();

    }

    private void initAdapter() {
        items = StringUtils.getStringList(mActivity,R.array.simple_index);
        adapter = new ArrayAdapter<>(mActivity,android.R.layout.simple_list_item_1,items);
        lvIndex.setAdapter(adapter);
        lvIndex.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Animation anim = AnimationUtils.loadAnimation(mActivity, ConstantValue.simple[position]);
        lvIndex.startAnimation(anim);
    }
}
