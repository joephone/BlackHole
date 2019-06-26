package com.transcendence.blackhole.base;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.BaseActivity;

/**
 * @author Joephone on 2019/5/8 10:45
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 */

public class LauncherActivity extends BaseActivity implements Animation.AnimationListener {
    private ImageView ivLauncher;

    private final int ANIM_DURATION_TIME = 1000;

    @Override
    public int getLayoutId() {
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return R.layout.activity_launcher;
    }


    @Override
    public void init() {
        ivLauncher = findViewById(R.id.ivLauncher);
        initStartAnim();
    }

    /**
     * 启动动画
     */
    private void initStartAnim() {
        // 渐变展示启动屏
        AlphaAnimation aa = new AlphaAnimation(0.4f, 1.0f);
        aa.setDuration(ANIM_DURATION_TIME * 2);
        aa.setAnimationListener(this);
        ivLauncher.startAnimation(aa);

//        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        sa.setDuration(ANIM_TIME);
//        mIconView.startAnimation(sa);
//
        // 自转显示启动屏
//        RotateAnimation ra = new RotateAnimation(180, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        ra.setDuration(ANIM_TIME);
//        mTextView.startAnimation(ra);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        startActivity(MainActivity.class);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
