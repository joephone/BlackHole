package com.transcendence.core.utils.log;

import android.util.Log;

import com.transcendence.core.BuildConfig;
import com.transcendence.core.global.Global;


/**
 * Created by lwb on 2017/12/25.
 * 日志封装
 * StackTrace(堆栈轨迹)存放的就是方法调用栈的信息，每次调用一个方法会产生一个方法栈，
 * 当前方法调用另外一个方法时会使用栈将当前方法的现场信息保存在此方法栈当中，获取这个栈就可以得到方法调用的详细过程。
 * 例如：异常处理中常用的e.printStackTrace()实质就是打印异常调用的堆栈信息。
 *
 * StackTraceElement表示StackTrace(堆栈轨迹)中的一个方法对象，通过这个对象可以获取调用栈当中的调用过程信息，
 * 包括方法的类名、方法名、文件名以及调用的行数。
 * 查看StackTraceElement﻿​类的源代码，我们可以获取方法所在行、所在类等的信息；
 */

public class LogUtils {
    static String TAG = Global.TAG;
    static String className;//类名
    static String methodName;//方法名
    static int lineNumber;//行数
    public static boolean debug;

    /**
     * 判断是否可以调试
     *
     * @return
     */
    public static boolean isDebuggable() {
        return BuildConfig.DEBUG;
    }

    private static String createLog(String log) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("==").append(methodName);
        buffer.append("(").append(className).append(":").append(lineNumber).append(")==:");
        buffer.append(log);
        return buffer.toString();
    }

    /**
     * 获取文件名、方法名、所在行数
     *
     * @param sElements
     */
    private static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }

    public static void e(String message) {
        if (!isDebuggable())
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.e(TAG, createLog(message));
    }

    public static void i(String message) {
        if (!isDebuggable())
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.i(TAG,createLog(message));
    }

    public static void d(String message) {
        if (!isDebuggable())
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.d(TAG, createLog(message));
    }

    public static void v(String message) {
        if (!isDebuggable())
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.v(TAG, createLog(message));
    }

    public static void w(String message) {
        if (!isDebuggable())
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.w(TAG, createLog(message));
    }

}