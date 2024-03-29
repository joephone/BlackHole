package com.transcendence.core.base.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.transcendence.core.utils.AppUtils;

import java.util.Calendar;


/**
 * @author Joephone on 2019/5/16 16:03
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 */

public abstract class BaseActivity extends AppCompatActivity {
    /**
     * Activity 在活动界面中的全局变量，用来代替this，在基类中定义是为了省去每个集成此类的 Activity 都定义一次
     */
    protected BaseActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        initActivity();
    }


    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }


    protected void initActivity() {
        mActivity = this;
        init();
        setBar();
    }


    /**
     * 返回一个用于显示界面的布局id
     *
     * @return 视图id
     */
    protected abstract int getLayoutId();


    /**
     * 初始化View后代码写在这个方法中
     */
    protected abstract void init();


    /**
     * 设置沉浸状态栏的方法,需要在setContentView()之后调用
     * //设置6.0以后沉浸状体栏的问题
     */
    private void setBar() {
        //兼容5.0+，状态栏设置透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else {
            //为了沉浸式状态栏 4.4以上才有
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
    }


    /**
     * 通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mActivity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 通过Class跳转界面
     **/
    protected void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    protected void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(mActivity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }


    protected static final int MIN_CLICK_DELAY_TIME = 600;    // 连点最短时间
    protected long lastClickTime1 = 0;  // 记录点击时间
    protected long lastClickTime2 = 0;  // 记录点击时间

    /**
     * 主要的方法，重写dispatchTouchEvent
     * 专治 连点狂魔
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:     // 抬起
                long currentTime1 = Calendar.getInstance().getTimeInMillis();
                if (currentTime1 - lastClickTime1 > MIN_CLICK_DELAY_TIME) {
                    lastClickTime1 = currentTime1;
                } else {
//                    L.logI("ACTION_UP 俩家慢点呃");
                    return true;
                }
                break;
            case MotionEvent.ACTION_DOWN:   // 按下
                long currentTime2 = Calendar.getInstance().getTimeInMillis();
                if (currentTime2 - lastClickTime2 > MIN_CLICK_DELAY_TIME) {
                    lastClickTime2 = currentTime2;
                } else {
//                    L.logI("ACTION_DOWN 俩家慢点呃");
                    return true;
                }
                //点他处 软键盘自动下沉
                View v = getCurrentFocus();
                if (isShouldHideKeyboard(v, ev)) {
                    hideKeyboard(v.getWindowToken());
                }
                break;
            case MotionEvent.ACTION_MOVE:   // 滑动
                break;
        }
        return super.dispatchTouchEvent(ev);
    }



    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *7
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     * @param token
     */
    protected void hideKeyboard(IBinder token) {
        if (token != null) {
//            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
            AppUtils.hideInputMethod(BaseActivity.this);
        }
    }

    /**
     * 实现界面黑白化配置
     */
    protected void setLayerType(){
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        getWindow().getDecorView().setLayerType(View.LAYER_TYPE_HARDWARE,paint);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (mButterKnife != null){ mButterKnife.unbind();}
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


//    //记录用户首次点击返回键的时间
//    private long firstTime=0;
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        switch (keyCode){
//            case KeyEvent.KEYCODE_BACK:
//                long secondPress = System.currentTimeMillis();
//                if(secondPress-firstTime>2000){
//                    firstTime = secondPress;
//                    ToastUtils.show("莫按莫按，再按就退出克鸟");
//                    return false;
//                }else {
//                    System.exit(0);
//                }
//                break;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
