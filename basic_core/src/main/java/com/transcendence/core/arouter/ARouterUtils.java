package com.transcendence.core.arouter;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.transcendence.core.utils.L;

/**
 * @author Joephone on 2019/12/20 11:37
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class ARouterUtils {

    /**
     * 在activity中添加
     * @param activity              activity_test
     */
    public static void injectActivity(Activity activity){
        if (activity==null){
            return;
        }
        ARouter.getInstance().inject(activity);
    }

    /**
     * 在fragment中添加
     * @param fragment              fragment
     */
    public static void injectFragment(Fragment fragment){
        if (fragment==null){
            return;
        }
        ARouter.getInstance().inject(fragment);
    }

    /**
     * 销毁资源
     */
    public static void destroy(){
        L.d("销毁路由资源");
        ARouter.getInstance().destroy();
    }

    /**
     * 简单的跳转页面
     * @param string                string目标界面对应的路径
     */
    public static void navigation(String string){
        if (string==null){
            return;
        }
        ARouter.getInstance()
                .build(string)
                .navigation();
    }
}
