package com.transcendence.blackhole.core.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

//import com.antfortune.freeline.FreelineCore;
import com.transcendence.blackhole.R;
import com.transcendence.blackhole.demo.LoginInterceptor.act.Interceptors;
import com.transcendence.core.utils.L;

/**
 * @author Joephone on 2019/5/6 11:49
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 */

public class ApbApplication extends ApApplication {


    int count=0;

    @Override
    public void onCreate() {
        super.onCreate();
//        FreelineCore.init(this);

        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
//                L.d("onActivityStarted--");
                if (count == 0) {
//                    L.d(">>>>>>>>>>>>>>>>>>>切到前台  lifecycle");
//                    Toast.makeText(activity,"APP切到前台",Toast.LENGTH_SHORT).show();
                }
                count++;
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
//                L.d("onActivityStopped--");
                count--;
                if (count == 0) {
                    Toast.makeText(getApplicationContext(), R.string.activity_safe_warning,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
//                L.d("onActivitySaveInstanceState--");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
//                L.d("onActivityDestroyed--");
            }
        });

    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }
}
