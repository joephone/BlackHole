package com.transcendence.blackhole.base.config;

import android.content.Context;

/**
 * @author Joephone on 2019/10/30 16:10
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc 链式调度 中转  给用户的接口
 * @Edition 1.0
 * @EditionHistory
 */

public class ProjectInit {

    public static Configurator init(Context context){
        Configurator.getInstance()
                .getConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator(){
        return Configurator.getInstance();
    }

    public static <T>T getConfiguratorByKey(Object key){
        return getConfigurator().getConfiguratorByKey(key);
    }

    public static Context getApplicationContext(){
        return getConfiguratorByKey(ConfigKeys.APPLICATION_CONTEXT.name());
    }
}
