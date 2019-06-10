package com.transcendence.blackhole.base;

import com.transcendence.blackhole.activity.apkbus.ApkBusMainActivity;
import com.transcendence.blackhole.activity.base.act.HandlerMainActivity;
import com.transcendence.blackhole.activity.base.act.LaunchModeActivity;
import com.transcendence.blackhole.activity.base.act.LifeCycleActivity;
import com.transcendence.blackhole.activity.base.act.TelephoneInfoActivity;
import com.transcendence.blackhole.activity.image.WxImagePickerActivity;
import com.transcendence.blackhole.activity.toast.ToastActivity;
import com.transcendence.blackhole.activity.widget.custom.AutoScrollActivity;
import com.transcendence.blackhole.activity.widget.scroll.ScrollLayoutTwoActivity;
import com.transcendence.blackhole.index.BaseIndexActivity;


/**
 * @author Joephone on 2019/5/27 11:45
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc   base序列
 * @Edition 1.0
 * @EditionHistory
 */

public interface AppConstantValue {
    Class[] appIndex = {BaseIndexActivity.class,WxImagePickerActivity.class,ApkBusMainActivity.class,
                        MainActivity.class,AutoScrollActivity.class,ToastActivity.class,ScrollLayoutTwoActivity.class};


    Class[] baseIndex = {LifeCycleActivity.class, LaunchModeActivity.class,TelephoneInfoActivity.class, HandlerMainActivity.class};
}
