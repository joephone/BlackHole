package com.transcendence.blackhole.demo.radar.act;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.demo.radar.library.RandomTextView;
import com.transcendence.core.base.activity.TitleBarActivity;


public class RadarMainActivity extends TitleBarActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo_other_radar_main;
    }

    @Override
    protected void init() {
        final RandomTextView randomTextView = findViewById(R.id.random_textview);
        randomTextView.setOnRippleViewClickListener(
                new RandomTextView.OnRippleViewClickListener() {
                    @Override
                    public void onRippleViewClicked(View view) {
                        RadarMainActivity.this.startActivity(
                                new Intent(RadarMainActivity.this, RefreshProgressActivity.class));
                    }
                });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                randomTextView.addKeyWord("大S");
                randomTextView.addKeyWord("小S");
                randomTextView.addKeyWord("微S");
                randomTextView.show();
            }
        }, 2 * 1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_radar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
