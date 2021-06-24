package com.transcendence.core.global;

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
    public static int LIMIT = 10;
    public static String GITHUB_AUTHOR = "https://github.com/joephone";
    public static String GITHUB_AUTHOR_MAIN_PROJECT = "https://github.com/joephone/BlackHole";
    public static String WAN_PRIVATE_POLICY = "file:///android_asset/privacy_policy.html";
    public static String MD5_MAP = "6ec99cb762ffd9158e8b27dc33d9680d";


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
        public static final String APP_BADGE = "appBadge";
    }

    public static final class PDF {
        public static String url = "http://hotelpodlipou.sk/uploads/files/sample.pdf";
        public static String url2 = "http://livedoor.4.blogimg.jp/nikoneko55-hogehoge/imgs/9/9/9937d147.gif";
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


    public final class REQUEST_CODE {   //Request    RequestCode不能为负值,也不能大于16位bit值65536
        public static final int CALL_DIAL = 10020;
        public static final int PDF_PRE = 10030;
        public static final int SET_SERVICE = 10040;
        public static final int PRESCRIBE = 10050;
        public static final int FIND_PWD = 10060;
        public static final int RIGISTER = 10070;
        public static final int DOCTOR_SIGNATURE = 10100;
        public static final int HORI_SIGNATURE = 10101;
        public static final int AUTH = 20000;

        public static final int MESSION = 20020;
        public static final int GROUP_MOVE = 20030;
        public static final int SUBMIT = 20040;
        public static final int DOINGS = 20050;
        public static final int FORM = 20060;
        public static final int ARCHIVEID = 20070;
        public static final int FAST_REPLY = 20080;
        public static final int SEND_QUESTIONAIRE = 20090;
        public static final int FRINTER_PRINT = 20100;
        public final static int REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS = 20110;
        public final static int SMS = 20120;
        public static final int TECH_SUPPORT = 20130;
        public static final int FILLIN_TECH_SUPPORT = 20140;
        public static final int FORM_SIGNATURE = 20150;
        public static final int DOCTOR_FILL_QUESTIONAIRE = 20160;
        public static final int GET_CITY_NAME = 20170;
        public static final int GET_HOSPITAL_NAME = 20180;
        public static final int REQUEST_PERMISSION = 20190;

        public static final int REQUEST_CODE_SELECT = 100;
        public static final int REQUEST_CODE_PREVIEW = 101;



        public static final int SET_DATE = 50020;
        public static final int SET_TIME = 50030;
    }
}
