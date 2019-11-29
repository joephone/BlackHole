package com.transcendence.blackhole.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.transcendence.blackhole.base.app.LibApplication;

/**
 * @author Joephone on 2019/11/25 15:58
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class AppInfoUtils {

    static AppInfoUtils INSTANCE;

    public static AppInfoUtils getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new AppInfoUtils();
        }
        return INSTANCE;
    }


    /**
     * 获取本地软件版本号code
     */
    public int getVersionCode() {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = LibApplication.getAppContext().getPackageManager().getPackageInfo(LibApplication.getAppContext().getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }
}
