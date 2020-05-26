package com.transcendence.blackhole.global;

import android.os.Environment;

import com.transcendence.core.R;

/**
 * @author Joephone on 2019/8/9 15:44
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class Global {
    public static final String TAG = "BlackHole";
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
            R.drawable.ic_life_cycle,};


    public final class PUBLIC_INTENT_KEY {
        public static final String ITEMS = "items";
        public static final String LAYOUT = "layout";
        public static final String TITLE = "title";
    }


    public final class SP_KEY {
        public static final String APP_FIRST_START = "appFirstStart";
    }

    public final class PDF {
        String url = "http://hotelpodlipou.sk/uploads/files/sample.pdf";
        String url2 = "http://livedoor.4.blogimg.jp/nikoneko55-hogehoge/imgs/9/9/9937d147.gif";
    }

    public final class MAP {
        //比例尺 100
        public static final  int SMALL_ZOOM =18;
        public static final  int MID_ZOOM =13;
        public static final  int BIG_ZOOM =15;
        public static final  String DEFAULT_LAT ="defaultLan";
        public static final  String DEFAULT_LON ="defaultLon";
    }


    public static int standardZoom(){
        return MAP.SMALL_ZOOM;
    }





}
