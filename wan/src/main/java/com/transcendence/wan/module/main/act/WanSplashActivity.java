package com.transcendence.wan.module.main.act;

import android.support.constraint.ConstraintLayout;
import android.view.MotionEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.transcendence.blackhole.global.Global;
import com.transcendence.blackhole.utils.GlideUtils;
import com.transcendence.blackhole.utils.L;
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
    private final int ANIM_DURATION_TIME = 100;
    private ImageView ivLauncher;
    private ConstraintLayout clContainer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wan_splash;
    }

    @Override
    protected void initView() {
        ivLauncher = findViewById(R.id.ivLauncher);
        clContainer = findViewById(R.id.clContainer);
        int [] ids = Global.mLauncherIds;
        int index =new Random().nextInt(ids.length);
        GlideUtils.getInstance().loadMipmap(this,ids[index],ivLauncher);
        initStartAnim();
    }

    @Override
    protected void loadData() {

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


    private float mX = 0;
    //手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)
    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //继承了Activity的onTouchEvent方法，直接监听点击事件
//        if(event.getAction() == MotionEvent.ACTION_DOWN) {
//            //当手指按下的时候
//            x1 = event.getX();
//            y1 = event.getY();
//        }
//        if(event.getAction() == MotionEvent.ACTION_UP) {
//            //当手指离开的时候
//            x2 = event.getX();
//            y2 = event.getY();
//            if(y1 - y2 > 50) {
//                Toast.makeText(mActivity, "向上滑", Toast.LENGTH_SHORT).show();
//            } else if(y2 - y1 > 50) {
//                Toast.makeText(mActivity, "向下滑", Toast.LENGTH_SHORT).show();
//            } else if(x1 - x2 > 50) {
//                Toast.makeText(mActivity, "向左滑", Toast.LENGTH_SHORT).show();
//            } else if(x2 - x1 > 50) {
//                Toast.makeText(mActivity, "向右滑", Toast.LENGTH_SHORT).show();
//            }
//        }

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mX = event.getX();
                L.d(mX+"ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                L.d(event.getX()+"ACTION_UP");
                if(event.getX()-mX>100){
                    finish();
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
