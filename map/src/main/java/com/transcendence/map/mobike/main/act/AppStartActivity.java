package com.transcendence.map.mobike.main.act;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.transcendence.map.R;
import com.transcendence.map.mobike.personal.act.PersonalActivity;
import com.transcendence.map.mobike.view.SplashVideoView;

/**
 * @author Joephone on 2019/11/6 14:52
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class AppStartActivity extends AppCompatActivity implements View.OnClickListener{

    private SplashVideoView mVideoView;
    private TextView mTvCounter;
    private MobikeCountDownTimer mCountDownTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobike_start);

        mVideoView = findViewById(R.id.videoView);
        mTvCounter = findViewById(R.id.tvCounter);
        mTvCounter.setOnClickListener(this);

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.mobike_splash_video);
        mVideoView.play(uri);

        mCountDownTimer = new MobikeCountDownTimer(3000, 1000);
        mCountDownTimer.start();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tvCounter){
            redirectTo();
        }
    }

    private void redirectTo() {
        Intent intent = new Intent(this,PersonalActivity.class);
        startActivity(intent);
        finish();
    }

    private class MobikeCountDownTimer extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MobikeCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mTvCounter.setText("跳过   "+(millisUntilFinished/1000+"秒"));
        }

        @Override
        public void onFinish() {
            redirectTo();
        }
    }
}
