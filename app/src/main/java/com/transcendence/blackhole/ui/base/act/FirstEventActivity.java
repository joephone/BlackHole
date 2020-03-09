package com.transcendence.blackhole.ui.base.act;

import android.view.MotionEvent;
import android.widget.Toast;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;

/**
 * @author Joephone on 2019/6/21 17:59
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class FirstEventActivity extends TitleBarActivity {

    //手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)
    private float x1 = 0;
    private float x2 = 0;
    private float y1 = 0;
    private float y2 = 0;


    @Override
    public int getLayoutId() {
        return R.layout.activity_base_first_event;
    }

    @Override
    public void init() {

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //继承了Activity的onTouchEvent方法，直接监听点击事件
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            //当手指按下的时候
            x1 = event.getX();
            y1 = event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            //当手指离开的时候
            x2 = event.getX();
            y2 = event.getY();
            if(y1 - y2 > 50) {
                Toast.makeText(mActivity, "向上滑", Toast.LENGTH_SHORT).show();
            } else if(y2 - y1 > 50) {
                Toast.makeText(mActivity, "向下滑", Toast.LENGTH_SHORT).show();
            } else if(x1 - x2 > 50) {
                Toast.makeText(mActivity, "向左滑", Toast.LENGTH_SHORT).show();
            } else if(x2 - x1 > 50) {
                Toast.makeText(mActivity, "向右滑", Toast.LENGTH_SHORT).show();
            }
        }

//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                mX = event.getX();
//                L.d(mX+"ACTION_DOWN");
//                break;
//            case MotionEvent.ACTION_UP:
//                L.d(event.getX()+"ACTION_UP");
//                if(event.getX()-mX>100){
//                    finish();
//                }
//                break;
//        }
        return super.onTouchEvent(event);
    }
}
