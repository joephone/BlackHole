package com.transcendence.blackhole.core;

import com.transcendence.blackhole.demo.other.act.badge.BadgeActivity;
import com.transcendence.core.arouter.ARouterController;
import com.transcendence.blackhole.base.launchmode.act.SingleTaskFirstActivity;
import com.transcendence.blackhole.demo.allapp.AllAppActivity;
import com.transcendence.blackhole.demo.calc.act.XiaoMiCalcActivity;
import com.transcendence.blackhole.demo.didi.act.DidiMainActivity;
import com.transcendence.blackhole.demo.handler.act.HandlerMainActivity;
import com.transcendence.blackhole.demo.lottery.act.LotteryOneActivity;
import com.transcendence.blackhole.demo.lottery.act.LotteryThreeActivity;
import com.transcendence.blackhole.demo.lottery.act.LotteryTwoActivity;
import com.transcendence.blackhole.demo.mvp.act.MvpBeautyListActivity;
import com.transcendence.blackhole.demo.mvp.act.MvpLoginActivity;
import com.transcendence.blackhole.demo.other.act.GitHubContributionActivity;
import com.transcendence.blackhole.demo.other.act.JavaH5Activity;
import com.transcendence.blackhole.demo.other.act.OverKeyBoardActivity;
import com.transcendence.blackhole.demo.pdfdownload.PdfDownMainActivity;
import com.transcendence.blackhole.demo.scan.act.MengJianNanActivity;
import com.transcendence.blackhole.demo.translationbehavior.act.ZhihuBehaviorActivity;
import com.transcendence.blackhole.index.BaseIndexActivity;
import com.transcendence.blackhole.index.LaunchModeIndexActivity;
import com.transcendence.blackhole.index.LotteryIndexActivity;
import com.transcendence.blackhole.index.MarqueeIndexActivity;
import com.transcendence.blackhole.index.MvpIndexActivity;
import com.transcendence.blackhole.index.OtherIndexActivity;
import com.transcendence.blackhole.index.ScrollIndexActivity;
import com.transcendence.blackhole.index.UIEditIndexActivity;
import com.transcendence.blackhole.index.UIIndexActivity;
import com.transcendence.blackhole.index.UIRvIndexActivity;
import com.transcendence.blackhole.index.UIimageIndexActivity;
import com.transcendence.blackhole.index.VoiceIndexActivity;
import com.transcendence.blackhole.ui.base.act.FirstEventActivity;
import com.transcendence.blackhole.ui.base.act.LifeCycleActivity;
import com.transcendence.blackhole.ui.base.act.LockerHomeActivity;
import com.transcendence.blackhole.ui.base.act.RetrofitIntroActivity;
import com.transcendence.blackhole.ui.base.act.ScreenKeyDownActivity;
import com.transcendence.blackhole.ui.base.act.TouchEventActivity;
import com.transcendence.blackhole.ui.gallery.act.JGalleryMainActivity;
import com.transcendence.blackhole.ui.gallery.act.ZgalleryMainActivity;
import com.transcendence.blackhole.ui.image.act.HandlerDownLoadActivity;
import com.transcendence.blackhole.ui.image.act.ImageAutoCycleOneActivity;
import com.transcendence.blackhole.ui.image.act.ImageAutoCycleTwoActivity;
import com.transcendence.blackhole.ui.image.act.ImageLoadActivity;
import com.transcendence.blackhole.ui.image.act.WxImagePickerActivity;
import com.transcendence.blackhole.ui.image.headcliper.act.HeadCliperMainActivity;
import com.transcendence.blackhole.ui.rv.SwipeRecyclerViewMainActivity;
import com.transcendence.blackhole.ui.rv.freshloadmore.act.RvLoadMoreActivity;
import com.transcendence.blackhole.ui.rv.mi.act.XiaoMiAct;
import com.transcendence.blackhole.ui.rv.pullloadmorerecyclerviewsample.PullLoadMoreRvMainActivity;
import com.transcendence.blackhole.ui.scroll.ScrollLayoutTwoActivity;
import com.transcendence.blackhole.ui.scroll.meituantop.act.MeiTuanMainActivity;
import com.transcendence.blackhole.ui.scroll.pulltozoom.act.PullToZoomAct;
import com.transcendence.blackhole.ui.scroll.xiaohuoshu.act.XiaohongshuActivity;
import com.transcendence.blackhole.ui.toast.ToastActivity;
import com.transcendence.blackhole.ui.widget.custom.segmentview.IosSegmentViewActivity;
import com.transcendence.blackhole.ui.widget.edittext.AutoClearEditActivity;
import com.transcendence.blackhole.ui.widget.edittext.SoftKeyboardAdjustActivity;
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
    Class[] appIndex = {
            MainActivity.class,
            BaseIndexActivity.class,
            UIIndexActivity.class,
            LotteryIndexActivity.class,
            MvpIndexActivity.class,
            ScrollIndexActivity.class,
            OtherIndexActivity.class,
            VoiceIndexActivity.class,
            ToastActivity.class};

    /**
     * 1
     */
    Class[] baseIndex = {
            LifeCycleActivity.class,
            LaunchModeIndexActivity.class,
            ScreenKeyDownActivity.class,
            LockerHomeActivity.class,
            HandlerMainActivity.class,
            FirstEventActivity.class,
            TouchEventActivity.class
            ,RetrofitIntroActivity.class};
    /**
     * 1.1
     */
    Class[] launchModeIndex = {SingleTaskFirstActivity.class};
    /**
     * 2
     */
    Class[] uiIndex = {
            UIimageIndexActivity.class,
            UIRvIndexActivity.class,
            UIEditIndexActivity.class};

    /**
     * 2.1
     */
    Class[] imageIndex =
            {WxImagePickerActivity.class,
            ImageLoadActivity.class,
            JGalleryMainActivity.class,
            ZgalleryMainActivity.class,
            HeadCliperMainActivity.class,
            HandlerDownLoadActivity.class,
            ImageAutoCycleOneActivity.class,
            ImageAutoCycleTwoActivity.class};

    /**
     * 2.2
     */
    Class[] rvIndex = {
            XiaoMiCalcActivity.class,
            RvLoadMoreActivity.class,
            PullLoadMoreRvMainActivity.class,
            SwipeRecyclerViewMainActivity.class};

    /**
     * 2.3
     */
    Class[] etIndex = {
            AutoClearEditActivity.class,
            SoftKeyboardAdjustActivity.class};

    /**
     * 3
     */
    Class[] lotteryIndex = {
            LotteryOneActivity.class,
            LotteryTwoActivity.class,
            LotteryThreeActivity.class};



    /**
     * 4
     */
    Class[] mvpIndex = {
            MvpLoginActivity.class,
            MvpBeautyListActivity.class};

    /**
     * 5
     */
    Class[] scrollIndex = {
            XiaohongshuActivity.class,
            ScrollLayoutTwoActivity.class,
            MeiTuanMainActivity.class,
            PullToZoomAct.class};

    /**
     * 6
     */
    Class[] otherIndex = {
            MarqueeIndexActivity.class,
            OverKeyBoardActivity.class,
            GitHubContributionActivity.class,
            AllAppActivity.class,
            ZhihuBehaviorActivity.class,
            IosSegmentViewActivity.class,
            XiaoMiAct.class,
            DidiMainActivity.class,
            MengJianNanActivity.class,
            XiaoMiCalcActivity.class,
            JavaH5Activity.class,
            PdfDownMainActivity.class,
            BadgeActivity.class};    //PDFdownMainActivity

    Class[] voiceIndex = {};



    /**
     * 6.1
     */
    Class[] marqueeIndex = {
            MarqueeTextOneActivity.class,
            MarqueeTextTwoActivity.class};

}
