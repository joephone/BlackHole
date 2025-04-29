package com.transcendence.core.base.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.transcendence.core.utils.statusbar.StatusBarUtils;


/**
 * @author joephone
 * @date 2023/9/16
 * @desc
 */
public abstract class AbsBaseAc extends AppCompatActivity {

    protected AbsBaseAc mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        StatusBarUtils.Companion.with(this).init();
        init();
    }

    protected void init() {
        initLayout();
        initView();
//        loadData();
    }

    /**
     * 初始化布局
     */
    protected void initLayout() {
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
        }
    }

    /**
     * 获取布局 ID
     */
    protected abstract int getLayoutId();

    /**
     * 初始化布局
     */
    protected abstract void initView();

//    /**
//     * 初始化数据
//     */
//    protected abstract void loadData();
}