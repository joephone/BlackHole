package com.transcendence.wan.base.app;

import com.transcendence.blackhole.base.app.LibApplication;
import com.transcendence.config.ProjectInit;
import com.transcendence.global.API;

/**
 * @author Joephone on 2019/12/9 16:00
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class WanApp extends LibApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        ProjectInit.init(this)
                .withApiHost(API.API_WAN_ANDROID)
                .configurator();
    }
}
