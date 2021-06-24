package com.transcendence.blackhole.demo.radar.act;


import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.transcendence.blackhole.R;
import com.transcendence.core.base.activity.TitleBarActivity;


public class RefreshProgressActivity extends TitleBarActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo_other_radar_refresh_progress;
    }

    @Override
    protected void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
