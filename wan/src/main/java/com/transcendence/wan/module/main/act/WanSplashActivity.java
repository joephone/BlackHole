package com.transcendence.wan.module.main.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.transcendence.blackhole.global.Global;
import com.transcendence.blackhole.utils.GlideUtils;
import com.transcendence.wan.R;
import com.transcendence.wan.base.act.WanBaseActivity;

import java.util.Random;

/**
 * @author Joephone on 2019/9/5 15:19
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class WanSplashActivity extends WanBaseActivity implements Animation.AnimationListener {

    private final int count = 5;
    private final int ANIM_DURATION_TIME = 2500;
    private ImageView ivLauncher;
    private ConstraintLayout clContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wan_splash);

        ivLauncher = findViewById(R.id.ivLauncher);
        clContainer = findViewById(R.id.clContainer);
        int [] ids = Global.mLauncherIds;
        int index =new Random().nextInt(ids.length);
        GlideUtils.getInstance().loadMipmap(this,ids[index],ivLauncher);
        initStartAnim();
    }

    private void initStartAnim() {
        // 渐变展示启动屏
        AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
        aa.setDuration(ANIM_DURATION_TIME * 2);
        aa.setAnimationListener(this);
        clContainer.startAnimation(aa);
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        startAct(WanMainActivity.class);
        finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
