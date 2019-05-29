package com.transcendence.blackhole.base;

import com.transcendence.blackhole.activity.apkbus.ApkBusMainActivity;
import com.transcendence.blackhole.activity.base.LifeCycleActivity;
import com.transcendence.blackhole.activity.image.WxImagePickerActivity;
import com.transcendence.blackhole.activity.toast.ToastActivity;
import com.transcendence.blackhole.activity.widget.custom.AutoScrollActivity;

//import com.transcendence.blackhole.activity.base.LifeCycleActivity;
//import com.transcendence.blackhole.activity.image.WxImagePickerActivity;

/**
 * @author Joephone on 2019/5/27 11:45
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public interface AppConstantValue {
    Class[] appIndex = {LifeCycleActivity.class, WxImagePickerActivity.class,ApkBusMainActivity.class,
                        MainActivity.class,AutoScrollActivity.class,ToastActivity.class};

}
