package com.transcendence.guard.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.GridView;
import android.widget.SlidingDrawer;

import com.transcendence.guard.R;
import com.transcendence.guard.adapter.HomeAdapter;

/**
 * @Author Joephone on 2021/2/9 0009 上午 11:10
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class HomeActivity extends AppCompatActivity {

    private GridView mGvHome;
    private HomeAdapter mHomeAdapter;
    private SlidingDrawer sd;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_guard);

        sd = (SlidingDrawer) findViewById(R.id.sd);
        mGvHome = findViewById(R.id.gv_home);
        mHomeAdapter = new HomeAdapter(HomeActivity.this);
        mGvHome.setAdapter(mHomeAdapter);
    }


    /**
     * 检测menu键打开抽屉
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            if (sd.isOpened()) {
                sd.close();
            } else if (!sd.isOpened()) {
                sd.open();
            }
        }
        return super.onKeyDown(keyCode, event);

    }
}
