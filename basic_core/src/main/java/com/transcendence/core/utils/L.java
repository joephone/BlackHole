package com.transcendence.core.utils;


import android.util.Log;

import com.transcendence.core.global.Global;


/**
 * Created by Administrator on 2017/9/7.
 * 如果打包  就将isTest设置为false
 */

public class L {

    public static void logI(String content) {
        Log.i(Global.TAG, content);
    }

    public static void logE(String content) {
        Log.e(Global.TAG, content);
    }

    /**
     * 调试通用日志
     * @param content
     */
    public static void d(String content) {
        Log.d(Global.TAG, content);
    }


    public static void w(String content) {
        Log.w(Global.TAG, content);
    }

    public static void w(Throwable content) {
        Log.w(Global.TAG, content);
    }

    public static void e(String content) {
        Log.e(Global.TAG, content);
    }

}
