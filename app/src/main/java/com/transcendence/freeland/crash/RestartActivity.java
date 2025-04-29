package com.transcendence.freeland.crash;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.transcendence.core.BuildConfig;
import com.transcendence.freeland.R;
import com.transcendence.freeland.main.ArouterAc;
import com.transcendence.freeland.main.guide.LauncherActivity;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2020/11/29
 *    desc   : 重启应用
 */
public class RestartActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, RestartActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        restart(this);
        Toast.makeText(RestartActivity.this, R.string.crash_restart,Toast.LENGTH_SHORT).show();
        finish();
    }



    public static void restart(Context context) {
        Intent intent;
        if (BuildConfig.DEBUG) {
            // 如果是未登录的情况下跳转到闪屏页
            intent = new Intent(context, LauncherActivity.class);  //SplashActivity SplashActivity
        } else {
            // 如果是已登录的情况下跳转到首页
            intent = new Intent(context, ArouterAc.class);
        }

        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

}
