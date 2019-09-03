package com.transcendence.blackhole.global;

import android.os.Environment;

import com.transcendence.blackhole.library.R;

/**
 * @author Joephone on 2019/8/9 15:44
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class Global {
    public static final String TAG = "blackhole";
    public static boolean isTest = false;
    public static int maxImgCount = 9;


    public static String ABSOLUTE_PATH =  Environment.getExternalStorageDirectory().getAbsolutePath() + "/blackhole";


    public static int[] mBeautyIds = new int[]{R.mipmap.beauty01,
            R.mipmap.beauty02,
            R.mipmap.beauty03,
            R.mipmap.beauty04,
            R.mipmap.beauty05,
            R.mipmap.beauty06,
            R.mipmap.beauty07,
            R.mipmap.beauty08,
            R.mipmap.beauty09,
            R.mipmap.beauty10,};


    public static int[] mLauncherIds = new int[]{
            R.mipmap.bg_launcher_animation,
            R.mipmap.bg_launcher_bieyoudongtian,
            R.mipmap.beauty01,
            R.mipmap.beauty02,
            R.mipmap.beauty03,
            R.mipmap.beauty04,
            R.mipmap.beauty05,
            R.mipmap.beauty06,
            R.mipmap.beauty07,
            R.mipmap.beauty08,
            R.mipmap.beauty09,
            R.mipmap.beauty10,};


    public final class PUBLIC_INTENT_KEY {
        public static final String ITEMS = "items";
        public static final String LAYOUT = "layout";
        public static final String TITLE = "title";
    }


    public final class SP_KEY {
        public static final String APP_FIRST_START = "appFirstStart";
    }


}
