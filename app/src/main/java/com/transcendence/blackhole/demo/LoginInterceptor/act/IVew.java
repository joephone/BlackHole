package com.transcendence.blackhole.demo.LoginInterceptor.act;

import android.app.Activity;
import android.os.Bundle;

/**
 * @Author Joephone on 2020/3/16 1:34
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc 为了方便其他地方的调用采用接口的形式
 * @Edition 1.0
 * @EditionHistory
 */

public interface IVew {

    //获取子类的对象
    Activity getViewActivity();

    //设置窗口样式  会在onCreate之前调用
    void setWindowTheme();

    //初始化控件;子类不能重写onCreate
    void onInitView(Bundle savedInstanceState);

    //视图可见;子类不能重写onResume
    void resume();

}
