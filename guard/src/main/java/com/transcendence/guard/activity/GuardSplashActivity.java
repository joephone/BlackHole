package com.transcendence.guard.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.transcendence.guard.R;


/**
 * @Author Joephone on 2021/2/8 下午 12:42
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class GuardSplashActivity extends AppCompatActivity {

    private ProgressBar pb;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_splash_guard);

        init();
    }

    private void init() {
        pb = findViewById(R.id.pb);

    }
}
