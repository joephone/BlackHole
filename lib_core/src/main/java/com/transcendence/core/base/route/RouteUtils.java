package com.transcendence.core.base.route;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author joephone
 * @date 2023/2/2
 * @desc
 */
public class RouteUtils {

    public static void navigationActivity(String path){
        ARouter.getInstance().build(path).navigation();
    }

    public static Fragment navigationFragment(String path){
        return (Fragment) ARouter.getInstance().build(path).navigation();
    }

}
