package com.transcendence.blackhole.global;

import android.os.Environment;

/**
 * Created by Joephone on 2018/3/29 13:53
 * E-Mail Address：joephonechen@gmail.com
 */

public class Global {
    public static final String TAG = "blackhole";
    public static boolean isTest = false;
    public static int maxImgCount = 9;

    public static String ABSOLUTE_PATH =  Environment.getExternalStorageDirectory().getAbsolutePath() + "/blackhole";

}
