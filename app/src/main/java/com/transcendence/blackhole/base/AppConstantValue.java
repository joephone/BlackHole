package com.transcendence.blackhole.base;

import com.transcendence.blackhole.arouter.ARouterController;
import com.transcendence.blackhole.demo.allapp.AllAppActivity;
import com.transcendence.blackhole.demo.didi.act.DidiMainActivity;
import com.transcendence.blackhole.demo.handler.act.HandlerMainActivity;
import com.transcendence.blackhole.demo.loopview.LoopViewActivity;
import com.transcendence.blackhole.demo.lottery.act.LotteryOneActivity;
import com.transcendence.blackhole.demo.lottery.act.LotteryThreeActivity;
import com.transcendence.blackhole.demo.lottery.act.LotteryTwoActivity;
import com.transcendence.blackhole.demo.mvp.act.MvpBeautyListActivity;
import com.transcendence.blackhole.demo.mvp.act.MvpLoginActivity;
import com.transcendence.blackhole.demo.other.act.GitHubContributionActivity;
import com.transcendence.blackhole.demo.other.act.OverKeyBoardActivity;
import com.transcendence.blackhole.demo.rvmonitor.act.RvMonitorActivity;
import com.transcendence.blackhole.demo.translationbehavior.act.ZhihuBehaviorActivity;
import com.transcendence.blackhole.index.BaseIndexActivity;
import com.transcendence.blackhole.index.ImageIndexActivity;
import com.transcendence.blackhole.index.LotteryIndexActivity;
import com.transcendence.blackhole.index.MarqueeIndexActivity;
import com.transcendence.blackhole.index.MvpIndexActivity;
import com.transcendence.blackhole.index.OtherIndexActivity;
import com.transcendence.blackhole.index.ScrollIndexActivity;
import com.transcendence.blackhole.index.VoiceIndexActivity;
import com.transcendence.blackhole.ui.base.act.FirstEventActivity;
import com.transcendence.blackhole.ui.base.act.LaunchModeInfoActivity;
import com.transcendence.blackhole.ui.base.act.LifeCycleActivity;
import com.transcendence.blackhole.ui.base.act.LockerHomeActivity;
import com.transcendence.blackhole.ui.base.act.RetrofitIntroActivity;
import com.transcendence.blackhole.ui.base.act.ScreenKeyDownActivity;
import com.transcendence.blackhole.ui.base.act.TelephoneInfoActivity;
import com.transcendence.blackhole.ui.base.act.TouchEventActivity;
import com.transcendence.blackhole.ui.gallery.act.JGalleryMainActivity;
import com.transcendence.blackhole.ui.gallery.act.ZgalleryMainActivity;
import com.transcendence.blackhole.ui.image.act.ImageLoadActivity;
import com.transcendence.blackhole.ui.image.act.WxImagePickerActivity;
import com.transcendence.blackhole.ui.image.headcliper.act.HeadCliperMainActivity;
import com.transcendence.blackhole.ui.rv.mi.act.XiaoMIAct;
import com.transcendence.blackhole.ui.scroll.ScrollLayoutTwoActivity;
import com.transcendence.blackhole.ui.scroll.meituantop.act.MeiTuanMainActivity;
import com.transcendence.blackhole.ui.scroll.personalscroll.act.PersonalScrollMainAct;
import com.transcendence.blackhole.ui.scroll.pulltozoom.act.PullToZoomAct;
import com.transcendence.blackhole.ui.scroll.xiaohuoshu.act.XiaohongshuActivity;
import com.transcendence.blackhole.ui.toast.ToastActivity;
import com.transcendence.blackhole.ui.widget.custom.AutoScrollActivity;
import com.transcendence.blackhole.ui.widget.custom.segmentview.IosSegmentViewActivity;
import com.transcendence.blackhole.ui.widget.marquee.MarqueeTextOneActivity;
import com.transcendence.blackhole.ui.widget.marquee.MarqueeTextTwoActivity;

/**
 * @author Joephone on 2019/5/27 11:45
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc   base序列
 * @Edition 1.0
 * @EditionHistory
 */
public interface AppConstantValue {

    String [] mainIndex = {
            ARouterController.APP_MAIN,
            ARouterController.WAN_MAIN,
            ARouterController.AMAP_MAIN,
                            };

    /**
     * 0
     */
    Class[] appIndex = {MainActivity.class,
            BaseIndexActivity.class,
            ImageIndexActivity.class,
            LotteryIndexActivity.class,
            MarqueeIndexActivity.class,
            MvpIndexActivity.class,
            ScrollIndexActivity.class,
            OtherIndexActivity.class,
            //8
            VoiceIndexActivity.class,
                        AutoScrollActivity.class,ToastActivity.class,RetrofitIntroActivity.class};

    /**
     * 1
     */
    Class[] baseIndex = {LifeCycleActivity.class, LaunchModeInfoActivity.class,ScreenKeyDownActivity.class,LockerHomeActivity.class,
            TelephoneInfoActivity.class, HandlerMainActivity.class, FirstEventActivity.class,TouchEventActivity.class};

    /**
     * 2
     */
    Class[] imageIndex = {WxImagePickerActivity.class,ImageLoadActivity.class,
                            JGalleryMainActivity.class, ZgalleryMainActivity.class,
                                HeadCliperMainActivity.class};

    /**
     * 3
     */
    Class[] lotteryIndex = {LotteryOneActivity.class,LotteryTwoActivity.class,LotteryThreeActivity.class};

    /**
     * 4
     */
    Class[] marqueeIndex = {MarqueeTextOneActivity.class,MarqueeTextTwoActivity.class};

    /**
     * 5
     */
    Class[] mvpIndex = {MvpLoginActivity.class,MvpBeautyListActivity.class};

    /**
     * 6
     */
    Class[] scrollIndex = {XiaohongshuActivity.class,RvMonitorActivity.class,
                            ScrollLayoutTwoActivity.class,
                            MeiTuanMainActivity.class, PullToZoomAct.class,
                            PersonalScrollMainAct.class};

    /**
     * 7
     */
    Class[] otherIndex = {OverKeyBoardActivity.class,
                            GitHubContributionActivity.class, AllAppActivity.class, ZhihuBehaviorActivity.class,
                            IosSegmentViewActivity.class, XiaoMIAct.class, DidiMainActivity.class, LoopViewActivity.class};

    Class[] voiceIndex = {};

}
