package com.transcendence.blackhole.demo.allapp;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;

/**
 * @author Joephone on 2019/8/27 17:04
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public abstract class SingleFragmentActivity extends TitleBarActivity {
    //子类返回对应的Fragment对象
    protected abstract Fragment createFragment();

    //允许子类使用自己的布局来覆盖父类布局
    @LayoutRes//该注解表示任何时候，该实 现方法都应该返回有效的布局资源ID
    protected int getLayoutResId(){
        return  R.layout.activity_fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        FragmentManager fm=getSupportFragmentManager();

        Fragment fragment=fm.findFragmentById(R.id.frameContainer);
        if(fragment==null){
            fragment=createFragment();
            fm.beginTransaction().add(R.id.frameContainer,fragment).commit();
        }
    }
}
