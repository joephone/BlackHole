package com.transcendence.map.act;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.utils.StringUtils;
import com.transcendence.blackhole.utils.permission.PermissionPool;
import com.transcendence.map.R;
import com.transcendence.map.catches.act.CatchesMapActivity;
import com.transcendence.map.fragment.BasicAmapFragmentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joephone on 2019/10/14 17:13
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc Amap序列
 */

public class AmapIndexActivity extends TitleBarActivity implements AdapterView.OnItemClickListener {
    private ArrayAdapter<String> adapter;
    private ListView lvIndex;
    private List<String> items = new ArrayList<>();
    Class[] amapIndex = {
            BasicAmapFragmentActivity.class,
            BasicAmapActivity.class,
            AmapLocationActivity.class,
            AmapLocationSourceActivity.class,
            CatchesMapActivity.class};

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(mActivity, amapIndex[position]);
        startActivity(intent);
    }


    @Override
    public void init() {
        setTitle("Amap序列");
        lvIndex = findViewById(R.id.lvIndex);

        onPermissionRequest(PermissionPool.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    @Override
    protected void onPermissionsGranted(int requestCode) {
        super.onPermissionsGranted(requestCode);

        List<String> items = StringUtils.getStringList(mActivity,R.array.amap_index_item);
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
