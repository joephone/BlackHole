package com.transcendence.guard.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.transcendence.guard.R;
import com.transcendence.guard.adapter.HomeAdapter;


/**
 * @Author Joephone on 2021/2/8 0008 下午 2:18
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class GuardHomeActivity extends AppCompatActivity {

    private GridView mGvHome;
    private HomeAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_home_guard);

        mGvHome = findViewById(R.id.gv_home);
        mAdapter = new HomeAdapter(GuardHomeActivity.this);
        mGvHome.setAdapter(mAdapter);
    }
}
