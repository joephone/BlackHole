package com.transcendence.animation.index;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.transcendence.animation.AnimConstantValue;
import com.transcendence.animation.R;
import com.transcendence.blackhole.base.activity.BaseActivity;
import com.transcendence.blackhole.utils.StringUtils;

import java.util.List;

/**
 * @author Joephone on 2019/5/16 11:52
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc  复杂动画索引
 */
public class ComplexIndexActivity extends BaseActivity implements AdapterView.OnItemClickListener{
    private ArrayAdapter<String> adapter;
    private ListView lvIndex;
    List<String> items;
    @Override
    public int getLayoutId() {
        return R.layout.activity_index;
    }




    @Override
    public void init() {
        lvIndex = findViewById(R.id.lvIndex);
        initAdapter();

    }

    private void initAdapter() {
        items = StringUtils.getStringList(mActivity,R.array.complex_index);
        adapter = new ArrayAdapter<>(mActivity,android.R.layout.simple_list_item_1,items);
        lvIndex.setAdapter(adapter);
        lvIndex.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Animation anim = AnimationUtils.loadAnimation(mActivity, AnimConstantValue.complex[position]);
        lvIndex.startAnimation(anim);
    }
}
