package com.transcendence.blackhole.base.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Joephone on 2019/5/7 14:37
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc   Fragment要引用v4
 */

public abstract class BaseFragment extends Fragment {
    // Activity对象
    public FragmentActivity mActivity;
    // 根布局
    private View mRootView;
    /**
     * 获得全局的，防止使用getActivity()为空
     * onAttach 先于onCreateView执行  将fragment固定在活动上
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (FragmentActivity) context;
    }

    /**
     * 获取Activity，防止出现 getActivity() 为空
     */
    public FragmentActivity getFragmentActivity() {
        return mActivity;
    }

    /**
     *  NonNull  不可用空   Nullable可为空
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null && getLayoutId() > 0) {
            mRootView = inflater.inflate(getLayoutId(), null);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(null == mActivity){
            mActivity = getActivity();
        }

        initView(view);
        init();
    }


    //引入布局
    protected abstract int getLayoutId();


    //初始化控件
    protected abstract void initView(View view);

    //初始化控件
    protected abstract void init();


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
        intent.setClass(getActivity(), cls);
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
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }





    /**
     * 销毁当前 Fragment 所在的 Activity
     */
    public void finish() {
        mActivity.finish();
    }

    /**
     * 获取系统服务
     */
    public Object getSystemService(@NonNull String name) {
        return mActivity.getSystemService(name);
    }

    /**
     * Fragment返回键被按下时回调
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //默认不拦截按键事件，传递给Activity
        return false;
    }
}
