package com.transcendence.guard.activity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.AlphaAnimation;
import android.widget.ProgressBar;
import android.widget.SlidingDrawer;

import androidx.appcompat.app.AppCompatActivity;

import com.transcendence.guard.R;

public class GuardMainActivity extends AppCompatActivity {

    private ProgressBar pb;
    private SlidingDrawer sd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_guard);

        init();
    }

    private void init() {
        pb = findViewById(R.id.pb);

        //添加朦胧效果
        AlphaAnimation aa= new AlphaAnimation(0.2f,1.0f);
        findViewById(R.id.rl_splash).startAnimation(aa);
        aa.setDuration(800);

    }




//    public boolean onInterceptTouchEvent(MotionEvent event){
//        boolean intercepted=false;
//        int x=(int)event.getX();
//        int y=(int)event.getY();
//        switch(event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if(父容器需要当前点击事件){
//                    intercepted=true;
//                }else{
//                    intercepted=false;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                intercepted=false;
//                break;
//            default:
//                break;
//        }
//        mLastXIntercepted=x;
//        mLastYIntercepted=y;
//        return intercepted;
//    }


//    public boolean dispatchTouchEvent(MotionEvent event){
//        int x=(int)event.getX();
//        int y=(int)event.getY();
//
//        switch(event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                parent.requestDisallowInterceptedTouchEvent(true);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int deltaX=x-mLastX;
//                int deltaY=y-mLastY;
//                if(父容器需要点击事件){
//                    parent.requestDisallowInterceptedTouchEvent(false);
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                intercepted=false;
//                break;
//            default:
//                break;
//        }
//        mLastX=x;
//        mLastY=y;
//        return super.dispatchTouchEvent(event);
//    }


}
