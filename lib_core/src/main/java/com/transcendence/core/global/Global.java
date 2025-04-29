package com.transcendence.core.global;

import com.transcendence.core.R;
import com.transcendence.core.base.app.CoreApp;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author joephone
 * @date 2023/5/23
 * @desc
 */
public class Global {

    public static final String TAG = "wan";

    public static final String BUGLY_ID = "4b1e18351f";
    public static final String BUGLY_KEY = "41682c5c-d8d9-4577-b949-c268ad44c023";

    //文件夹名称
    public static final String FILE_TYPE = "FullLog";
    public static final int DEFAULT_LOG_MAX_LIENS = 5;
    //存在日期LAUNCH_TIME
    public static final int FULL_LOG_MAX_KEEP_DAYS = 1;

//    public static final File DIRECTORY_FULL_LOG =  CoreApp.getAppContext().getExternalFilesDir("FullLog");
//    public static final File DIRECTORY_CRASH =  CoreApp.getAppContext().getExternalCacheDir();
    public static final String FILE_PATH_FULL_LOG =  new SimpleDateFormat("yyyy_MM_dd", Locale.getDefault()).format(new Date()) + ".txt";

    public static int[] mBeautyIds = new int[]{
            R.drawable.beauty01,
            R.drawable.beauty02,
            R.drawable.beauty03,
            R.drawable.beauty04,
            R.drawable.beauty05,
            R.drawable.beauty06,
            R.drawable.beauty07,
            R.drawable.beauty08,
            R.drawable.beauty09,
            R.drawable.beauty10,};



    public final class PUBLIC_INTENT_KEY {
        public static final String URL = "url";
        public static final String TITLE = "title";
        public static final String NAME = "name";
        public static final String INDEX = "index";
        public static final String ID = "id";
        public static final String TYPE = "type";
        public static final String SEARCHRESULT = "SearchResult";
        public static final String DATA = "data";
        public static final String LIST = "list";
        public static final String BIG_IMAGE = "bigImage";
    }


    public final class SP_KEY {
        public static final String APP_FIRST_START = "appFirstStart";
        public static final String APP_BADGE = "appBadge";
        public static final String HAS_AGREE = "hasAgree";
        public static final String LAUNCH_TIME = "launch_time";
    }


    public static final class PDF {
        public static String url = "http://hotelpodlipou.sk/uploads/files/sample.pdf";
        public static String url2 = "https://test-jpfile1.oss-cn-shenzhen.aliyuncs.com//Bom/bom/2022/1/19/2022011911370824626513.pdf";
    }

    public final class MAP {
        //比例尺 100
        public static final  int SMALL_ZOOM =18;
        public static final  int MID_ZOOM =13;
        public static final  int BIG_ZOOM =15;
        public static final  String DEFAULT_LAT ="defaultLan";
        public static final  String DEFAULT_LON ="defaultLon";
    }

    public static final class DIRECTORY {
        public static final String FULL_LOG =  CoreApp.getAppContext().getExternalFilesDir(FILE_TYPE).getAbsolutePath(); //File directory = mAppContext.getExternalFilesDir("FullLog");
        public static final String CRASH =  CoreApp.getAppContext().getExternalCacheDir().getAbsolutePath();
        public static final String RECORD =  CoreApp.getAppContext().getExternalFilesDir("Record").getAbsolutePath();
    }


    public static int standardZoom(){
        return MAP.SMALL_ZOOM;
    }

    public static String BASE_URL_DF = "https://app-m.dfpv.com.cn";
    /**
     * 隐私协议
     */
    public static String PRIVACY_CLAUSE = "/webh5/PrivacyClause/index.html";
    /**
     * 用户协议
     */
    public static String USER_AGREEMENT = "/webh5/UserAgreement/index.html";

}
