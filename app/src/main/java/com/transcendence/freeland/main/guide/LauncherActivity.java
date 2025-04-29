package com.transcendence.freeland.main.guide;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.core.global.Global;
import com.transcendence.core.utils.mmkv.MMkvHelper;
import com.transcendence.freeland.R;
import com.transcendence.freeland.main.ArouterAc;
import com.transcendence.logcat.manager.LogManager;

/**
 * @author Joephone on 2019/5/8 10:45
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 */

public class LauncherActivity extends AppAc implements Animation.AnimationListener  {

    private ImageView ivLauncher;
    private ConstraintLayout clContainer;
    private final int ANIM_DURATION_TIME = 500;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launcher;
    }

    @Override
    protected void initView() {
        LogManager.getInstance().startInit(mActivity);
        ivLauncher = findViewById(R.id.ivLauncher);
        clContainer = findViewById(R.id.clContainer);
//        int [] ids = Global.mLauncherIds;
//        int index =new Random().nextInt(ids.length);
//        GlideUtils.getInstance().loadImageFromLocal(ids[index],ivLauncher);
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

        if(MMkvHelper.Companion.getInstance().decodeBooleanFirstStart(Global.SP_KEY.APP_FIRST_START)){
            MMkvHelper.Companion.getInstance().encode(Global.SP_KEY.APP_FIRST_START,false);
            startAc(GuideActivity.class);
            finish();
        }else {
            // AppIndexActivity
            startAc(ArouterAc.class);
            finish();
//            ARouter.getInstance().build("/wan/WanMainActivity").navigation();
//            finish();
        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

}
