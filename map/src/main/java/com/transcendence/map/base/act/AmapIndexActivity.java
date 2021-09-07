package com.transcendence.map.base.act;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.transcendence.core.arouter.ARouterController;
import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.core.utils.StringUtils;
import com.transcendence.map.R;
import com.transcendence.map.catches.act.CatchesMapActivity;
import com.transcendence.map.fragment.BasicAmapFragmentActivity;
import com.transcendence.map.gpsloc.GpsLocActivity;
import com.transcendence.map.mobike.main.act.MobikeMainActivity;
import com.transcendence.map.mobike.main.act.SlidingUpPanelActivity;
import com.transcendence.map.mobike.main.act.SlidingUpPanelTestActivity;
import com.transcendence.map.weinxinloc.act.WeixinLocActivity;
import com.transcendence.permissions.OnPermissionCallback;
import com.transcendence.permissions.Permissions;

import java.util.List;

/**
 * @author Joephone on 2019/10/14 17:13
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc Amap序列
 */
@Route(path = ARouterController.AMAP_MAIN)
public class AmapIndexActivity extends TitleBarActivity implements AdapterView.OnItemClickListener {

    private ArrayAdapter<String> adapter;
    private ListView lvIndex;

    Class[] amapIndex = {
            BasicAmapFragmentActivity.class,
            BasicAmapActivity.class,
            AmapLocationActivity.class,
            AmapLocationSourceActivity.class,
            CatchesMapActivity.class,
            WeixinLocActivity.class,
            MobikeMainActivity.class,
            SlidingUpPanelActivity.class,
            SlidingUpPanelTestActivity.class,
            GpsLocActivity.class
    };


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


        Permissions.with(mActivity)
                .permission(com.transcendence.permissions.PermissionPool.GROUP.LOCATION)
                .request(new OnPermissionCallback() {
                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        suc();
                    }
                });


    }


    private void suc(){
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
