/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package com.transcendence.douya;

import android.os.Build;
import android.webkit.WebView;

import com.transcendence.blackhole.base.app.LibApplication;


public class DouyaApplication extends LibApplication {


    private static DouyaApplication sInstance;

    public DouyaApplication() {
        sInstance = this;
    }


    public static DouyaApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

//        NightModeHelper.setup(this);
//
//        AndroidThreeTen.init(this);
//        FabricUtils.init(this);
//        ViewTarget.setTagId(R.id.glide_view_target_tag);
//        Stetho.initializeWithDefaults(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (BuildConfig.DEBUG) {
                WebView.setWebContentsDebuggingEnabled(true);
            }
        }
    }
}
