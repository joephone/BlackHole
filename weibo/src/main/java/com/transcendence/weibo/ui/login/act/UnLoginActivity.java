package com.transcendence.weibo.ui.login.act;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.transcendence.weibo.R;
import com.transcendence.weibo.base.api.Constants;
import com.transcendence.weibo.ui.home.fragment.HomeFragment;
import com.transcendence.weibo.utils.ToastUtil;

/**
 * Created by wenmingvs on 16/5/9.
 */

public class UnLoginActivity extends AppCompatActivity {

    private static final int HOME_FRAGMENT = 0X001;
    private static final int MESSAGE_FRAGMENT = 0X002;
    private static final int DISCOVERY_FRAGMENT = 0X004;
    private static final int PROFILE_FRAGMENT = 0X005;

    private int mCurrentIndex;
    private Context mContext;
    private HomeFragment mHomeFragment;
//    private MessageFragment mMessageFragment;
//    private DiscoverFragment mDiscoverFragment;
//    private ProfileFragment mProfileFragment;


    private FragmentManager mFragmentManager;
    private Oauth2AccessToken mAccessToken;
    private RelativeLayout mHomeTab, mMessageTab, mDiscoeryTab, mProfile;
    private ImageView mPostTab;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unlogin_mainactivity_layout);
        mContext = this;
        mHomeTab = (RelativeLayout) findViewById(R.id.tv_home);
        mMessageTab = (RelativeLayout) findViewById(R.id.tv_message);
        mDiscoeryTab = (RelativeLayout) findViewById(R.id.tv_discovery);
        mProfile = (RelativeLayout) findViewById(R.id.tv_profile);
        mPostTab = (ImageView) findViewById(R.id.fl_post);

        mFragmentManager = getSupportFragmentManager();
        setTabFragment(HOME_FRAGMENT);
        setUpListener();
//        StatusBarUtils.from(this)
//                .setTransparentStatusbar(true)
//                .setStatusBarColor(Color.WHITE)
//                .setLightStatusBar(true)
//                .process(this);
    }

    public void setTabFragment(int index) {
        if (mCurrentIndex != index) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            hideAllFragments(transaction);
            switch (index) {
                case HOME_FRAGMENT:
                    mHomeTab.setSelected(true);
                    if (mHomeFragment == null) {
                        mHomeFragment = new HomeFragment();
                        transaction.add(R.id.main_content_fl, mHomeFragment);
                    } else {
                        transaction.show(mHomeFragment);
                    }
                    mCurrentIndex = HOME_FRAGMENT;
                    break;
                case MESSAGE_FRAGMENT:
                    mMessageTab.setSelected(true);
//                    if (mMessageFragment == null) {
//                        mMessageFragment = new MessageFragment();
//                        transaction.add(R.id.main_content_fl, mMessageFragment);
//                    } else {
//                        transaction.show(mMessageFragment);
//                    }
                    mCurrentIndex = MESSAGE_FRAGMENT;
                    break;

                case DISCOVERY_FRAGMENT:
                    mDiscoeryTab.setSelected(true);
//                    if (mDiscoverFragment == null) {
//                        mDiscoverFragment = new DiscoverFragment();
//                        transaction.add(R.id.main_content_fl, mDiscoverFragment);
//                    } else {
//                        transaction.show(mDiscoverFragment);
//                    }
                    mCurrentIndex = DISCOVERY_FRAGMENT;
                    break;
                case PROFILE_FRAGMENT:
                    mProfile.setSelected(true);
//                    if (mProfileFragment == null) {
//                        mProfileFragment = new ProfileFragment();
//                        transaction.add(R.id.main_content_fl, mProfileFragment);
//                    } else {
//                        transaction.show(mProfileFragment);
//                    }
                    mCurrentIndex = PROFILE_FRAGMENT;
                    break;
            }
            transaction.commit();
        } else if (mCurrentIndex == HOME_FRAGMENT && mHomeFragment != null) {

        }
    }


    private void hideAllFragments(FragmentTransaction transaction) {
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
        }
//        if (mMessageFragment != null) {
//            transaction.hide(mMessageFragment);
//        }
//
//        if (mDiscoverFragment != null) {
//            transaction.hide(mDiscoverFragment);
//        }
//        if (mProfileFragment != null) {
//            transaction.hide(mProfileFragment);
//        }
//        mHomeTab.setSelected(false);
//        mMessageTab.setSelected(false);
//        mDiscoeryTab.setSelected(false);
//        mProfile.setSelected(false);
    }

    private void setUpListener() {
        mHomeTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTabFragment(HOME_FRAGMENT);

            }
        });
        mMessageTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTabFragment(MESSAGE_FRAGMENT);
            }
        });
        mPostTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShort(mContext, "请先登录");
            }
        });
        mDiscoeryTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTabFragment(DISCOVERY_FRAGMENT);

            }
        });
        mProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTabFragment(PROFILE_FRAGMENT);
            }
        });
    }

    public void openLoginWebView(View view) {
        String authurl = "https://open.weibo.cn/oauth2/authorize" + "?" + "client_id=" + Constants.APP_KEY
                + "&response_type=token&redirect_uri=" + Constants.REDIRECT_URL
                + "&key_hash=" + Constants.AppSecret + (TextUtils.isEmpty(Constants.PackageName) ? "" : "&packagename=" + Constants.PackageName)
                + "&display=mobile" + "&scope=" + Constants.SCOPE;

//        Intent intent = new Intent(mContext, WebViewActivity.class);
//        intent.putExtra("url", authurl);
//        startActivity(intent);
//        finish();
    }
}
