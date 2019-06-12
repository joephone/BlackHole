package com.transcendence.blackhole.base;

import com.transcendence.blackhole.index.BaseIndexActivity;
import com.transcendence.blackhole.index.VoiceIndexActivity;
import com.transcendence.blackhole.ui.apkbus.ApkBusMainActivity;
import com.transcendence.blackhole.ui.handler.act.HandlerMainActivity;
import com.transcendence.blackhole.ui.base.act.LaunchModeActivity;
import com.transcendence.blackhole.ui.base.act.LifeCycleActivity;
import com.transcendence.blackhole.ui.base.act.TelephoneInfoActivity;
import com.transcendence.blackhole.ui.hardware.act.IsTwoSdCardActivity;
import com.transcendence.blackhole.ui.image.WxImagePickerActivity;
import com.transcendence.blackhole.ui.other.act.TableScheActivity;
import com.transcendence.blackhole.ui.toast.ToastActivity;
import com.transcendence.blackhole.ui.voice.act.RecordActivity;
import com.transcendence.blackhole.ui.voice.act.RecordActivity2;
import com.transcendence.blackhole.ui.widget.custom.AutoScrollActivity;
import com.transcendence.blackhole.ui.widget.scroll.ScrollLayoutTwoActivity;


/**
 * @author Joephone on 2019/5/27 11:45
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc   base序列
 * @Edition 1.0
 * @EditionHistory
 */

public interface AppConstantValue {
    Class[] appIndex = {BaseIndexActivity.class,WxImagePickerActivity.class,VoiceIndexActivity.class,ApkBusMainActivity.class,
                        MainActivity.class,AutoScrollActivity.class,ToastActivity.class,ScrollLayoutTwoActivity.class,
                        IsTwoSdCardActivity.class, TableScheActivity.class};


    Class[] baseIndex = {LifeCycleActivity.class, LaunchModeActivity.class,TelephoneInfoActivity.class, HandlerMainActivity.class};


    Class[] voiceIndex = {RecordActivity.class,RecordActivity2.class};

}
