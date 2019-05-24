package com.transcendence.blackhole.base.mvp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.transcendence.blackhole.utils.L;
import com.transcendence.blackhole.utils.permission.PermissionPool;


/**
 * Created by Joephone on 2019/4/22 12:01
 * E-Mail Address：joephonechen@gmail.com
 */

public abstract class BaseActivity extends AppCompatActivity {
    // Activity 在活动界面中的全局变量，用来代替this，在基类中定义是为了省去每个集成此类的 Activity 都定义一次
    public BaseActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
//        ButterKnife.bind(this);
        //预防系统字体放大字体
        mActivity = this;
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        displayMetrics.scaledDensity = displayMetrics.density;

//        SystemBarHelper.immersiveStatusBar(this);
//        StatusBarUtil.setColor(this, getResources().getColor(R.color.transtion),0);
        //设置6.0以后沉浸状体栏的问题
        setBar();



        initView();
        init();
    }

    /**
     * 返回一个用于显示界面的布局id
     * @return          视图id
     */
    public abstract int getLayoutId();

    /** 初始化View的代码写在这个方法中 */
    public abstract void initView();
    /** 初始化View后代码写在这个方法中 */
    public abstract void init();

    /**
     * 设置沉浸状态栏的方法,需要在setContentView()之后调用
     */
    private void setBar() {
        //兼容5.0+，状态栏设置透明  21
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else
            //为了沉浸式状态栏 4.4以上才有 19
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
//        SystemBarHelper.immersiveStatusBar(this);
    }



    @Override
    protected void onRestart() {
        super.onRestart();
        //预防系统字体放大字体
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        displayMetrics.scaledDensity = displayMetrics.density;
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    protected Dialog showLoadDialog;







    /**
     * android6.0权限处理
     * @param permissionCode    权限标记Code
     * @param permissionName    权限名称
     *                          int requestCode, String[] permissions, int[] grantResults
     */
    public void onPermissionRequest(@PermissionPool.PermissionCode int permissionCode, @PermissionPool.PermissionName String permissionName){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            L.logI("22以下");
            onPermissionsGranted(permissionCode);
            return ;
        }
        if(ContextCompat.checkSelfPermission(this, permissionName)== PackageManager.PERMISSION_GRANTED){
            //有权限
            onPermissionsGranted(permissionCode);
        }else{
            //没有权限,开始申请
            ActivityCompat.requestPermissions(this,new String[]{permissionName},permissionCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            //授权成功
            onPermissionsGranted(requestCode);
        }else if(grantResults[0]== PackageManager.PERMISSION_DENIED){
            //授权失败
            onPermissionsDenied(requestCode);
        }
    }

    /**
     * 有授权执行的方法(子类重写)
     */
    public void onPermissionsGranted(int requestCode) {
    }

    /**
     * 没有授权执行的方法(子类重写)
     */
    public void onPermissionsDenied(int requestCode) {
    }



//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if (requestCode == 1) {
//            for (int i = 0; i < permissions.length; i++) {
//                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
////                    Toast.makeText(this, "" + "权限" + permissions[i] + "申请成功", Toast.LENGTH_SHORT).show();
//                } else {
////                    Toast.makeText(this, "" + "权限" + permissions[i] + "申请失败", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }


    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
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
    public void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
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
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(mActivity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }


    // 连点最短时间
    public static final int MIN_CLICK_DELAY_TIME = 600;
    // 记录点击时间
    public long lastClickTime1 = 0;
    // 记录点击时间
    public long lastClickTime2 = 0;

    /**
     * 主要的方法，重写dispatchTouchEvent
     * 专治 连点狂魔
     * @param
     * @return
     */
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_UP:     // 抬起
//                long currentTime1 = Calendar.getInstance().getTimeInMillis();
//                if (currentTime1 - lastClickTime1 > MIN_CLICK_DELAY_TIME) {
//                    lastClickTime1 = currentTime1;
//                } else {
//                    L.logI("ACTION_UP 俩家慢点呃");
//                    return true;
//                }
//                break;
//            case MotionEvent.ACTION_DOWN:   // 按下
//                long currentTime2 = Calendar.getInstance().getTimeInMillis();
//                if (currentTime2 - lastClickTime2 > MIN_CLICK_DELAY_TIME) {
//                    lastClickTime2 = currentTime2;
//                } else {
//                    L.logI("ACTION_DOWN 俩家慢点呃");
//                    return true;
//                }
//                View v = getCurrentFocus();
//                if (isShouldHideKeyboard(v, ev)) {
//                    hideKeyboard(v.getWindowToken());
//                }
//                break;
//            case MotionEvent.ACTION_MOVE:   // 滑动
//                break;
//        }
//        return super.dispatchTouchEvent(ev);
//    }


    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onPause(this);
    }

    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }
}
