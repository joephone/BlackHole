package com.transcendence.music.ui.welcome;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.transcendence.music.R;

import java.util.concurrent.TimeUnit;


public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_welcome);
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

    public void startNavigationActivity(Long along) {
//        Intent intent = new Intent(this, NavigationActivity.class);
//        startActivity(intent);
//        finish();
    }
}
