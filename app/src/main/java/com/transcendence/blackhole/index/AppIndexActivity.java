package com.transcendence.blackhole.index;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.activity.image.WxImagePickerActivity;
import com.transcendence.blackhole.utils.StringUtils;

import java.util.List;

/**
 * @author Joephone on 2019/5/24 11:55
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 */

public class AppIndexActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ArrayAdapter<String> adapter;
    private ListView lvIndex;
    List<String> items;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        init();
    }

    private void init() {
        lvIndex = findViewById(R.id.lvIndex);

        List<String> items = StringUtils.getStringList(AppIndexActivity.this,R.array.app_index_item);
        adapter = new ArrayAdapter<>(AppIndexActivity.this,
                android.R.layout.simple_list_item_1, items);
        lvIndex.setAdapter(adapter);
        lvIndex.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        switch (position){
            case 0:
                intent.setClass(AppIndexActivity.this, WxImagePickerActivity.class);
                startActivity(intent);
                break;
        }
    }
}
