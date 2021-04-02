package com.transcendence.device.app;

import com.squareup.leakcanary.LeakCanary;
import com.transcendence.core.base.app.LibApplication;
import com.transcendence.device.utils.CrashUtils;

/**
 * @Author Joephone on 2021/3/23 0023 上午 11:59
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class DeviceApp extends LibApplication {

    public void onCreate() {
        super.onCreate();
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);

        CrashUtils.getInstance().init(this);
//        LogUtils.getBuilder(this).setTag("MyTag").setLog2FileSwitch(true).create();
    }
}
