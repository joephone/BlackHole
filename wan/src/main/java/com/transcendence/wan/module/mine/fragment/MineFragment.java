package com.transcendence.wan.module.mine.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.transcendence.core.global.Global;
import com.transcendence.core.utils.L;
import com.transcendence.ui.dialog.TipDialog;
import com.transcendence.ui.dialog.listener.SimpleCallback;
import com.transcendence.ui.scroll.HeaderZoomLayout;
import com.transcendence.wan.R;
import com.transcendence.wan.core.mvp.WanBaseFragment;
import com.transcendence.wan.event.LoginEvent;
import com.transcendence.wan.module.login.model.LoginBean;
import com.transcendence.wan.module.main.act.WanWebActivity;
import com.transcendence.wan.module.mine.act.AboutMeActivity;
import com.transcendence.wan.module.mine.act.MyCoinActivity;
import com.transcendence.wan.module.mine.act.RankActivity;
import com.transcendence.wan.module.mine.model.MyCoinBean;
import com.transcendence.wan.module.mine.presenter.MinePresenter;
import com.transcendence.wan.module.mine.view.MineView;
import com.transcendence.wan.module.setting.act.SettingActivity;
import com.transcendence.wan.utils.AnimatorUtils;
import com.transcendence.wan.utils.UserUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author Joephone on 2019/12/17 14:41
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class MineFragment extends WanBaseFragment<MinePresenter> implements View.OnClickListener,MineView, HeaderZoomLayout.OnScrollListener {
    private static final String ARG_SHOW_TEXT = "text";
    private ImageView ivRight;


//    private RelativeLayout rlUserInfo;
    private LinearLayout llCoin,llShare,llPpen,llSetting,llAboutAuthor,llReadLater;
    private LinearLayout ll_user_level,ll_user_id;
    private FrameLayout flRight;
    private HeaderZoomLayout mScroll;
    private TextView tvName,tvId,tvRanking,tvLevel,tvMyCoinCount;
    private RoundedImageView userIcon;



    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_navi_mine;
    }

    @Override
    protected MinePresenter initPresenter() {
        return new MinePresenter();
    }


    @Override
    protected void initView() {
        userIcon = findViewById(R.id.civ_user_icon);
        userIcon.setOnClickListener(this);
        tvName = findViewById(R.id.tvName);
        tvName.setOnClickListener(this);
        tvId = findViewById(R.id.tvId);
        tvRanking = findViewById(R.id.tvRanking);
        tvLevel = findViewById(R.id.tvLevel);
        tvMyCoinCount = findViewById(R.id.tvMyCoinCount);
        ll_user_level = findViewById(R.id.ll_user_level);
        ll_user_id = findViewById(R.id.ll_user_level);

//        ivRight = findViewById(R.id.ivRight);
//        rlUserInfo = findViewById(R.id.rlUserInfo);
//        rlUserInfo.setOnClickListener(this);
        llCoin = findViewById(R.id.ll_coin);
        llCoin.setOnClickListener(this);
        llShare= findViewById(R.id.ll_share);
        llShare.setOnClickListener(this);
        llPpen = findViewById(R.id.ll_open_project);
        llPpen.setOnClickListener(this);
        llReadLater = findViewById(R.id.ll_read_later);
        llReadLater.setOnClickListener(this);


        llSetting = findViewById(R.id.ll_setting);
        llSetting.setOnClickListener(this);

//        flRight = findViewById(R.id.fl_right);
//        flRight.setOnClickListener(this);
//        nsv = findViewById(R.id.nsv);
        llAboutAuthor = findViewById(R.id.ll_about_author);
        llAboutAuthor.setOnClickListener(this);
        mScroll = findViewById(R.id.scrollView);
        mScroll.setOnScrollListener(this);
        init();
    }

    @Override
    protected void loadData() {
        loadUserInfo();
    }

    private void init() {
//        ivRight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                RankActivity.start(getContext());
//            }
//        });
//        nsv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//
//            @Override
//            public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                setIvBlurHeight(oldScrollY);
//            }
//        });
    }

    private void setIvBlurHeight(int height) {

    }

    public static MineFragment newInstance(String title) {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.civ_user_icon:
                if(UserUtils.getInstance().toDoIfLogin(getContext())){

                }
                break;
            case R.id.tv_name:
                if(UserUtils.getInstance().toDoIfLogin(getContext())){

                }
                break;
            case R.id.fl_right:
                RankActivity.start(getContext());
                break;
            case R.id.rlUserInfo:
                if(UserUtils.getInstance().toDoIfLogin(getContext())){
                    SettingActivity.start(getActivity());
                }
                break;
            case R.id.ll_coin:
                if(UserUtils.getInstance().toDoIfLogin(getContext())){
                    MyCoinActivity.start(getContext());
                }
                break;
            case R.id.ll_share:
                if(UserUtils.getInstance().toDoIfLogin(getContext())){
                    RankActivity.start(getContext());
                }
                break;
            case R.id.ll_read_later:
//                ARouterUtils.navigation(ARouterController.APP_MAIN);
                //        if (Build.VERSION.SDK_INT < 30) {
                TipDialog.with(getActivity()).message("此应用专为新版本Android设备打造，感谢您的使用")
                        .onYes(new SimpleCallback<Void>() {
                            @Override
                            public void onResult(Void data) {
                                finish();
                            }
                        }).show();
//        }
                break;
            case R.id.ll_open_project:
                WanWebActivity.start(getContext(), Global.GITHUB_AUTHOR_MAIN_PROJECT,"开源项目");
                break;
            case R.id.ll_about_author:
                AboutMeActivity.start(getContext());  // ARouterUtils.navigation(AppConstantValue.mainIndex[2]);
                break;
            case R.id.ll_setting:
                SettingActivity.start(getContext());
                break;

        }
    }

    @Override
    public void getMyCoinSuc(int code, MyCoinBean bean) {
        setMyCoinInfo(bean);
    }


    @Override
    public void onScroll(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if(Math.abs(scrollY - oldScrollY)>50){
            loadUserInfo();
        }
    }

    private void loadUserInfo() {
        if (UserUtils.getInstance().isLogin()) {
            presenter.getCoin();
        }
        if (UserUtils.getInstance().isLogin()) {
            LoginBean bean = UserUtils.getInstance().getLoginBean();
//            ImageLoader.userIcon(civ_user_icon, UserUtils.getInstance().getLoginBean().getIcon());
//            ImageLoader.userBlur(iv_blur, UserInfoUtils.getInstance().getBg());
            if(!TextUtils.isEmpty(bean.getUsername())){
                tvName.setText(bean.getUsername());
            }
            if(bean.getId()>=0){
                tvId.setText("Id："+bean.getId()+"");
            }
            ll_user_id.setVisibility(View.VISIBLE);
            ll_user_level.setVisibility(View.VISIBLE);
            tvMyCoinCount.setText("");
        } else {
//            civ_user_icon.setImageResource(R.color.transparent);
//            iv_blur.setImageResource(R.color.transparent);
            tvName.setText(R.string.login);
            ll_user_id.setVisibility(View.INVISIBLE);
            ll_user_level.setVisibility(View.INVISIBLE);
            tvMyCoinCount.setText("");
            tvId.setText("");
        }
    }

    private void setMyCoinInfo(MyCoinBean bean) {
        if(bean.getLevel()>=0){
            tvLevel.setText("Level："+bean.getLevel()+"");
        }
        if(bean.getRank()>=0){
            tvRanking.setText("Rank："+bean.getRank()+"");
        }
        if(bean.getCoinCount()>=0){
            AnimatorUtils.doIntAnim(tvMyCoinCount, bean.getCoinCount(), 300);
        }
    }


    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent event) {
        L.d("onLoginEvent");
        if (isDetached()) {
            return;
        }
        loadUserInfo();
    }
}
