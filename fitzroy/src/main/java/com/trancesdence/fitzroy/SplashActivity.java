package com.trancesdence.fitzroy;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @Author Joephone on 2021/9/14 0014 上午 11:40
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc   ctrl + alt + L 格式化
 * @Edition 1.0
 * @EditionHistory
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FitzRoyMainActivity.start(SplashActivity.this);
            }
        },3000);
    }
}
