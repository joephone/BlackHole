package com.transcendence.blackhole.base.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.transcendence.blackhole.utils.L;


/**
 * @author Joephone on 2019/5/16 16:03
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 */

public abstract class BaseActivity extends AppCompatActivity {
    // Activity 在活动界面中的全局变量，用来代替this，在基类中定义是为了省去每个集成此类的 Activity 都定义一次
    public BaseActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d("base onCreate");
        setContentView(getLayoutId());
        L.d("base after setContentView");

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
     * 初始化View后代码写在这个方法中
     */
    public abstract void init();

    /**
     * 返回一个用于显示界面的布局id
     *
     * @return 视图id
     */
    public abstract int getLayoutId();


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


    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onPause(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (mButterKnife != null){ mButterKnife.unbind();}
    }
}
