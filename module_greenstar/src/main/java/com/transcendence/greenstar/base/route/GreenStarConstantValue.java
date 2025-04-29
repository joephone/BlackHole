package com.transcendence.greenstar.base.route;

import com.transcendence.greenstar.demo.biometric.BiometricActivity;
import com.transcendence.greenstar.demo.dbnote.act.DBNoteActivity;
import com.transcendence.greenstar.demo.encryption.act.EncryptionMainAc;
import com.transcendence.greenstar.demo.exportlog.logcat.LogcatKt;
import com.transcendence.greenstar.demo.jscalljava.JsCallJavaMainActivity;
import com.transcendence.greenstar.demo.load.LoadAcKt;
import com.transcendence.greenstar.demo.luckypanel.LuckyPanelAc;
import com.transcendence.greenstar.demo.overscroller.act.OverScrollerDemoActivity;
import com.transcendence.greenstar.demo.pdf.act.FileDisplayActivity;
import com.transcendence.greenstar.demo.record.example.RecordExampleActivity;
import com.transcendence.greenstar.demo.showPermissions.ShowPermissionAc;
import com.transcendence.greenstar.demo.splash.SplashActivity;
import com.transcendence.greenstar.demo.tabvprv.act.TabVpRvActivity;
//import com.transcendence.greenstar.demo.wearecord.WeaRecordMainKt;

/**
 * @author joephone
 * @date 2023/2/27
 * @desc
 */
public interface GreenStarConstantValue {

    Class[] demoIndex = {
            LuckyPanelAc.class,
            EncryptionMainAc.class,
      //      WeaRecordMainKt.class,//RecordMainActivity.class,
            RecordExampleActivity.class,
            DBNoteActivity.class,
            BiometricActivity.class,
            LogcatKt.class,
            SplashActivity.class,
            ShowPermissionAc.class,
            LoadAcKt.class,
            FileDisplayActivity.class,
            TabVpRvActivity.class,
            OverScrollerDemoActivity.class,
            JsCallJavaMainActivity.class,
    };

}
