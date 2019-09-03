package com.mzelzoghbi.utils;

import android.util.Log;

/**
 * @author Joephone on 2019/9/3 14:35
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class Jlog {
    /**
     *
     * @param tag
     * @param content
     */
    public static void d(String tag,String content){
        Log.d(tag,content);
    }

    /**
     *
     * @param tag
     * @param content
     */
    public static void e(String tag,String content){
        Log.e(tag,content);
    }

    /**
     *
     * @param tag
     * @param content
     */
    public static void i(String tag,String content){
        Log.i(tag,content);
    }
}
