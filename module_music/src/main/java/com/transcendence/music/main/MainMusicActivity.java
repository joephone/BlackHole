package com.transcendence.music.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.transcendence.core.utils.log.LogUtils;
import com.transcendence.music.R;

public class MainMusicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_music);
        LogUtils.d("");
    }
}