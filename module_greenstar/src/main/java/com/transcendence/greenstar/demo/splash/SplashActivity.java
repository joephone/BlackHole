package com.transcendence.greenstar.demo.splash;

import android.app.Activity;
import android.os.Handler;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.core.ui.dialog.splash.BizCommonDialog;
import com.transcendence.core.utils.log.LogUtils;
import com.transcendence.greenstar.R;

/**
 * @author joephone
 * @date 2023/6/13
 * @desc
 */
public class SplashActivity extends AppAc {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        checkPrivacyClause(SplashActivity.this);
    }

    private void checkPrivacyClause(final Activity activity) {
        LogUtils.d("checkPrivacyClause");
//        if(!MMkvHelper.Companion.getInstance().decodeBoolean(Global.SP_KEY.HAS_AGREE)){
//
//        }
        BizCommonDialog lcbCommonDialog = BizCommonDialog.newInstance(BizCommonDialog.PRIVACY_CLAUSE, "10000", new BizCommonDialog.OnClickCustomButton() {

            @Override
            public void onClickCustomLeftButton() {
                twiceCheckPrivacyClause(activity);
            }

            @Override
            public void onClickCustomRightButton() {
                new Handler().postDelayed(() ->
                        showMsg("进入程序")
                        , 5000);
            }
        });
        SplashActivity splashActivity = (SplashActivity) activity;
        if (!splashActivity.isDestroyed() && !splashActivity.isFinishing()) {
            lcbCommonDialog.show(SplashActivity.this.getSupportFragmentManager(), BizCommonDialog.TAG_PRIVACYCLAUSE);
        }
    }


    private void twiceCheckPrivacyClause(final Activity activity) {
        LogUtils.d("twiceCheckPrivacyClause");
        BizCommonDialog lcbCommonDialog = BizCommonDialog.newInstance(BizCommonDialog.TWICE_PRIVACY_CLAUSE, "10000", new BizCommonDialog.OnClickCustomButton() {

            @Override
            public void onClickCustomLeftButton() {
                finish();
            }

            @Override
            public void onClickCustomRightButton() {
                checkPrivacyClause(activity);
            }

        });
        SplashActivity splashActivity = (SplashActivity) activity;
        if (!splashActivity.isDestroyed() && !splashActivity.isFinishing()) {
            lcbCommonDialog.show(splashActivity.getSupportFragmentManager(), BizCommonDialog.TAG_TWICE_PRIVACYCLAUSE);
        }
    }
}
