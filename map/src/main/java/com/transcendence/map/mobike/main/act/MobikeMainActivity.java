package com.transcendence.map.mobike.main.act;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.transcendence.map.R;

/**
 * @author Joephone on 2019/11/7 14:50
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class MobikeMainActivity extends AmapFragmentActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobike_main);
        //初始化地图控件
        initMapViewFragment();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }



}
