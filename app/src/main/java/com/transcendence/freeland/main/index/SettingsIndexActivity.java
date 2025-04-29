package com.transcendence.freeland.main.index;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.core.utils.AppUtils;
import com.transcendence.core.utils.StringUtils;
import com.transcendence.greenstar.R;

import java.util.List;

/**
 * @author Joephone on 2023/5/29 17:53
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc Settings序列
 * @Edition 1.0
 * @EditionHistory
 */

public class SettingsIndexActivity extends AppAc implements AdapterView.OnItemClickListener {
    private ArrayAdapter<String> adapter;
    private ListView lvIndex;
    private List<String> items;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_index;
    }

    @Override
    protected void initView() {
        setTitle("Setting序列");
        lvIndex = findViewById(R.id.lvIndex);

        List<String> items = StringUtils.getStringListAndIndex(mActivity, R.array.settings_index_item);
        adapter = new ArrayAdapter<>(mActivity,
                android.R.layout.simple_list_item_1, items);
        lvIndex.setAdapter(adapter);
        lvIndex.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        switch (position){
            case 0:
                intent =  new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                break;
            case 1:
                intent =  new Intent(Settings.ACTION_WIFI_SETTINGS);
                break;
            case 2:
                Uri packageURI = Uri.parse("package:" + AppUtils.getPackageName());   //"com.tencent.WBlog"
                intent =  new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,packageURI);
                break;
            case 3:  //跳转开发人员选项界面
                intent =  new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
                break;
            case 4:
                intent =  new Intent(Settings.ACTION_APPLICATION_SETTINGS);
                break;
            case 5:
                intent =  new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
                break;
            case 6:
                intent =  new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
                break;
            case 7:
                intent =  new Intent(Settings.ACTION_DATE_SETTINGS);
                break;
            case 8:
                intent =  new Intent(Settings.ACTION_DEVICE_INFO_SETTINGS);
                break;
            case 9:
                intent =  new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
                break;
            case 10:
                intent =  new Intent(Settings.ACTION_INPUT_METHOD_SUBTYPE_SETTINGS);
                break;
            case 11:
                intent =  new Intent(Settings.ACTION_INTERNAL_STORAGE_SETTINGS);
                break;
            case 12:
                intent =  new Intent(Settings.ACTION_MEMORY_CARD_SETTINGS);
                break;
            case 13:
                intent =  new Intent(Settings.ACTION_LOCALE_SETTINGS);
                break;
            case 14:
                intent =  new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                break;
            case 15:
                intent =  new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS);
                break;
            case 16:
                intent =  new Intent(Settings.ACTION_NFCSHARING_SETTINGS);
                break;
            case 17:
                intent =  new Intent(Settings.ACTION_NFC_SETTINGS);
                break;
            case 18:
                intent =  new Intent(Settings.ACTION_SECURITY_SETTINGS);
                break;
            case 19:
                intent =  new Intent(Settings.ACTION_SETTINGS);
                break;
            case 20:
                intent =  new Intent(Settings.ACTION_SOUND_SETTINGS);
                break;
            case 21:
                intent =  new Intent(Settings.ACTION_SYNC_SETTINGS);
                break;
        }
//        intent.setClass(mActivity, SettingsConstantValue.settingsIndex[position]);
        startActivity(intent);
    }


}
