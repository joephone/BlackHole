package com.transcendence.music.ui.welcome;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.transcendence.music.R;
import com.transcendence.music.ui.WebViewActivity;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class WelcomeActivity extends AppCompatActivity {

    private Timer mTimer;
    private final int ANIM_DURATION_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_welcome);

        countDown();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startTimer();
    }



    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(
                android.R.anim.fade_in,
                android.R.anim.fade_out);
    }

    private void startTimer() {

    }

    public void startMain() {
        Intent intent = new Intent(this, WebViewActivity.class);
        startActivity(intent);
        finish();
    }


    public void countDown() {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                startMain();
            }
        }, ANIM_DURATION_TIME);
    }

}
