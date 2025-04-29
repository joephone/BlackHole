package com.transcendence.core.utils;

import android.content.Context;

import com.transcendence.core.base.app.CoreApp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author joephone
 * @date 2023/2/27
 * @desc
 * @edition 1.1 添加自动添加序号方法 + 单例化这个类
 */
public class StringUtils {

    /**
     * 单例化这个类
     */
    private static StringUtils mInstance;

    private StringUtils( ) {

    }

    public static StringUtils getInstance() {
        if (mInstance == null) {
            synchronized (StringUtils.class) {
                if (mInstance == null) {
                    mInstance = new StringUtils();

                }
            }
        }
        return mInstance;

    }

    /**
     * 根据id 得到字符串
     */
    public static String getString(int id) {
        try {
            return CoreApp.getInstance().getApplicationContext().getString(id);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "";
    }

//    public static List<String> getStringList(Context context, int name) {
//        List<String> list = new ArrayList<>();
//        String[] array = context.getResources().getStringArray(name);
//        for (int i = 0; i < array.length; i++) {
//            list.add(array[i]);
//        }
//        return list;
//    }

    /**
     *  自动添加序号
     * @param context
     * @param name
     * @return
     */
    public static List<String> getStringListAndIndex(Context context, int name) {
        List<String> list = new ArrayList<>();
        String[] array = context.getResources().getStringArray(name);
        for (int i = 0; i < array.length; i++) {
            list.add((i+1)+"."+array[i]);
        }
        return list;
    }


    public static String[] getStringArray(Context context, int name) {
        String[] array = context.getResources().getStringArray(name);
        return array;
    }
}
