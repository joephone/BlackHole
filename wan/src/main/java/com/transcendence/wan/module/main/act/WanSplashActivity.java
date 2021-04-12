package com.transcendence.wan.module.main.act;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.transcendence.core.global.Global;
import com.transcendence.core.utils.GlideUtils;
import com.transcendence.core.utils.L;
import com.transcendence.ui.textview.kugoo.CountDownTextView;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseActivity;
import com.transcendence.wan.module.main.presenter.SplashPresenter;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Joephone on 2019/9/5 15:19
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class WanSplashActivity extends WanBaseActivity<SplashPresenter> implements Animation.AnimationListener, View.OnClickListener {

    private final int count = 5;
    private final int ANIM_DURATION_TIME = 10000;
    private ImageView ivLauncher;
    private RelativeLayout clContainer;
    private CountDownTextView mTvSkip;
    private Timer mTimer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wan_splash;
    }

    @Nullable
    @Override
    protected SplashPresenter initPresenter() {
        return new SplashPresenter();
    }

    @Override
    protected void initView() {
//        Debug.startMethodTracing("BlackHole");
        ivLauncher = findViewById(R.id.ivLauncher);
        clContainer = findViewById(R.id.clContainer);
        mTvSkip = findViewById(R.id.tv_skip);
        mTvSkip.setOnClickListener(this);
        mTvSkip.setDuration(ANIM_DURATION_TIME);
        int [] ids = Global.mLauncherIds;
        int index =new Random().nextInt(ids.length);
        GlideUtils.getInstance().loadImageFromLocal(ids[index],ivLauncher);
//        initStartAnim();
        CountDown();
        mTvSkip.start();
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
        startMain();
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

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void clearLoading() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        Debug.stopMethodTracing();
    }

    public void CountDown(){
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                startMain();
            }
        },ANIM_DURATION_TIME);

    }

    private void startMain() {
        startAct(WanMainActivity.class);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_skip:
                mTvSkip.stop();
                mTimer.cancel();
                startMain();
                break;
        }
    }
}
