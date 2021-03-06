package com.transcendence.blackhole.core;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.transcendence.blackhole.R;
import com.transcendence.core.base.activity.BaseActivity;
import com.transcendence.blackhole.demo.guide.GuideActivity;
import com.transcendence.core.global.Global;
import com.transcendence.blackhole.index.IndexActivity;
import com.transcendence.core.utils.GlideUtils;
import com.transcendence.core.utils.SPUtils;

import java.util.Random;

/**
 * @author Joephone on 2019/5/8 10:45
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 */

public class LauncherActivity extends BaseActivity implements Animation.AnimationListener {
    private ImageView ivLauncher;
    private ConstraintLayout clContainer;
    private final int ANIM_DURATION_TIME = 100;

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
        clContainer = findViewById(R.id.clContainer);
        int [] ids = Global.mLauncherIds;
        int index =new Random().nextInt(ids.length);
        GlideUtils.getInstance().loadImageFromLocal(ids[index],ivLauncher);
        initStartAnim();
    }

    /**
     * 启动动画
     */
    private void initStartAnim() {
        // 渐变展示启动屏
        AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
        aa.setDuration(ANIM_DURATION_TIME * 2);
        aa.setAnimationListener(this);
        clContainer.startAnimation(aa);

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
        if(SPUtils.getInstance(Global.TAG).getBoolean(Global.SP_KEY.APP_FIRST_START,true)){
            SPUtils.getInstance(Global.TAG).put(Global.SP_KEY.APP_FIRST_START,false);
            startActivity(GuideActivity.class);
            finish();
        }else {
            // AppIndexActivity
            startActivity(IndexActivity.class);
            finish();
//            ARouter.getInstance().build("/wan/WanMainActivity").navigation();
//            finish();

            /**
             * // 2. Jump with parameters
             ARouter.getInstance().build("/test/1")
             .withLong("key1", 666L)
             .withString("key3", "888")
             .withObject("key4", new Test("Jack", "Rose"))
             .navigation();
             */
        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
