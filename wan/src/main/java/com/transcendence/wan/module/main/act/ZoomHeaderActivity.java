package com.transcendence.wan.module.main.act;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.transcendence.wan.R;

/**
 * @Author Joephone on 2021/01/18 17:35
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class ZoomHeaderActivity extends AppCompatActivity {

//    @Override
//    protected int getLayoutId() {
//        return R.layout.fragment_navi_mine;
//    }
//
//    @Override
//    protected void init() {
//
//    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BlackHole","onCreate");
        setContentView(R.layout.fragment_navi_mine);
    }
}
