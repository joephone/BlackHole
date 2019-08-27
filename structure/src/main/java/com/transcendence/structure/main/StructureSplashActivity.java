package com.transcendence.structure.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import com.transcendence.structure.R;

/**
 * @author Joephone on 2019/8/21 11:25
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class StructureSplashActivity extends AppCompatActivity {

    private LinearLayout llSplash;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_structure_splash);

        init();
    }


    public void init() {
        llSplash = findViewById(R.id.llSplash);

//        startAnimThree();

        startAnimFour();
    }


    private void startAnimFour() {
        AlphaAnimation start = new AlphaAnimation(0.3f, 1.0f);
        start.setDuration(3000);
        start.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startMainActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        llSplash.startAnimation(start);
    }


    /**
     * 开启动画
     */
    private void startAnimThree() {
        // 动画集合
        AnimationSet set = new AnimationSet(false);
        // 旋转动画
        RotateAnimation rotateAnimation = new RotateAnimation(180, 180,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        // 设置动画时间
        rotateAnimation.setDuration(2000);
        // 保持动画状态
        rotateAnimation.setFillAfter(true);

        // 缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        // 设置动画时间
        scaleAnimation.setDuration(2000);
        // 保持动画状态
        scaleAnimation.setFillAfter(true);

        // 渐变动画

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        // 保持动画状态
        alphaAnimation.setFillAfter(true);

        // 添加动画
        set.addAnimation(rotateAnimation);
        set.addAnimation(scaleAnimation);
        set.addAnimation(alphaAnimation);
        /*
         * 设置动画的监听事件，当动画运行完成后，启动新的activity
		 */
        set.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                startMainActivity();
            }
        });

        llSplash.startAnimation(set);

    }

    private void startMainActivity() {
        Intent intentMain = new Intent(StructureSplashActivity.this, StructureMainActivity.class);
        startActivity(intentMain);
        finish();
    }
}
