package com.transcendence.core.base.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author joephone
 * @date 2023/5/27
 * @desc
 */
public class BaseAc extends AppCompatActivity {

    // Activity 在活动界面中的全局变量，用来代替this，在基类中定义是为了省去每个集成此类的 Activity 都定义一次
    protected BaseAc mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
    }

}
