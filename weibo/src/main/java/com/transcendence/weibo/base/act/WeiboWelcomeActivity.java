package com.transcendence.weibo.base.act;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.transcendence.weibo.R;
import com.transcendence.weibo.base.token.AccessTokenKeeper;
import com.transcendence.weibo.ui.login.act.UnLoginActivity;

import java.util.Timer;
import java.util.TimerTask;


/**
 * @author Joephone on 2020/2/28 22:56
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc 必须继承activity
 * @Edition 1.0
 * @EditionHistory
 */

public class WeiboWelcomeActivity extends Activity {

    private Intent mStartIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_layout);

        if (AccessTokenKeeper.readAccessToken(this).isSessionValid()) {
            mStartIntent = new Intent(WeiboWelcomeActivity.this, WeiboMainActivity.class);
        } else {
            mStartIntent = new Intent(WeiboWelcomeActivity.this, UnLoginActivity.class);
        }
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.sendMessage(Message.obtain());
            }
        }, 500);
    }


    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            startActivity(mStartIntent);
            finish();
        }
    };

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
