package com.transcendence.blackhole.demo.other.act;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.demo.other.view.InputMethodLayout;
import com.transcendence.core.base.activity.TitleBarActivity;

public class InputMethodSecondActivity extends TitleBarActivity {
    private static final String TAG = "MainActivity";

    InputMethodLayout rl;
    int heightDifference;
    private ImageView iv;
    private boolean flag = false;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo_other_secondactivity;
    }


    protected void init() {
        rl = (InputMethodLayout) findViewById(R.id.rl);
        iv = (ImageView) findViewById(R.id.iv);
        rl.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            //当键盘弹出隐藏的时候会 调用此方法。
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                //获取当前界面可视部分
                InputMethodSecondActivity.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                //获取屏幕的高度
                int screenHeight = InputMethodSecondActivity.this.getWindow().getDecorView().getRootView().getHeight();
                //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
                heightDifference = screenHeight - r.bottom;

                if (heightDifference > screenHeight / 3) {
                    startAnimation();
                    flag = true;
                } else {
                    backAnimation();
                    flag = false;
                }
            }
        });
        startAnimation();
    }


    private void startAnimation() {
        ObjectAnimator animator0 = ObjectAnimator.ofFloat(
                iv,
                "alpha",
                1F,
                0.0F);

//        ObjectAnimator animator1 = ObjectAnimator.ofFloat(iv,
//                "translationY", -heightDifference);


        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(
                animator0
//                ,
//                animator1
        );
        if (!flag) {

            set.start();
        }
    }

    private void backAnimation() {
        ObjectAnimator animator0 = ObjectAnimator.ofFloat(iv,
                "alpha", 0.0F, 1F);
//        ObjectAnimator animator1 = ObjectAnimator.ofFloat(iv,
//                "translationY", -heightDifference, 0);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(
                animator0
//                ,
//                animator1
        );
        set.start();

    }


}
