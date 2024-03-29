package com.transcendence.core.base.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.transcendence.core.base.mvp.LibBasePresenter;
import com.transcendence.core.R;
import com.transcendence.core.utils.AppUtils;
import com.transcendence.core.utils.L;


/**
 * @author Joephone on 2019/5/16 16:03
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 */

public abstract class BaseMvpActivity<V,T extends LibBasePresenter<V>> extends AppCompatActivity {
    /**
     * Activity 在活动界面中的全局变量，用来代替this，在基类中定义是为了省去每个集成此类的 Activity 都定义一次
     */
    public BaseMvpActivity mActivity;
    protected T basePresenter;

    /**
     *  titleBar 层 成员变量
     */
    private View titleBar;
    private View statusView;
    private TextView tvTitle;
    private TextView tvRight;
    private ImageView ivBack;
    private ImageView ivRight;
    private FrameLayout flBack;

    protected boolean mHasTitle = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        basePresenter = createPresenter();
        basePresenter.onAttach((V)this);
        initActivity();
    }


    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    @Override
    public void setContentView(int layoutResID) {
        prepareContentView(layoutResID, mHasTitle);
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
    public abstract int getLayoutId();

    /**
     * 初始化View后代码写在这个方法中
     */
    public abstract void init();

    /**
     *
     */
    protected abstract T createPresenter();

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


    public static final int MIN_CLICK_DELAY_TIME = 600;    // 连点最短时间
    public long lastClickTime1 = 0;  // 记录点击时间
    public long lastClickTime2 = 0;  // 记录点击时间

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
//                long currentTime1 = Calendar.getInstance().getTimeInMillis();
//                if (currentTime1 - lastClickTime1 > MIN_CLICK_DELAY_TIME) {
//                    lastClickTime1 = currentTime1;
//                } else {
////                    L.logI("ACTION_UP 俩家慢点呃");
//                    return true;
//                }
                break;
            case MotionEvent.ACTION_DOWN:   // 按下
//                long currentTime2 = Calendar.getInstance().getTimeInMillis();
//                if (currentTime2 - lastClickTime2 > MIN_CLICK_DELAY_TIME) {
//                    lastClickTime2 = currentTime2;
//                } else {
////                    L.logI("ACTION_DOWN 俩家慢点呃");
//                    return true;
//                }

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

//    /**
//     * android6.0权限处理
//     * @param permissionCode    权限标记Code
//     * @param permissionName    权限名称
//     *                          int requestCode, String[] permissions, int[] grantResults
//     */
//    public void onPermissionRequest(@PermissionPool.PermissionCode int permissionCode, @PermissionPool.PermissionName String permissionName){
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//            L.logI("22以下");
//            onPermissionsGranted(permissionCode);
//            return ;
//        }
//        if(ContextCompat.checkSelfPermission(this, permissionName)== PackageManager.PERMISSION_GRANTED){
//            //有权限
//            onPermissionsGranted(permissionCode);
//        }else{
//            //没有权限,开始申请
//            ActivityCompat.requestPermissions(this,new String[]{permissionName},permissionCode);
//        }
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            //授权成功
            onPermissionsGranted(requestCode);
        }else if(grantResults[0]==PackageManager.PERMISSION_DENIED){
            //授权失败
            onPermissionsDenied(requestCode);
        }
    }

    /**
     * 有授权执行的方法(子类重写)
     */
    protected void onPermissionsGranted(int requestCode) {

    }

    /**
     * 没有授权执行的方法(子类重写)
     */
    protected void onPermissionsDenied(int requestCode) {
    }


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
//            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
            AppUtils.hideInputMethod(BaseMvpActivity.this);
        }
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
        basePresenter.onDetach();
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



    public void setContentView(int layoutResID, boolean hasTitle) {
        prepareContentView(layoutResID, hasTitle);
    }

    public void setContentView(boolean hasTitle) {
        prepareContentView(0, hasTitle);
    }

    private void prepareContentView(int layoutResID, boolean hasTitle) {
        L.d("title bar prepareContentView");
        LinearLayout parent = new LinearLayout(this);
        parent.setOrientation(LinearLayout.VERTICAL);
        if (hasTitle) {
            titleBar = getLayoutInflater().inflate(R.layout.title, parent, false);
            parent.addView(titleBar);
            initTitle();
        }
        // 添加原内容
        LinearLayout.LayoutParams mainParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1);
        if (layoutResID > 0) {
            View mainView = getLayoutInflater().inflate(layoutResID, null);
            mainView.setLayoutParams(mainParams);
            parent.addView(mainView);
            mainView = parent;
            super.setContentView(mainView);
        } else {
            super.setContentView(parent);
        }
    }

    private void initTitle() {
        if (titleBar != null) {
            flBack = (FrameLayout) titleBar.findViewById(R.id.fl_back);
            statusView = (View) titleBar.findViewById(R.id.title_status);
            tvRight = (TextView) titleBar.findViewById(R.id.tv_right);
            tvTitle = (TextView) titleBar.findViewById(R.id.tv_title);
            ivBack = (ImageView) titleBar.findViewById(R.id.iv_back);
            ivRight = (ImageView) titleBar.findViewById(R.id.iv_right);
            initStateHeigt();
            ivBack.setVisibility(View.VISIBLE);
            flBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }

    protected void setTitle(String title) {
        setTitle(title, null);
    }

    protected void setTitle(String title, String rightText) {
        setTitle(true, title, rightText);
    }

    protected void setTitle(boolean showBackButton,String title) {
        setTitle(showBackButton, title, null);
    }

    protected void setTitle(boolean hasTitle) {
        mHasTitle = hasTitle;
    }

    protected void setTitle(boolean showBackButton, String title, String rightText) {
        if (flBack != null) {
            flBack.setVisibility(showBackButton ? View.VISIBLE : View.INVISIBLE);
        }

        if (tvTitle != null) {
            if(!TextUtils.isEmpty(title)){
                tvTitle.setText(title);
            }
        }

        if (tvRight != null) {
            tvRight.setVisibility(rightText == null ? View.INVISIBLE : View.VISIBLE);
            if(!TextUtils.isEmpty(rightText)){
                tvRight.setText(rightText);
            }
        }
    }


    private void initStateHeigt() {
        int statusHeight = AppUtils.getStatusHeight(this);
        LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusHeight);
        statusView.setLayoutParams(l);
    }
}
