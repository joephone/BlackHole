package com.transcendence.blackhole.base;

import com.transcendence.blackhole.index.BaseIndexActivity;
import com.transcendence.blackhole.index.ImageIndexActivity;
import com.transcendence.blackhole.index.LotteryIndexActivity;
import com.transcendence.blackhole.index.MarqueeIndexActivity;
import com.transcendence.blackhole.index.OtherIndexActivity;
import com.transcendence.blackhole.index.VoiceIndexActivity;
import com.transcendence.blackhole.ui.base.act.FirstEventActivity;
import com.transcendence.blackhole.ui.base.act.LaunchModeActivity;
import com.transcendence.blackhole.ui.base.act.LifeCycleActivity;
import com.transcendence.blackhole.ui.base.act.LockerHomeActivity;
import com.transcendence.blackhole.ui.base.act.RetrofitIntroActivity;
import com.transcendence.blackhole.ui.base.act.ScreenKeyDownActivity;
import com.transcendence.blackhole.ui.base.act.TelephoneInfoActivity;
import com.transcendence.blackhole.ui.handler.act.HandlerMainActivity;
import com.transcendence.blackhole.ui.hardware.act.IsTwoSdCardActivity;
import com.transcendence.blackhole.ui.image.act.ImageLoadActivity;
import com.transcendence.blackhole.ui.image.act.WxImagePickerActivity;
import com.transcendence.blackhole.ui.lottery.act.LotteryOneActivity;
import com.transcendence.blackhole.ui.lottery.act.LotteryThreeActivity;
import com.transcendence.blackhole.ui.lottery.act.LotteryTwoActivity;
import com.transcendence.blackhole.ui.mvp.act.MvpLoginActivity;
import com.transcendence.blackhole.ui.other.act.OverKeyBoardActivity;
import com.transcendence.blackhole.ui.other.act.TableScheActivity;
import com.transcendence.blackhole.ui.other.photowaterfall.act.WaterFallOneActivity;
import com.transcendence.blackhole.ui.toast.ToastActivity;
import com.transcendence.blackhole.ui.voice.act.RecordActivity;
import com.transcendence.blackhole.ui.voice.act.RecordActivity2;
import com.transcendence.blackhole.ui.widget.custom.AutoScrollActivity;
import com.transcendence.blackhole.ui.widget.marquee.MarqueeTextOneActivity;
import com.transcendence.blackhole.ui.widget.marquee.MarqueeTextTwoActivity;
import com.transcendence.blackhole.ui.widget.scroll.ScrollLayoutTwoActivity;


/**
 * @author Joephone on 2019/5/27 11:45
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc   base序列
 * @Edition 1.0
 * @EditionHistory
 */

public interface AppConstantValue {
    Class[] appIndex = {BaseIndexActivity.class, ImageIndexActivity.class,LotteryIndexActivity.class,MarqueeIndexActivity.class, OtherIndexActivity.class,VoiceIndexActivity.class,
                        MainActivity.class,AutoScrollActivity.class,ToastActivity.class,MvpLoginActivity.class,RetrofitIntroActivity.class};


    Class[] baseIndex = {LifeCycleActivity.class, LaunchModeActivity.class,ScreenKeyDownActivity.class,LockerHomeActivity.class,
            TelephoneInfoActivity.class, HandlerMainActivity.class, FirstEventActivity.class};


    Class[] imageIndex = {WxImagePickerActivity.class,ImageLoadActivity.class};


    Class[] lotteryIndex = {LotteryOneActivity.class,LotteryTwoActivity.class,LotteryThreeActivity.class};


    Class[] marqueeIndex = {MarqueeTextOneActivity.class,MarqueeTextTwoActivity.class};


    Class[] voiceIndex = {RecordActivity.class,RecordActivity2.class};


    Class[] otherIndex = {ScrollLayoutTwoActivity.class,
            IsTwoSdCardActivity.class, TableScheActivity.class, WaterFallOneActivity.class, OverKeyBoardActivity.class};



}
