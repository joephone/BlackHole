package com.transcendence.blackhole.utils;


import android.util.Log;

import com.transcendence.blackhole.global.Global;


/**
 * Created by Administrator on 2017/9/7.
 */

public class L {

    //如果打包  就将isTest设置为false
    public static void logI(String content) {

            Log.i(Global.TAG, content);

    }

    public static void logE(String content) {

            Log.e(Global.TAG, content);

    }

    public static void d(String content) {
            Log.d(Global.TAG, content);
    }

}