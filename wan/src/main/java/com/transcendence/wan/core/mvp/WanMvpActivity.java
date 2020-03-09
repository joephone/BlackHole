package com.transcendence.wan.core.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2020/3/2.
 */

public abstract class WanMvpActivity extends AppCompatActivity {


//    public P presenter;

    /**
     * 获取布局资源文件
     */
    protected abstract int getLayoutId();

    /**
     * 初始化presenter
     */
//    @Nullable
//    protected abstract P initPresenter();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 绑定数据
     */
    protected abstract void loadData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initWindow();
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
        }
//        presenter = initPresenter();
//        if (presenter != null) {
//            presenter.attach(this);
//        }
        initView();
        loadData();
    }

}
